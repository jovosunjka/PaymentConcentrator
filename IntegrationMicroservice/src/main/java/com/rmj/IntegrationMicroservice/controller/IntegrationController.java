package com.rmj.IntegrationMicroservice.controller;

import com.rmj.IntegrationMicroservice.model.Client;
import com.rmj.IntegrationMicroservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/integration")
public class IntegrationController {

    @Autowired
    private ClientService clientService;


    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody Client client)
    {
        clientService.add(client);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/supported-payments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getSupportedPayments()
    {
        List<String> supportedPayments = new ArrayList<String>();
        supportedPayments.add("card-payment");
        supportedPayments.add("paypal");
        supportedPayments.add("bitcoin");

        return new ResponseEntity(supportedPayments, HttpStatus.OK);
    }
}
