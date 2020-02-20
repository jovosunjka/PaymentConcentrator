package com.rmj.PaymentMicroservice.service;

import com.rmj.PaymentMicroservice.dto.PaymentTypeDTO;
import com.rmj.PaymentMicroservice.dto.PlanDTO;
import com.rmj.PaymentMicroservice.dto.ProductDTO;
import com.rmj.PaymentMicroservice.exception.NotFoundException;
import com.rmj.PaymentMicroservice.exception.RequestTimeoutException;
import com.rmj.PaymentMicroservice.exception.UserNotFoundException;
import com.rmj.PaymentMicroservice.model.Currency;
import com.rmj.PaymentMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.PaymentMicroservice.model.PaymentAccount;
import com.rmj.PaymentMicroservice.model.Transaction;
import com.rmj.PaymentMicroservice.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
	
    List<PaymentTypeDTO> getCurrentlyActivatedPaymentTypes(List<PaymentAccount> accounts);

	List<String> getPaymentTypeNames();

	Transaction pay(Long merchantOrderId, double amount, Currency currency, LocalDateTime timestamp, String redirectUrl,
			String callbackUrl);

	void saveChosenPayment(Long transactionId, String paymentType);

	String getMicroserviceFrontendUrl(Long transactionId, String paymentType) throws NotFoundException, RequestTimeoutException;

	void transactionCompleted(long transactionId, String status);

	List<Transaction> getTransactions(String[] transactionIds);

	List<FormFieldsForPaymentTypeDTO> getFormFieldsForPaymentTypes();

	boolean loggedUserHasPaymentType(List<PaymentAccount> accounts, String paymentType);

	void paperPlans(ProductDTO productDTO);
}
