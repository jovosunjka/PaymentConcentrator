package com.rmj.PCC.services;

import com.rmj.PCC.dto.BankAccountDTO;
import com.rmj.PCC.dto.BankToPccDTO;
import com.rmj.PCC.models.Bank;
import com.rmj.PCC.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;
    
    @Autowired
	private RestTemplate restTemplate;

    @Override
    public List<Bank> getAllBanks() {
        List<Bank> banks = new ArrayList<>();
        banks = bankRepository.findAll();
        return banks;
    }

	@Override
	public void AddNewBank(Bank newBank) {
		bankRepository.save(newBank);
	}

	@Override
	public String redirectToBank(BankToPccDTO bankToPccDTO) {
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        BankAccountDTO bankAccountDTO = bankToPccDTO.getUser();
        HttpEntity<BankAccountDTO> httpEntity = new HttpEntity<BankAccountDTO>(bankAccountDTO,headers);
        
        String bankUrl = bankToPccDTO.getBank().getRedirectUrl();
        ResponseEntity<String> responseEntity = restTemplate.exchange(bankUrl,HttpMethod.POST,httpEntity,String.class);
        
        String status;
        if(responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody().equals("Transaction is acceptable!"))
        	status = "success";
        else
        	status = "fail";
		
		return status;
	}

	@Override
	public Bank getBank(int bin) {
		return bankRepository.findByBin(bin)
				.orElseThrow(()-> new RuntimeException("Bank (bin = "+ bin +") did not found"));
	}
    
    
}
