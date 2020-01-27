package com.rmj.PayPalMicroservice.service;

import com.rmj.PayPalMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.PayPalMicroservice.model.Currency;
import com.rmj.PayPalMicroservice.model.FormField;
import com.rmj.PayPalMicroservice.model.Transaction;
import com.rmj.PayPalMicroservice.repository.FormFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FormFieldServiceImpl implements FormFieldService {

	@Autowired
	private FormFieldRepository formFieldRepository;


	@Override
	public List<FormField> getFormFields() {
		return formFieldRepository.findAll();
	}
}
