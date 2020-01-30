package com.rmj.BitcoinMicroservice.service;

import com.rmj.BitcoinMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.BitcoinMicroservice.models.Currency;

import java.time.LocalDateTime;

public interface PaymentService {

	String getFrontendUrl();

	Long makeTransaction(Long merchantOrderId, double amount, Currency currency, LocalDateTime timestamp,
						 String redirectUrl, String callbackUrl);

    FormFieldsForPaymentTypeDTO getFormFieldsForPaymentType();
}
