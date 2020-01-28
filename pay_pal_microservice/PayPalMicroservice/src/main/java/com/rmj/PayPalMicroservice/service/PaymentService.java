package com.rmj.PayPalMicroservice.service;

import java.time.LocalDateTime;

import com.rmj.PayPalMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.PayPalMicroservice.model.Currency;

public interface PaymentService {
	
	String getFrontendUrl();
	
	Long makeTransaction(Long merchantOrderId, double amount, Currency currency, LocalDateTime timestamp,
			String redirectUrl, String callbackUrl);

    FormFieldsForPaymentTypeDTO getFormFieldsForPaymentType();
}
