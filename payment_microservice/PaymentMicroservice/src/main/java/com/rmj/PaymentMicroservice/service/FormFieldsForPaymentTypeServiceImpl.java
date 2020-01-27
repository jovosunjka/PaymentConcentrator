package com.rmj.PaymentMicroservice.service;


import com.rmj.PaymentMicroservice.dto.FormFieldsForPaymentTypeDTO;
import com.rmj.PaymentMicroservice.model.FormFieldsForPaymentType;
import com.rmj.PaymentMicroservice.repository.FormFieldsForPaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class FormFieldsForPaymentTypeServiceImpl implements FormFieldsForPaymentTypeService {

	@Value("${proxy-server.url}")
	private String proxyServerUrl;

	@Autowired
	private FormFieldsForPaymentTypeRepository formFieldsForPaymentTypeRepository;

	@Autowired
	private RestTemplate restTemplate;


	@Override
	public FormFieldsForPaymentType getFormFieldsForPaymentType(String paymentType) {
		return formFieldsForPaymentTypeRepository.findByPaymentType(paymentType)
				.orElseThrow(() -> new RuntimeException("FormFieldsForPaymentType (paymentType=".concat(paymentType).concat(") not found!")));
	}

	@Override
	public FormFieldsForPaymentTypeDTO getFormFieldsForPaymentTypeFromMicroservice(String paymentType) {
		String url = proxyServerUrl.concat("/").concat(paymentType).concat("/payment/form-fields-for-payment-type");
		ResponseEntity<FormFieldsForPaymentTypeDTO> responseEntity = restTemplate.getForEntity(url,
																					FormFieldsForPaymentTypeDTO.class);
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			throw new RuntimeException("Form fields for payment type (".concat(paymentType.concat(") from microservice not found")));
		}

		return responseEntity.getBody();
	}

	@Override
	public void save(FormFieldsForPaymentType formFieldsForPaymentType) {
		formFieldsForPaymentTypeRepository.save(formFieldsForPaymentType);
	}
}
