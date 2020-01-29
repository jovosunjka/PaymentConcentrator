package com.rmj.SEP.Banka.Services;

import java.time.LocalDateTime;

import com.rmj.SEP.Banka.dto.BankAccountDTO;
import com.rmj.SEP.Banka.models.BankAccount;

public interface BankService {
	void filter();
    boolean checkBin(int cardBin);
    String redirectToPcc(Long transactionId,LocalDateTime timeStamp,BankAccountDTO user,int bin);
    BankAccount getBank(Long cardNumber);
}
