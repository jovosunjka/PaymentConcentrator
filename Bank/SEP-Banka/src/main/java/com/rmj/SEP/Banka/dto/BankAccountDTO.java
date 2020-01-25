package com.rmj.SEP.Banka.dto;


public class BankAccountDTO {
	private String cardHolder;
	private String expDate;
    private int cardNumber;
    private int securityCode;
    private double amount;
    
    
    public BankAccountDTO(){}

    public BankAccountDTO(int cardNumber, int securityCode, double amount,String cardHolder,String expDate)
    {
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.amount = amount;
        this.cardHolder = cardHolder;
        this.expDate = expDate;
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

    public void setAccNum(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
