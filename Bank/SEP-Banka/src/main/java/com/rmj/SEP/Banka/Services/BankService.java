package com.rmj.SEP.Banka.Services;


public interface BankService {
    void add(int accountNumber, int cardNumber, int securityCode, int amount, String cardHolder,String expirationDate);
}
