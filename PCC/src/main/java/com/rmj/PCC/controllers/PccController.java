package com.rmj.PCC.controllers;

import com.rmj.PCC.dto.BankDTO;
import com.rmj.PCC.dto.BankToPccDTO;
import com.rmj.PCC.dto.TransactionCompletedDTO;
import com.rmj.PCC.models.Bank;
import com.rmj.PCC.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/pcc")
public class PccController {
    @Autowired
    private BankService bankService;

    @RequestMapping(value = "/save-bank",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveBank(@RequestBody BankDTO bankDTO) {
        bankService.saveBank(bankDTO.getName(), bankDTO.getBin(), bankDTO.getRedirectUrl(), bankDTO.getTransactionCompletedUrl());
        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(value = "/check",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity Check(@RequestBody BankToPccDTO dto)
    {
    	bankService.makeTransaction(dto.getTransactionId(), dto.getTimeStamp(), dto.getAmount(), dto.getCurrency(),
                dto.getAcquirerBin(), dto.getUser());
    	return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/response",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity response(@RequestBody TransactionCompletedDTO transactionCompletedDTO)
    {
        try {
            bankService.sendTransactionCompletedDTO(transactionCompletedDTO);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);

    }
}
