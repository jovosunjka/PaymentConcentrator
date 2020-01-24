package com.rmj.CardPaymentMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rmj.CardPaymentMicroservice.dto.BankAccountDTO;
import com.rmj.CardPaymentMicroservice.dto.CardNumberAndPinDTO;
import com.rmj.CardPaymentMicroservice.dto.PayDTO;
import com.rmj.CardPaymentMicroservice.dto.RedirectUrlDTO;
import com.rmj.CardPaymentMicroservice.service.PaymentService;

@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	
	@RequestMapping(value = "/frontend-url", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
																			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO> getFrontendUrl(@RequestBody PayDTO payDTO)
    {
		Long transactionId = paymentService.makeTransaction(payDTO.getMerchantOrderId(), payDTO.getAmount(), payDTO.getCurrency(),
														payDTO.getTimestamp(), payDTO.getRedirectUrl(), payDTO.getCallbackUrl());
		Double amount = payDTO.getAmount();
		String frontendUrl = paymentService.getFrontendUrl() + "/" + transactionId + "/" + amount;
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
												produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RedirectUrlDTO> pay(@RequestParam("transactionId") Long transactionId, 
														@RequestBody CardNumberAndPinDTO cardNumberAndPinDTO)
	{
	
	String frontendUrl = paymentService.pay(transactionId, cardNumberAndPinDTO.getCardNumber(), cardNumberAndPinDTO.getPin());
	return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
	}
	

}
