package com.rmj.PCC.controllers;

import com.rmj.PCC.models.Bank;
import com.rmj.PCC.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/pcc")
public class PccController {
    @Autowired
    private BankService bankService;

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public ResponseEntity Check(@RequestBody int bin)
    {
        List<Bank> bankList = bankService.getAllBanks();

        if(bankList.size()>0)
        {
            for(Bank b:bankList)
            {
                if(b.getBin() == bin)
                {

                }
            }
        }
        else
            return ResponseEntity.status(HttpStatus.OK).body("Bank list is empty");

        return ResponseEntity.status(HttpStatus.OK).body("Bank list is empty");

    }

}
