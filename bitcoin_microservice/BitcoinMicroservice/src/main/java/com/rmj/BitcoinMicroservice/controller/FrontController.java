package com.rmj.BitcoinMicroservice.controller;

import com.rmj.BitcoinMicroservice.dto.PayDTO;
import com.rmj.BitcoinMicroservice.models.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.rmj.BitcoinMicroservice.service.PaymentService;
import com.rmj.BitcoinMicroservice.dto.RedirectUrlDTO;

import java.time.LocalDateTime;


@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class FrontController {
	
	@Autowired
	private PaymentService paymentService;


    @PreAuthorize("hasAuthority('PAY')")
    @RequestMapping(value = "/frontend-url", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO> getFrontendUrl(@RequestBody PayDTO payDTO)
    {
        Long transactionId = paymentService.makeTransaction(payDTO.getMerchantOrderId(), payDTO.getAmount(), payDTO.getCurrency(),
                payDTO.getTimestamp(), payDTO.getRedirectUrl(), payDTO.getCallbackUrl());
        String frontendUrl = paymentService.getFrontendUrl() + "/" + transactionId;
        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }
}
