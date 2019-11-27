package com.rmj.PayPalMicroservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {

    @RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody Integer amount)
    {
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
