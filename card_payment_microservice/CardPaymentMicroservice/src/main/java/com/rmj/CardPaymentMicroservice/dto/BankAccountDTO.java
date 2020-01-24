package com.rmj.CardPaymentMicroservice.dto;


public class BankAccountDTO {
    private int cardNumber;
    private int pin;
    private double amount;
    private String name;
    private String surname;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        surname = surname;
    }



    public BankAccountDTO(){}

    public BankAccountDTO(int cardNumber, int pin, double amount, String name, String surname)
    {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.amount = amount;
        this.name = name;
        this.surname = surname;
    }
}
