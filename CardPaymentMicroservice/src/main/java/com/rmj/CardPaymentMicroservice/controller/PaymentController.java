package com.rmj.CardPaymentMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rmj.CardPaymentMicroservice.dto.RedirectUrlDTO;
import com.rmj.CardPaymentMicroservice.service.PaymentService;

@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	
	@RequestMapping(value = "/frontend-url", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO> getFrontendUrl()
    {
		String frontendUrl = paymentService.getFrontendUrl();
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
	

}
