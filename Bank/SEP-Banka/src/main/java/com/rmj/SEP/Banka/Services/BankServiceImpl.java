package com.rmj.SEP.Banka.Services;

import com.rmj.SEP.Banka.dto.BankAccountDTO;
import com.rmj.SEP.Banka.dto.BankToPccDTO;
import com.rmj.SEP.Banka.exceptions.AlredyExistException;
import com.rmj.SEP.Banka.models.BankAccount;
import com.rmj.SEP.Banka.repository.BankServiceInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankServiceInterface serviceRepo;
    
    @Value("${bin}")
    private int bankBin;
    
    @Value("${redirect-url}")
    private String redirectUrl;
    
    @Value("${response-url}")
    private String responseUrl;
    
    @Autowired
	private RestTemplate restTemplate;
    
    

    @EventListener(ApplicationReadyEvent.class)
    public void OnStart()
    {
        filter();
    }


	@Override
	public boolean checkBin(int cardBin) {
		if(cardBin == bankBin)
			return true;
		else
			return false;
	}

	@Override
	public String redirectToPcc(Long transactionId, LocalDateTime timeStamp, BankAccountDTO user,int bin) {
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        BankToPccDTO bankToPccDTO = new BankToPccDTO(transactionId,timeStamp,user,bin);
        HttpEntity<BankToPccDTO> httpEntity = new HttpEntity<BankToPccDTO>(bankToPccDTO,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(redirectUrl,HttpMethod.POST,httpEntity,String.class);
        
		return null;
	}

	@Override
	public BankAccount getBank(Long cardNumber) {
		return serviceRepo.findByCardNumber(cardNumber)
				.orElseThrow(()-> new RuntimeException("Bank account with card number " + cardNumber + " did not found"));
	}


	@Override
	public void filter() {
		
		//NE ZNAM DA LI POSTOJI PRAKTICNIJE RESENJE AKO POSTOJI JOVO MENJAJ
		List<BankAccount> accounts = serviceRepo.findAll();
		
		for(BankAccount a : accounts)
		{
			int accBin = (int)a.getCardNumber()/100000;
			if(accBin != bankBin)
				serviceRepo.delete(a);	
		}
	}
}
