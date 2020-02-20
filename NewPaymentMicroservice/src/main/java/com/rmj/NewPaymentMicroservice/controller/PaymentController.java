package com.rmj.NewPaymentMicroservice.controller;


import com.rmj.NewPaymentMicroservice.dto.PayDTO;
import com.rmj.NewPaymentMicroservice.dto.RedirectUrlDTO;
import com.rmj.NewPaymentMicroservice.model.TransactionStatus;
import com.rmj.NewPaymentMicroservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	//@PreAuthorize("hasAuthority('PAY')")
	@RequestMapping(value = "/frontend-url", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
																			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO> getFrontendUrl(@RequestBody PayDTO payDTO)
    {
		System.out.println("PAYING...");
		paymentService.transactionCompleted(payDTO.getMerchantOrderId(), TransactionStatus.SUCCESS);

        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(null), HttpStatus.OK);
    }

}
