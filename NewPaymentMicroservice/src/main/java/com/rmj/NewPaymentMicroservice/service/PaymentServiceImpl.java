package com.rmj.NewPaymentMicroservice.service;


import com.rmj.NewPaymentMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.NewPaymentMicroservice.dto.TransactionCompletedDTO;
import com.rmj.NewPaymentMicroservice.model.FormField;
import com.rmj.NewPaymentMicroservice.model.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private RestTemplate restTemplate;


    @Override
    public void transactionCompleted(Long merchantOrderId, TransactionStatus status) {
		System.out.println("PRODUCT IS PAYED!!!");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        TransactionCompletedDTO transactionCompletedDTO = new TransactionCompletedDTO(merchantOrderId, status);
        HttpEntity<TransactionCompletedDTO> httpEntity =
								new HttpEntity<TransactionCompletedDTO>(transactionCompletedDTO, headers);
        String url = "https://localhost:8083/payment/transaction-completed";
        restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Void.class);
    }

	@Override
	public FormFieldsForPaymentTypeDTO getFormFieldsForPaymentType() {
		List<FormField> formFields = new ArrayList<FormField>();
		formFields.add(new FormField("field_1", "string", true));
		formFields.add(new FormField("field_2", "string", true));
		formFields.add(new FormField("field_3", "string", true));
		return new FormFieldsForPaymentTypeDTO(formFields);
	}
}
