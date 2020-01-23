package com.rmj.SEP.Banka.controller;

import com.rmj.SEP.Banka.models.BankAccount;
import com.rmj.SEP.Banka.repository.BankServiceInterface;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/bank")
public class BankController {
    @Autowired
    private BankServiceInterface serviceRepo;


    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity Check(@RequestBody BankAccount user)
    {
        List<BankAccount> accounts = new ArrayList<>();
        accounts = serviceRepo.findAll();

        if(accounts.size()>0)
        {
            for(int i = 0; i < accounts.size();i++)
            {
                if(accounts.get(i).getCardNumber() == user.getCardNumber() && accounts.get(i).getPin() == user.getPin())
                {
                    if(accounts.get(i).getAmount()>= user.getAmount())
                    {
                        return ResponseEntity.status(HttpStatus.OK).body("Transaction is acceptable!");
                    }
                    else
                    {
                        return ResponseEntity.status(HttpStatus.OK).body("User does not have enough assets");
                    }
                }
                else
                {
                    return ResponseEntity.status(HttpStatus.OK).body("Account does not exist!");
                }
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK).body("Account does not exist!");

        }

        return ResponseEntity.status(HttpStatus.OK).body("Account list is empty");

    }
}
