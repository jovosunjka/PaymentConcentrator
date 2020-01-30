package com.rmj.BitcoinMicroservice.service;

import com.rmj.BitcoinMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.BitcoinMicroservice.models.Currency;
import com.rmj.BitcoinMicroservice.models.FormField;
import com.rmj.BitcoinMicroservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Value("${frontend.url}")
	private String frontendUrl;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private FormFieldService formFieldService;

	
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
}
