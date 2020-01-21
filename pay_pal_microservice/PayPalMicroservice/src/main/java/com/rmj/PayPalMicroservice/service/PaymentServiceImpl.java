package com.rmj.PayPalMicroservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
}
