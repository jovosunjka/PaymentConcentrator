package com.rmj.PCC.services;

import com.rmj.PCC.dto.BankToPccDTO;
import com.rmj.PCC.models.Bank;

import java.util.List;

public interface BankService {
    List<Bank> getAllBanks();
    void AddNewBank(Bank newBank);
    String redirectToBank(BankToPccDTO bankToPccDTO);
    Bank getBank(int bin);
}
