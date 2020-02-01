package com.rmj.PCC.dto;

import com.rmj.PCC.dto.BankAccountDTO;
import com.rmj.PCC.models.Currency;

import java.time.LocalDateTime;

public class BankToPccDTO {
	private Long transactionId;
	private LocalDateTime timeStamp;
	private double amount;
	private Currency currency;
	private int acquirerBin;
	private BankAccountDTO user;
	
	
	public BankToPccDTO() {}
	
	
	public BankToPccDTO(Long transactionId, LocalDateTime timestamp, double amount, int acquirerBin, Currency currency,
                        BankAccountDTO user)
	{
		this.transactionId = transactionId;
		this.timeStamp = timestamp;
		this.amount = amount;
		this.currency = currency;
		this.acquirerBin = acquirerBin;
		this.user = user;
	}

	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public int getAcquirerBin() {
		return acquirerBin;
	}

	public void setAcquirerBin(int acquirerBin) {
		this.acquirerBin = acquirerBin;
	}

	public BankAccountDTO getUser() {
		return user;
	}
	public void setUser(BankAccountDTO user) {
		this.user = user;
	}


}
