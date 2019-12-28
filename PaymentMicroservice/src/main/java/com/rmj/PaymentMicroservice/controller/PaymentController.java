package com.rmj.PaymentMicroservice.controller;


import com.rmj.PaymentMicroservice.dto.PayDTO;
import com.rmj.PaymentMicroservice.dto.PaymentTypeDTO;
import com.rmj.PaymentMicroservice.dto.RedirectUrlDTO;
import com.rmj.PaymentMicroservice.dto.TransactionDTO;
import com.rmj.PaymentMicroservice.model.Transaction;
import com.rmj.PaymentMicroservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    //@Autowired
    //private DiscoveryClient eurekaClient;

    @Autowired
    private PaymentService paymentService;


    @RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
    															produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> pay(@RequestBody PayDTO payDTO) {
    	Transaction transaction = paymentService.pay(payDTO.getMerchantOrderId(), payDTO.getAmount(), payDTO.getCurrency(),
    			payDTO.getTimestamp(), payDTO.getRedirectUrl(), payDTO.getCallbackUrl());
        return new ResponseEntity<TransactionDTO>(new TransactionDTO(transaction.getId()), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentTypeDTO>> getPaymentTypes() {
        List<PaymentTypeDTO> paymentTypes = paymentService.getPaymentTypes();
        return new ResponseEntity<List<PaymentTypeDTO>>(paymentTypes, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/choose-payment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO> choosePayment(@RequestParam("transactionId") Long transactionId,
    															@RequestParam("paymentType") String paymentType) {
    	paymentType = paymentType.toLowerCase();
    	String frontendUrl = "";
    	if(paymentType.equalsIgnoreCase("pt_paypal-microservice")) {
    		paymentService.saveChosenPayment(transactionId, paymentType);
    		frontendUrl = paymentService.getMicroserviceFrontendUrl(paymentType);
    	}
    	else {
    		frontendUrl = "https://localhost:4206";    		
    	}
    	return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
}
