package com.rmj.PayPalMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rmj.PayPalMicroservice.dto.RedirectUrlDTO;
import com.rmj.PayPalMicroservice.dto.transactionDTO;
import com.rmj.PayPalMicroservice.repository.TransactionRepository;
import com.rmj.PayPalMicroservice.service.PaymentService;

@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private TransactionRepository repository;

	
	@RequestMapping(value = "/frontend-url", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO> getFrontendUrl()
    {
		String frontendUrl = paymentService.getFrontendUrl();
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
	
    @RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody Integer amount)
    {
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(path = "/saveTransaction", method = RequestMethod.POST)
    public ResponseEntity saveTransaction(@RequestBody transactionDTO executeTransaction)
    {
    	repository.save(executeTransaction);
    	System.out.println("sacuvana paypal transakcija u bazu");
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/getAllTransaction", method = RequestMethod.GET)
    public ResponseEntity getAllTransaction()
    {
    	
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
}
