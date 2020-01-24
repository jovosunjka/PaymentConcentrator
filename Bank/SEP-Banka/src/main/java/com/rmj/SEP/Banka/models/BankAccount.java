package com.rmj.SEP.Banka.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class BankAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private int accountNumber;
    
    @Column(name = "cardNumber", unique = true, nullable = false)
    private int cardNumber;

    @Column(name = "pin", unique = false, nullable = false)
    private int pin;

    @Column(name = "amount", unique = false, nullable = false)
    private double amount;

    @Column(name = "name", unique =  false, nullable = false)
    private String name;

    @Column(name = "surname", unique = false,nullable = false)
    private String surname;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bank_account_transactions", joinColumns = {
            @JoinColumn(name = "bank_account_id", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "transaction_id", nullable = false, updatable = false) })
    private List<Transaction> transactions;
    
    
    public BankAccount(){}

    public BankAccount(int accountNumber, int cardNumber, int pin, int amount, String name, String surname)
    {
    	this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.amount = amount;
        this.name = name;
        this.surname = surname;
        this.transactions = new ArrayList<Transaction>();
    }
    
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public long getCardNumber() {
        return cardNumber;
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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
