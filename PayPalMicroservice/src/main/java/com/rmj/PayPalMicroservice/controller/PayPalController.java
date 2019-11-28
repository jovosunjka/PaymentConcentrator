package com.rmj.PayPalMicroservice.controller;


import com.netflix.discovery.EurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {

    @RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity pay(@RequestBody Integer amount) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/aaa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAaa() {
        return new ResponseEntity("aaa", HttpStatus.CREATED);
    }
}
