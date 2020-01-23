package com.rmj.PaymentMicroservice.service;

import com.rmj.PaymentMicroservice.model.FormFieldsForPaymentType;

public interface FormFieldsForPaymentTypeService {

	FormFieldsForPaymentType getFormFieldsForPaymentType(String paymentType);
}
