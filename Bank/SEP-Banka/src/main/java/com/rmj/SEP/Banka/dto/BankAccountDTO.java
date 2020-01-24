package com.rmj.SEP.Banka.dto;


public class BankAccountDTO {
    private int cardNumber;
    private int pin;
    private double amount;
    
    
    public BankAccountDTO(){}

    public BankAccountDTO(int cardNumber, int pin, double amount)
    {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.amount = amount;
    }
   
    
    public long getCardNumber() {
        return cardNumber;
    }

    public void setAccNum(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
