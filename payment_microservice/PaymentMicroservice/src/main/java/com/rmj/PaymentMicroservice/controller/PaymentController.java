package com.rmj.PaymentMicroservice.controller;


import com.rmj.PaymentMicroservice.dto.*;
import com.rmj.PaymentMicroservice.exception.NotFoundException;
import com.rmj.PaymentMicroservice.exception.RequestTimeoutException;
import com.rmj.PaymentMicroservice.exception.UserNotFoundException;
import com.rmj.PaymentMicroservice.model.Transaction;
import com.rmj.PaymentMicroservice.model.User;
import com.rmj.PaymentMicroservice.service.PaymentService;
import com.rmj.PaymentMicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;


    //@PreAuthorize("hasAuthority('PAY')")
    @RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
    															produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionIdDTO> pay(@RequestBody PayDTO payDTO) {
    	Transaction transaction = paymentService.pay(payDTO.getMerchantOrderId(), payDTO.getAmount(), payDTO.getCurrency(),
    			payDTO.getTimestamp(), payDTO.getRedirectUrl(), payDTO.getCallbackUrl());
        return new ResponseEntity<TransactionIdDTO>(new TransactionIdDTO(transaction.getId()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('PAY')")
    @RequestMapping(value = "/types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaymentTypeDTO>> getPaymentTypes() {
        User loggedUser = null;
        try {
            loggedUser = userService.getLoggedUser();
        } catch (UserNotFoundException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        List<PaymentTypeDTO> paymentTypes = paymentService.getCurrentlyActivatedPaymentTypes(loggedUser.getAccounts());
        return new ResponseEntity<List<PaymentTypeDTO>>(paymentTypes, HttpStatus.OK);
    }

    @RequestMapping(value = "/form-fields-for-payment-types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormFieldsForPaymentTypesDTO> getFormFieldsForPaymentTypes() {
        List<FormFieldsForPaymentTypeDTO> formFieldsForPaymentTypeDTOs = paymentService.getFormFieldsForPaymentTypes();
        FormFieldsForPaymentTypesDTO retValue = new FormFieldsForPaymentTypesDTO(formFieldsForPaymentTypeDTOs);
        return new ResponseEntity<FormFieldsForPaymentTypesDTO>(retValue, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('PAY')")
    @RequestMapping(value = "/choose-payment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity choosePayment(@RequestParam("transactionId") Long transactionId,
    															@RequestParam("paymentType") String paymentType) {
        User loggedUser = null;
        try {
            loggedUser = userService.getLoggedUser();
        } catch (UserNotFoundException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        if (paymentType == null) {
            return new ResponseEntity("paymentType == null".concat(paymentType).concat(" accpunt"),
                    HttpStatus.BAD_REQUEST);
        }

        paymentType = paymentType.trim();

        if (paymentType.equals("")) {
            return new ResponseEntity("paymentType is empty string", HttpStatus.BAD_REQUEST);
        }

    	paymentType = paymentType.toLowerCase();

        if (!paymentService.loggedUserHasPaymentType(loggedUser.getAccounts(), paymentType)) {
            return new ResponseEntity("The logged user has not ".concat(paymentType).concat(" account"),
                    HttpStatus.BAD_REQUEST);
        }

    	paymentService.saveChosenPayment(transactionId, paymentType);
        String frontendUrl = null;
        try {
            frontendUrl = paymentService.getMicroserviceFrontendUrl(transactionId, paymentType);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RequestTimeoutException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
        }

        return new ResponseEntity<RedirectUrlDTO>(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);
    }

    @RequestMapping(value = "/transaction-completed", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity transactionCompleted(@RequestBody TransactionCompletedDTO transactionCompletedDTO) {
    	paymentService.transactionCompleted(transactionCompletedDTO.getMerchantOrderId(), transactionCompletedDTO.getStatus());
    	return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTOs> getTransactions(@RequestParam("transactionIds") String transactionIdsStr) {
    	String[] transactionIds = transactionIdsStr.split(",");
    	List<Transaction> transactions = paymentService.getTransactions(transactionIds);
    	List<TransactionDTO> transactionDTOs = transactions.stream()
    				.map(t -> new TransactionDTO(t))
    				.collect(Collectors.toList());
    	return new ResponseEntity<TransactionDTOs>(new TransactionDTOs(transactionDTOs), HttpStatus.OK);
    }
    
    @RequestMapping(value ="/paper-plans", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity paperPlans(@RequestBody ProductDTO productDTO) {
        
        try {
			paymentService.paperPlans(productDTO);
			return new ResponseEntity(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
			
    }
}
