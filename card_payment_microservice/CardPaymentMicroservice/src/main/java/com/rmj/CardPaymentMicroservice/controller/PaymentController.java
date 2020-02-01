package com.rmj.CardPaymentMicroservice.controller;

import com.rmj.CardPaymentMicroservice.dto.*;
import com.rmj.CardPaymentMicroservice.model.Property;
import com.rmj.CardPaymentMicroservice.model.User;
import com.rmj.CardPaymentMicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.rmj.CardPaymentMicroservice.service.PaymentService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private UserService userService;


	@PreAuthorize("hasAuthority('PAY')")
	@RequestMapping(value = "/frontend-url", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
																			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RedirectUrlDTO> getFrontendUrl(@RequestBody PayDTO payDTO)
    {
		User loggedUser = null;
		try {
			loggedUser = userService.getLoggedUser();
		} catch (Exception e) {
			new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}

		List<Property> properties = loggedUser.getProperties();
		String merchantId = properties.stream()
								.filter(prop -> prop.getIdentifier().equals("MERCAHNT_ID"))
								.map(prop -> prop.getValue())
								.findFirst().get();
		String merchantPassword = properties.stream()
				.filter(prop -> prop.getIdentifier().equals("MERCHANT_PASSWORD"))
				.map(prop -> prop.getValue())
				.findFirst().get();

		RedirectUrlDTO redirectUrlDTO = paymentService.makeTransaction(merchantId, merchantPassword, payDTO.getMerchantOrderId(), payDTO.getAmount(), payDTO.getCurrency(),
														payDTO.getTimestamp(), payDTO.getRedirectUrl(), payDTO.getCallbackUrl());
        return new ResponseEntity<RedirectUrlDTO>(redirectUrlDTO, HttpStatus.OK);
    }
	
	
	/*@RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
												produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RedirectUrlDTO> pay(@RequestParam("transactionId") Long transactionId, 
														@RequestBody CardNumberAndPinDTO cardNumberAndPinDTO)
	{
	
	    String frontendUrl = paymentService.pay(transactionId, cardNumberAndPinDTO.getCardNumber(), cardNumberAndPinDTO.getSecurityCode(),cardNumberAndPinDTO.getCardHolder(),cardNumberAndPinDTO.getExpirationDate());
	    return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
	}*/

	@RequestMapping(value = "/transaction-completed", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity transactionCompleted(@RequestBody TransactionCompletedFromBankDTO transactionCompletedDTO) {
		paymentService.transactionCompleted(transactionCompletedDTO.getMerchantOrderId(), transactionCompletedDTO.getAcquirerOrderId(), transactionCompletedDTO.getAcquirerTimeStamp(), transactionCompletedDTO.getStatus());
		return new ResponseEntity(HttpStatus.OK);
	}

    @RequestMapping(value = "/form-fields-for-payment-type", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormFieldsForPaymentTypeDTO> getFormFieldsForPaymentTypes() {
        FormFieldsForPaymentTypeDTO formFieldsForPaymentTypeDTO = paymentService.getFormFieldsForPaymentType();
        return new ResponseEntity<FormFieldsForPaymentTypeDTO>(formFieldsForPaymentTypeDTO, HttpStatus.OK);
    }

}
