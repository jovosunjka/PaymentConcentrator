package com.rmj.PCC.services;

import com.rmj.PCC.dto.BankToPccDTO;
import com.rmj.PCC.dto.BankAccountDTO;
import com.rmj.PCC.dto.TransactionCompletedDTO;
import com.rmj.PCC.models.Bank;
import com.rmj.PCC.models.Currency;

import java.time.LocalDateTime;
import java.util.List;

public interface BankService {
    List<Bank> getAllBanks();
    void AddNewBank(Bank newBank);
    void redirectToBank(String issuerBankUrl, Long acquirerOrderid, LocalDateTime acquirerTimeStamp,
                        double amount, Currency currency, BankAccountDTO user);
    Bank getBank(int bin);

    void makeTransaction(Long transactionId, LocalDateTime timeStamp, double amount, Currency currency,
                         int acquirerBin, BankAccountDTO user);

    void sendTransactionCompletedDTO(TransactionCompletedDTO transactionCompletedDTO);
}
