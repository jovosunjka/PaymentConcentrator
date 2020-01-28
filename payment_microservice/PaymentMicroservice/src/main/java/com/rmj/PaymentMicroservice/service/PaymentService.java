package com.rmj.PaymentMicroservice.service;

import com.rmj.PaymentMicroservice.dto.PaymentTypeDTO;
import com.rmj.PaymentMicroservice.exception.NotFoundException;
import com.rmj.PaymentMicroservice.exception.UserNotFoundException;
import com.rmj.PaymentMicroservice.model.Currency;
import com.rmj.PaymentMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.PaymentMicroservice.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
	
    List<PaymentTypeDTO> getPaymentTypes();

	List<String> getPaymentTypeNames();

	Transaction pay(Long merchantOrderId, double amount, Currency currency, LocalDateTime timestamp, String redirectUrl,
			String callbackUrl);

	void saveChosenPayment(Long transactionId, String paymentType);

	String getMicroserviceFrontendUrl(Long transactionId, String paymentType) throws UserNotFoundException, NotFoundException;

	void transactionCompleted(long transactionId, String status);

	List<Transaction> getTransactions(String[] transactionIds);

	List<FormFieldsForPaymentTypeDTO> getFormFieldsForPaymentTypes();
}
