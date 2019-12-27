package com.rmj.SEP.Banka.models;

import javax.persistence.*;

@Entity
public class BankAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "cardNumber", unique = true, nullable = false)
    private int cardNumber;

    @Column(name = "pin", unique = false, nullable = false)
    private int pin;

    @Column(name = "amount", unique = false, nullable = false)
    private int amount;

    @Column(name = "name", unique =  false, nullable = false)
    private String name;

    @Column(name = "surname", unique = false,nullable = false)
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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



    public BankAccount(){}

    public BankAccount(int cardNumber, int pin, int amount, String name, String surname)
    {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.amount = amount;
        this.name = name;
        this.surname = surname;
    }
}
