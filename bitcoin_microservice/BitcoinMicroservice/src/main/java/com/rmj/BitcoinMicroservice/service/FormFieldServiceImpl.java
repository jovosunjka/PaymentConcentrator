package com.rmj.BitcoinMicroservice.service;

import com.rmj.BitcoinMicroservice.models.FormField;
import com.rmj.BitcoinMicroservice.repository.FormFieldRepository;
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
