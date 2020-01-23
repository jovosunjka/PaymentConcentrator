package com.rmj.PaymentMicroservice.service;


import com.rmj.PaymentMicroservice.model.FormFieldsForPaymentType;
import com.rmj.PaymentMicroservice.repository.FormFieldsForPaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FormFieldsForPaymentTypeServiceImpl implements FormFieldsForPaymentTypeService {

	@Autowired
	private FormFieldsForPaymentTypeRepository formFieldsForPaymentTypeRepository;


	@Override
	public FormFieldsForPaymentType getFormFieldsForPaymentType(String paymentType) {
		return formFieldsForPaymentTypeRepository.findByPaymentType(paymentType)
				.orElseThrow(() -> new RuntimeException("FormFieldsForPaymentType (paymentType=".concat(paymentType).concat(") not found!")));
	}
}
