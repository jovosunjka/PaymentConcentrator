package com.rmj.CardPaymentMicroservice.service;

import java.time.LocalDateTime;

import com.rmj.CardPaymentMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.CardPaymentMicroservice.model.Currency;

public interface PaymentService {
	
	String getFrontendUrl();

	Long makeTransaction(Long merchantOrderId, double amount, Currency currency, LocalDateTime timestamp,
			String redirectUrl, String callbackUrl);

	String pay(Long transactionId, int cardNumber, int pin, String cardHolder, String expirationDate);

    FormFieldsForPaymentTypeDTO getFormFieldsForPaymentType();
}
