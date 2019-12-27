package com.rmj.CardPaymentMicroservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Value("${frontend.url}")
	private String frontendUrl;
	
	
	@Override
	public String getFrontendUrl() {
		return frontendUrl;
	}
}
