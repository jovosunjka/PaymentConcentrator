package com.rmj.PCC.controllers;

import com.rmj.PCC.dto.BankToPccDTO;
import com.rmj.PCC.models.Bank;
import com.rmj.PCC.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/pcc")
public class PccController {
    @Autowired
    private BankService bankService;
        
    @RequestMapping(value = "/check",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity Check(@RequestBody BankToPccDTO dto)
    {
    	try
    	{
    		Bank foundBank = bankService.getBank(dto.getBank().getBin());
    	}
    	catch(RuntimeException e)
    	{
    		System.out.println(e.getMessage());
    		return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
    	}
        
    	String response = bankService.redirectToBank(dto);
    	return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
