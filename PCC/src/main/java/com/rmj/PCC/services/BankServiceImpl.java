package com.rmj.PCC.services;

import com.rmj.PCC.models.Bank;
import com.rmj.PCC.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

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
    
    
}
