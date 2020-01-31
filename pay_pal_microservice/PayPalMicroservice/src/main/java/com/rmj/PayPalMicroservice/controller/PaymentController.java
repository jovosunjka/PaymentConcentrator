package com.rmj.PayPalMicroservice.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.rmj.PayPalMicroservice.dto.FormFieldsForPaymentTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.rmj.PayPalMicroservice.dto.PayDTO;
import com.rmj.PayPalMicroservice.dto.RedirectUrlDTO;
import com.rmj.PayPalMicroservice.model.PayPalResponse;
import com.rmj.PayPalMicroservice.repository.PayPalResponseRepository;
import com.rmj.PayPalMicroservice.repository.TransactionRepository;
import com.rmj.PayPalMicroservice.service.PaymentService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PayPalResponseRepository repository;


    @PreAuthorize("hasAuthority('PAY')")
	@RequestMapping(value = "/frontend-url", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RedirectUrlDTO> getFrontendUrl(@RequestBody PayDTO payDTO)
    {
		Long transactionId = paymentService.makeTransaction(payDTO.getMerchantOrderId(), payDTO.getAmount(), payDTO.getCurrency(),
														payDTO.getTimestamp(), payDTO.getRedirectUrl(), payDTO.getCallbackUrl());
		String frontendUrl = paymentService.getFrontendUrl() + "/" + transactionId + "/" + payDTO.getAmount() + "/" + payDTO.getCurrency();
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
	
    @RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody Integer amount)
    {
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(path = "/saveTransaction", method = RequestMethod.POST)
    public ResponseEntity<RedirectUrlDTO> saveTransaction(@RequestBody PayPalResponse executeTransaction)
    {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	
    	executeTransaction.setCreate_time(dateFormat.format(cal.getTime()).toString());
    	repository.save(executeTransaction);
    	String frontendUrl = paymentService.pay(executeTransaction.getIdPayment(), executeTransaction.getState());
    	System.out.println("Sacuvana " + executeTransaction.getState() + " paypal transakcija u bazu... url za redirect: " + frontendUrl);
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
    
    @RequestMapping(path = "/cancelTransaction", method = RequestMethod.POST)
    public ResponseEntity<RedirectUrlDTO> cancelTransaction(@RequestBody PayPalResponse executeTransaction)
    {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
    	
    	executeTransaction.setCreate_time(dateFormat.format(cal.getTime()).toString());
    	repository.save(executeTransaction);
    	String frontendUrl = paymentService.pay(executeTransaction.getIdPayment(), executeTransaction.getState());
    	System.out.println("Sacuvana neuspesna paypal transakcija u bazu");
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }

    @RequestMapping(path = "/getAllTransaction", method = RequestMethod.GET)
    public ResponseEntity getAllTransaction()
    {
    	
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/form-fields-for-payment-type", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormFieldsForPaymentTypeDTO> getFormFieldsForPaymentTypes() {
	    FormFieldsForPaymentTypeDTO formFieldsForPaymentTypeDTO = paymentService.getFormFieldsForPaymentType();
        return new ResponseEntity<FormFieldsForPaymentTypeDTO>(formFieldsForPaymentTypeDTO, HttpStatus.OK);
    }
}
