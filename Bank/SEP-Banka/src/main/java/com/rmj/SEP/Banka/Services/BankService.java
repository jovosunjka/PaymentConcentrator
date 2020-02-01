package com.rmj.SEP.Banka.Services;

import java.time.LocalDateTime;

import com.rmj.SEP.Banka.dto.BankAccountDTO;
import com.rmj.SEP.Banka.dto.TransactionCompletedDTO;
import com.rmj.SEP.Banka.models.BankAccount;
import com.rmj.SEP.Banka.models.Currency;

public interface BankService {

    void redirectToPcc(Long transactionId, LocalDateTime timeStamp, double amount, Currency currency,
                       int acquirerBin, BankAccountDTO user);

    BankAccount getBankAccount(Long cardNumber);

    BankAccount getBankAccount(String merchantId);

    void response(TransactionCompletedDTO transactionCompletedDTO);

    void save(BankAccount account);

    String makeTransaction(String merchantId, String merchantPassword, Long merchantOrderId,
           double amount, Currency currency, LocalDateTime merchantTimestamp, String redirectUrl, String callbackUrl);

    void pay(Long transactionId, BankAccountDTO bankAccountDTO);

    void request(Long acquirerOrderId, double amount, Currency currency, LocalDateTime merchantTimestamp,
                 BankAccountDTO user, String callbackUrl, String redirectUrl);
}
