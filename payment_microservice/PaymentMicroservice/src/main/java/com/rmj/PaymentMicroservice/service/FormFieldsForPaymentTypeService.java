package com.rmj.PaymentMicroservice.service;

import com.rmj.PaymentMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.PaymentMicroservice.model.FormFieldsForPaymentType;

import java.util.List;

public interface FormFieldsForPaymentTypeService {

	FormFieldsForPaymentType getFormFieldsForPaymentType(String paymentType);

	FormFieldsForPaymentTypeDTO getFormFieldsForPaymentTypeFromMicroservice(String paymentType);

	void save(FormFieldsForPaymentType formFieldsForPaymentType);
}
