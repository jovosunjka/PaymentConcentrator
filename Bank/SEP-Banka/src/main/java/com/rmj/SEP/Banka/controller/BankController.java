package com.rmj.SEP.Banka.controller;

import com.rmj.SEP.Banka.dto.BankAccountDTO;
import com.rmj.SEP.Banka.models.BankAccount;
import com.rmj.SEP.Banka.repository.BankServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/bank")
public class BankController {
    @Autowired
    private BankServiceInterface serviceRepo;


    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity Check(@RequestBody BankAccountDTO user)
    {
        List<BankAccount> accounts = new ArrayList<>();
        accounts = serviceRepo.findAll();
        LocalDate date = LocalDate.now();
        String[] dateSplit = user.getExpDate().split("/");
        int year = Integer.parseInt(dateSplit[1]);
        int month = Integer.parseInt(dateSplit[0]);
        
        int aa = date.getYear()%100;

        if(accounts.size()>0)
        {
            for(int i = 0; i < accounts.size();i++)
            {
                if(accounts.get(i).getCardNumber() == user.getCardNumber() && accounts.get(i).getSecurityCode() == user.getSecurityCode() && user.getCardHolder().equals(accounts.get(i).getCardHolder()))
                {
                	if(year > aa)
                	{
                		if(accounts.get(i).getAmount()>= user.getAmount())
                        {
                        	double pom = 0;
                        	
                        	pom = accounts.get(i).getAmount() - user.getAmount();
                        	
                        	accounts.get(i).setAmount(pom);
                        	
                        	serviceRepo.save(accounts.get(i));
                        	
                            return ResponseEntity.status(HttpStatus.OK).body("Transaction is acceptable!");
                        }
                        else
                        {
                            return ResponseEntity.status(HttpStatus.OK).body("User does not have enough assets");
                        }   
                	}
                	else if(year == aa)
                	{
                		if(month > date.getMonthValue())
                		{
                			if(accounts.get(i).getAmount()>= user.getAmount())
                            {
                            	double pom = 0;
                            	
                            	pom = accounts.get(i).getAmount() - user.getAmount();
                            	
                            	accounts.get(i).setAmount(pom);
                            	
                            	serviceRepo.save(accounts.get(i));
                            	
                                return ResponseEntity.status(HttpStatus.OK).body("Transaction is acceptable!");
                            }
                            else
                            {
                                return ResponseEntity.status(HttpStatus.OK).body("User does not have enough assets");
                            }   
                		}
                		else
                			return ResponseEntity.status(HttpStatus.OK).body("Users card has expired");
                	}
                	else
            			return ResponseEntity.status(HttpStatus.OK).body("Users card has expired");
                		                 
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
    
    @RequestMapping(value = "/response", method = RequestMethod.POST)
    public ResponseEntity Response(@RequestBody Long merchaintId, Long acquirerId, LocalDateTime acquirerTimestamp, Long paymentId)
    {
    	
    	List<BankAccount> accounts = serviceRepo.findAll();
    	
    	for(BankAccount a: accounts)
    	{
    		if(a.getAccountNumber() == merchaintId)
    		{
    			//dodati deo gde se prodavcu dodaje novac na racun i kreirati novu transakciju
    		}
    	}
    	
    	
    	return ResponseEntity.status(HttpStatus.OK).body("Transaction completed");
    }
}
