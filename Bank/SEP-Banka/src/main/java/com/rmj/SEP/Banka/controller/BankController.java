package com.rmj.SEP.Banka.controller;

import com.rmj.SEP.Banka.Services.BankService;
import com.rmj.SEP.Banka.dto.BankAccountDTO;
import com.rmj.SEP.Banka.dto.RedirectUrlDTO;
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
    
    @Autowired
    private BankService bankService;


    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity Check(@RequestBody BankAccountDTO user,@RequestParam("transactionId") Long transactionId)
    {
    	//dodati dodavanje novca na racun kad se izvuce racun prodavca
    	
    	int cardBin = (int)(user.getCardNumber()/100000);
    	boolean response = bankService.checkBin(cardBin);
    	
    	if(response == true)
    	{
    		boolean found = false;
            BankAccount account = new BankAccount();

            try {
            	account = bankService.getBank(user.getCardNumber());
            	found = true;
            	
            }catch(RuntimeException e)
            {
        		System.out.println("Error BankController SEP Banka / check: " + e.getMessage());
            }
            
            if(found)
        	{
        		LocalDate date = LocalDate.now();
        		String[] dateSplit = user.getExpDate().split("/");
        		int year = Integer.parseInt(dateSplit[1]);
        		int month = Integer.parseInt(dateSplit[0]);
            
        		int aa = date.getYear()%100;
        		
        		if(year > aa)
            	{
            		if(account.getAmount() >= user.getAmount())
                    {
                    	double pom = 0;
                    	pom = account.getAmount() - user.getAmount();
                    	
                    	account.setAmount(pom);
                    	
                    	serviceRepo.save(account);
                    	
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
            			if(account.getAmount()>= user.getAmount())
                        {
            				double pom = 0;
                        	pom = account.getAmount() - user.getAmount();
                        	
                        	account.setAmount(pom);
                        	
                        	serviceRepo.save(account);
                        	
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
        	}
            else
    			return ResponseEntity.status(HttpStatus.OK).body("User did not found");

    	}
    	else
    	{
    		String resp = bankService.redirectToPcc(transactionId,LocalDateTime.now(),user,cardBin);
    		
    		if(resp.equals("success"))
                return ResponseEntity.status(HttpStatus.OK).body("Transaction is completed!");
    		else
                return ResponseEntity.status(HttpStatus.OK).body("Transaction is not completed!");

    	}	
    	      

        return ResponseEntity.status(HttpStatus.OK).body("Account list is empty");

    }
    
    @RequestMapping(value="/request",method = RequestMethod.POST)
    public ResponseEntity Request(@RequestBody BankAccountDTO user)
    {
    	boolean found = false;
    	BankAccount account = new BankAccount();
    	try {
    		account = bankService.getBank(user.getCardNumber());
    		found = true;
    	}
    	catch(RuntimeException e)
    	{
    		System.out.println("Error BankController SEP Banka / request: " + e.getMessage());
    	}
    	
    	
    	if(found)
    	{
    		LocalDate date = LocalDate.now();
    		String[] dateSplit = user.getExpDate().split("/");
    		int year = Integer.parseInt(dateSplit[1]);
    		int month = Integer.parseInt(dateSplit[0]);
        
    		int aa = date.getYear()%100;
    		
    		if(year > aa)
        	{
        		if(account.getAmount() >= user.getAmount())
                {
                	double pom = 0;
                	pom = account.getAmount() - user.getAmount();
                	
                	account.setAmount(pom);
                	
                	serviceRepo.save(account);
                	
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
        			if(account.getAmount()>= user.getAmount())
                    {
        				double pom = 0;
                    	pom = account.getAmount() - user.getAmount();
                    	
                    	account.setAmount(pom);
                    	
                    	serviceRepo.save(account);
                    	
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
    	}  
    	else
			return ResponseEntity.status(HttpStatus.OK).body("User did not found");
    	
    	
    	
    	return ResponseEntity.status(HttpStatus.OK).body("Request failed");
    	
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
