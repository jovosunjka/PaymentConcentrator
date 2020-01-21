package com.rmj.BitcoinMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rmj.BitcoinMicroservice.service.PaymentService;
import models.RedirectUrlDTO;



@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class FrontController {
	
	@Autowired
	private PaymentService paymentService;	

	@RequestMapping(value = "/frontend-url", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO> getFrontendUrl()
    {
		String frontendUrl = paymentService.getFrontendUrl();
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }

}
