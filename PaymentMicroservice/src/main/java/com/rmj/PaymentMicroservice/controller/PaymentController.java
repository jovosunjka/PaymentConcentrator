package com.rmj.PaymentMicroservice.controller;


import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.rmj.PaymentMicroservice.dto.PaymentTypeDTO;
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


    @RequestMapping(value = "/pay", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity pay(@RequestBody Integer amount) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentTypeDTO>> getPaymentTypes() {
        List<PaymentTypeDTO> paymentTypes = paymentService.getPaymentTypes();
        return new ResponseEntity(paymentTypes, HttpStatus.CREATED);
    }
}
