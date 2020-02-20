package com.rmj.CardPaymentMicroservice.service;

import java.time.LocalDateTime;

import com.rmj.CardPaymentMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.CardPaymentMicroservice.dto.RedirectUrlDTO;
import com.rmj.CardPaymentMicroservice.model.Currency;
import com.rmj.CardPaymentMicroservice.model.TransactionStatus;

public interface PaymentService {
	
	String getFrontendUrl();

	RedirectUrlDTO makeTransaction(String merchantId, String merchantPassword, Long merchantOrderId, double amount, Currency currency, LocalDateTime timestamp,
								   String redirectUrl, String callbackUrl);

	String pay(Long transactionId, int cardNumber, int pin, String cardHolder, String expirationDate);

    FormFieldsForPaymentTypeDTO getFormFieldsForPaymentType();

	void transactionCompleted(Long transactionId, Long acquirerOrderId, LocalDateTime acquirerTimeStamp,
							  TransactionStatus status);
}
