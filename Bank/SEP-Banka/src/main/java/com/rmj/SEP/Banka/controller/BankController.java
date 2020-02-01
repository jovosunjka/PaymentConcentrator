package com.rmj.SEP.Banka.controller;

import com.rmj.SEP.Banka.Services.BankService;
import com.rmj.SEP.Banka.dto.*;
import com.rmj.SEP.Banka.models.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@CrossOrigin
@RestController
@RequestMapping(value = "/bank")
public class BankController {
    @Autowired
    private BankService bankService;

	@RequestMapping(value = "/check", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                                                                    produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity Check(@RequestBody PayDTO payDTO)
	{
        String frontendUrl = bankService.makeTransaction(payDTO.getMerchantId(), payDTO.getMerchantPassword(),
				payDTO.getMerchantOrderId(), payDTO.getAmount(), payDTO.getCurrency(), payDTO.getMerchantTimestamp(),
				payDTO.getRedirectUrl(), payDTO.getCallbackUrl());

		return new ResponseEntity(new RedirectUrlDTO(frontendUrl), HttpStatus.OK);

	}

    @RequestMapping(value = "/pay/{transactionId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity pay(@PathVariable("transactionId") Long transactionId, @RequestBody BankAccountDTO bankAccountDTO)
    {
    	bankService.pay(transactionId, bankAccountDTO);
        return new ResponseEntity(HttpStatus.OK);

    }
    
    @RequestMapping(value="/request",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity Request(@RequestBody PccToBankDTO pccToBankDTO)
    {
		String callbackUrl = "https://localhost:8088/api/pcc/response";
    	bankService.request(pccToBankDTO.getAcquirerOrderId(), pccToBankDTO.getAmount(), pccToBankDTO.getCurrency(),
				pccToBankDTO.getAcquirerTimeStamp(), pccToBankDTO.getUser(), callbackUrl, null);

    	return new ResponseEntity(HttpStatus.OK);

    }
    
    @RequestMapping(value = "/response", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity Response(@RequestBody TransactionCompletedDTO transactionCompletedDTO)
    {
    	bankService.response(transactionCompletedDTO);

    	return ResponseEntity.status(HttpStatus.OK).body("Transaction completed");
    }
    
    
}
