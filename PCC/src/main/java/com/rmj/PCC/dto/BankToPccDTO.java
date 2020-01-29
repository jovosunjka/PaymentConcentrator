package com.rmj.PCC.dto;

import java.time.LocalDateTime;

import com.rmj.PCC.models.Bank;

public class BankToPccDTO {
	private Long transactionId;
	private LocalDateTime timeStamp;
	private BankAccountDTO user;
	private Bank bank;
	
	
	public BankToPccDTO() {}
	
	
	public BankToPccDTO(Long transactionId,LocalDateTime timestamp, BankAccountDTO user, Bank bank)
	{
		this.transactionId = transactionId;
		this.timeStamp = timestamp;
		this.user = user;
		this.bank = bank;
	}
	
	
	
	public Bank getBank() {
		return bank;
	}


	public void setBank(Bank bank) {
		this.bank = bank;
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
	public BankAccountDTO getUser() {
		return user;
	}
	public void setUser(BankAccountDTO user) {
		this.user = user;
	}
	

}

