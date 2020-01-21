package com.rmj.CardPaymentMicroservice.service;

import java.time.LocalDateTime;

import com.rmj.CardPaymentMicroservice.dto.BankAccountDTO;
import com.rmj.CardPaymentMicroservice.model.Currency;

public interface PaymentService {
	
	String getFrontendUrl();

	Long makeTransaction(Long merchantOrderId, double amount, Currency currency, LocalDateTime timestamp,
			String redirectUrl, String callbackUrl);

	String pay(Long transactionId, BankAccountDTO bankAccountDTO);
}
