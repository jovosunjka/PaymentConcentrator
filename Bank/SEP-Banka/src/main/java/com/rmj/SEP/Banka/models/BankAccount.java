package com.rmj.SEP.Banka.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private int accountNumber;
    
    @Column(name = "card_number", unique = true, nullable = false)
    private int cardNumber;

    @Column(name = "security_code", unique = false, nullable = false)
    private int securityCode;

    @Column(name = "amount", unique = false, nullable = false)
    private double amount;

    @Column(name = "card_holder", unique =  false, nullable = false)
    private String cardHolder;
    
    @Column(name = "expiration_date", unique= false, nullable= false)
    private String expirationDate;

    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bank_account_transactions", joinColumns = {
            @JoinColumn(name = "bank_account_id", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "transaction_id", nullable = false, updatable = false) })
    private List<Transaction> transactions;
    
    
    public BankAccount(){}

    public BankAccount(int accountNumber, int cardNumber, int securityCode, int amount, String cardHolder,String expirationDate)
    {
    	this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.amount = amount;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.transactions = new ArrayList<Transaction>();
    }
    
    public String getExpirationDate()
    {
    	return expirationDate;
    }
    
    public void setExpirationDate(String expirationDate)
    {
    	this.expirationDate = expirationDate;
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

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
    	this.cardHolder = cardHolder;
    }

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
