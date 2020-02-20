package com.rmj.NewPaymentMicroservice.service;



import com.rmj.NewPaymentMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.NewPaymentMicroservice.model.TransactionStatus;

import java.time.LocalDateTime;

public interface PaymentService {


	void transactionCompleted(Long merchantOrderId, TransactionStatus status);

	FormFieldsForPaymentTypeDTO getFormFieldsForPaymentType();
}
