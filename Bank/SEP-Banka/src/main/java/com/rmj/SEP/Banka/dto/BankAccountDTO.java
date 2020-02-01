package com.rmj.SEP.Banka.dto;


import com.rmj.SEP.Banka.models.Currency;

import java.time.LocalDateTime;

public class BankAccountDTO {
	private String cardHolder;
	private String expDate;
    private long cardNumber;
    private int securityCode;
    
    public BankAccountDTO(){}

    public BankAccountDTO(String cardHolder, String expDate, long cardNumber, int securityCode) {
        this.cardHolder = cardHolder;
        this.expDate = expDate;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
    }

    public String getCardHolder()
    {
    	return cardHolder;
    }
    
    public void setCardHolder(String cardHolder)
    {
    	this.cardHolder = cardHolder;
    }
    
    public String getExpDate()
    {
    	return expDate;
    }
    
    public void setExpDate(String expDate)
    {
    	this.expDate = expDate;
    }
    
    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
