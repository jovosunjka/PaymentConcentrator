package com.rmj.SEP.Banka.dto;

import java.time.LocalDateTime;

public class BankToPccDTO {
	private Long transactionId;
	private LocalDateTime timeStamp;
	private BankAccountDTO user;
	private int bin;
	
	
	public BankToPccDTO() {}
	
	
	public BankToPccDTO(Long transactionId,LocalDateTime timestamp, BankAccountDTO user,int bin)
	{
		this.transactionId = transactionId;
		this.timeStamp = timestamp;
		this.user = user;
		this.bin = bin;
	}
	
	
	public int getBin() {
		return bin;
	}

	public void setBin(int bin) {
		this.bin = bin;
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
