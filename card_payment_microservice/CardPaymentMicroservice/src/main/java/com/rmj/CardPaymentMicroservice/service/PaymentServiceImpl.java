package com.rmj.CardPaymentMicroservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rmj.CardPaymentMicroservice.dto.BankAccountDTO;
import com.rmj.CardPaymentMicroservice.dto.TransactionCompletedDTO;
import com.rmj.CardPaymentMicroservice.model.Currency;
import com.rmj.CardPaymentMicroservice.model.Transaction;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Value("${frontend.url}")
	private String frontendUrl;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	@Override
	public String getFrontendUrl() {
		return frontendUrl;
	}	

	@Override
	public Long makeTransaction(Long merchantOrderId, double amount, Currency currency, LocalDateTime timestamp,
			String redirectUrl, String callbackUrl) {
		Transaction transaction = new Transaction(merchantOrderId, amount, currency, timestamp, redirectUrl, callbackUrl);
		transaction = transactionService.save(transaction);
		return transaction.getId();
	}

	@Override
	public String pay(Long transactionId, BankAccountDTO bankAccountDTO) {
		Transaction transaction = transactionService.getTransaction(transactionId);
		transaction.setCardNumber(""+bankAccountDTO.getCardNumber());
		transactionService.save(transaction);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BankAccountDTO> httpEntity = new HttpEntity<BankAccountDTO>(bankAccountDTO, headers);
        String bankUrl = "https://localhost:8080/api/bank/check";
        ResponseEntity<String> responseEntity = restTemplate.exchange(bankUrl, HttpMethod.POST, httpEntity, String.class);
        
        String status;
        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody().equals("Transaction is acceptable!")) {
        	status = "success";
        } else {
        	status = "fail";
        }
        
        HttpEntity<TransactionCompletedDTO> httpEntity2 = new HttpEntity<TransactionCompletedDTO>(new TransactionCompletedDTO(transaction.getMerchantOrderId(), status), headers);
    	ResponseEntity<Void> responseEntity2 = restTemplate.exchange(transaction.getCallbackUrl(), HttpMethod.PUT, httpEntity2, Void.class);
        return transaction.getRedirectUrl();
	}
}
