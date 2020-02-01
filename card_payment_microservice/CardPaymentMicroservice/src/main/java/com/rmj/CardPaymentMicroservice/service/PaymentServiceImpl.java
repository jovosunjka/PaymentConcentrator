package com.rmj.CardPaymentMicroservice.service;

import java.time.LocalDateTime;
import java.util.List;

import com.rmj.CardPaymentMicroservice.dto.*;
import com.rmj.CardPaymentMicroservice.model.FormField;
import com.rmj.CardPaymentMicroservice.model.TransactionStatus;
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

import com.rmj.CardPaymentMicroservice.model.Currency;
import com.rmj.CardPaymentMicroservice.model.Transaction;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Value("${frontend.url}")
	private String frontendUrl;

	@Value("${callback-url-sent-bank}")
	private String callBackUrlSentBank;
	
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private FormFieldService formFieldService;

	@Autowired
	private RestTemplate restTemplate;


	
	@Override
	public String getFrontendUrl() {
		return frontendUrl;
	}	

	@Override
	public RedirectUrlDTO makeTransaction(String merchantId, String merchantPassword, Long merchantOrderId, double amount,
										  Currency currency, LocalDateTime timestamp,
			String redirectUrl, String callbackUrl) {
		Transaction transaction = new Transaction(merchantOrderId, amount, currency, timestamp, redirectUrl, callbackUrl);
		transaction = transactionService.save(transaction);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		PayForBankDTO payForBankDTO = new PayForBankDTO(merchantId, merchantPassword, transaction.getId(), amount, currency, timestamp,
				callBackUrlSentBank, redirectUrl);
		HttpEntity<PayForBankDTO> httpEntity = new HttpEntity<PayForBankDTO>(payForBankDTO, headers);
		String bankUrl = "https://localhost:8080/api/bank/check";
		ResponseEntity<RedirectUrlDTO> responseEntity = restTemplate.exchange(bankUrl, HttpMethod.POST, httpEntity,
																								RedirectUrlDTO.class);

		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("Paying on bank - error");
		}

		return  responseEntity.getBody();
	}

	@Override
	public String pay(Long transactionId, int cardNumber, int securityCode, String cardHolder, String expirationDate) {
		
		Transaction transaction = transactionService.getTransaction(transactionId);
		transaction.setCardNumber("" + cardNumber);
		transaction = transactionService.save(transaction);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        BankAccountDTO bankAccountDTO = new BankAccountDTO(cardNumber, securityCode, transaction.getAmount(),cardHolder,expirationDate);
        HttpEntity<BankAccountDTO> httpEntity = new HttpEntity<BankAccountDTO>(bankAccountDTO, headers);
        String bankUrl = "https://localhost:8080/api/bank/check?transactionId="+ transactionId;
        ResponseEntity<RedirectUrlDTO> responseEntity = restTemplate.exchange(bankUrl, HttpMethod.POST, httpEntity, RedirectUrlDTO.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
        	throw new RuntimeException("Paying on bank - error");
        }

        return transaction.getRedirectUrl();
	}

    @Override
    public void transactionCompleted(Long transactionId, Long acquirerOrderId, LocalDateTime acquirerTimeStamp,
								TransactionStatus status) {
        Transaction transaction = transactionService.getTransaction(transactionId);
        transaction.setStatus(status);
        transactionService.save(transaction);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        TransactionCompletedDTO transactionCompletedDTO = new TransactionCompletedDTO(transaction.getMerchantOrderId(), status);
        HttpEntity<TransactionCompletedDTO> httpEntity =
								new HttpEntity<TransactionCompletedDTO>(transactionCompletedDTO, headers);
        restTemplate.exchange(transaction.getCallbackUrl(), HttpMethod.PUT, httpEntity, Void.class);
    }

	@Override
	public FormFieldsForPaymentTypeDTO getFormFieldsForPaymentType() {
		List<FormField> formFields = formFieldService.getFormFields();
		return new FormFieldsForPaymentTypeDTO(formFields);
	}
}
