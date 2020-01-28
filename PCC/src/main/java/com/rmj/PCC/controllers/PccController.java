package com.rmj.PCC.controllers;

import com.rmj.PCC.models.Bank;
import com.rmj.PCC.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public ResponseEntity Check(@RequestBody Long transactionId,@RequestBody LocalDateTime timeStamp,@RequestBody int bin)
    {
        List<Bank> bankList = bankService.getAllBanks();

        if(bankList.size()>0)
        {
            for(Bank b:bankList)
            {
                if(b.getBin() == bin)
                {
                	//redirect na instancu banke
                	
                	//napraviti nesto slicno ovom dole jer se odgovor vraca pcc pa pcc prosledjuje banci prodavca odgovor
                	
                	//ResponseEntity<String> responseEntity = restTemplate.exchange(bankUrl, HttpMethod.POST, httpEntity, String.class);
                    
                    //String status;
                    //if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody().equals("Transaction is acceptable!")) {
                   // 	status = "success";
                   // } else {
                   // 	status = "fail";
                   // }
                    
                   // HttpEntity<TransactionCompletedDTO> httpEntity2 = new HttpEntity<TransactionCompletedDTO>(new TransactionCompletedDTO(transaction.getMerchantOrderId(), status), headers);
                //	ResponseEntity<Void> responseEntity2 = restTemplate.exchange(transaction.getCallbackUrl(), HttpMethod.PUT, httpEntity2, Void.class);
                }
            }
        }
        else
            return ResponseEntity.status(HttpStatus.OK).body("Bank list is empty");

        return ResponseEntity.status(HttpStatus.OK).body("Bank list is empty");

    }

}
