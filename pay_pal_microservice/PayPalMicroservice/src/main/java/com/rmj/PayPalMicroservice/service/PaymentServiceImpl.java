package com.rmj.PayPalMicroservice.service;

import java.time.LocalDateTime;
import java.util.List;

import com.rmj.PayPalMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.PayPalMicroservice.dto.TransactionCompletedDTO;
import com.rmj.PayPalMicroservice.model.FormField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rmj.PayPalMicroservice.service.TransactionService;
import com.rmj.PayPalMicroservice.model.Currency;
import com.rmj.PayPalMicroservice.model.Transaction;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Value("${frontend.url}")
	private String frontendUrl;

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
	public Long makeTransaction(Long merchantOrderId, double amount, Currency currency, LocalDateTime timestamp,
			String redirectUrl, String callbackUrl) {
		Transaction transaction = new Transaction(merchantOrderId, amount, currency, timestamp, redirectUrl, callbackUrl);
		transaction = transactionService.save(transaction);
		return transaction.getId();
	}

	@Override
	public FormFieldsForPaymentTypeDTO getFormFieldsForPaymentType() {
		List<FormField> formFields = formFieldService.getFormFields();
		return new FormFieldsForPaymentTypeDTO(formFields);
	}
	
	@Override
	public String pay(Long transactionId, String status) {
		Transaction transaction = transactionService.getTransaction(transactionId);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
		HttpEntity<TransactionCompletedDTO> httpEntity2 = new HttpEntity<TransactionCompletedDTO>(new TransactionCompletedDTO(transaction.getMerchantOrderId(), status), headers);
    	ResponseEntity<Void> responseEntity2 = restTemplate.exchange(transaction.getCallbackUrl(), HttpMethod.PUT, httpEntity2, Void.class);
		return transaction.getRedirectUrl();
	}
}
