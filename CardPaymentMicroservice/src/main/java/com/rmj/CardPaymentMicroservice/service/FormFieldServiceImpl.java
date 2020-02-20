package com.rmj.CardPaymentMicroservice.service;

import com.rmj.CardPaymentMicroservice.model.FormField;
import com.rmj.CardPaymentMicroservice.repository.FormFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
