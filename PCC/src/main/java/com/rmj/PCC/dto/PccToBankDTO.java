package com.rmj.PCC.dto;

import com.rmj.PCC.models.Currency;

import java.time.LocalDateTime;

public class PccToBankDTO {
	private Long acquirerOrderId;
	private LocalDateTime acquirerTimeStamp;
	private double amount;
	private Currency currency;
	private BankAccountDTO user;


	public PccToBankDTO() {}


	public PccToBankDTO(Long acquirerOrderId, LocalDateTime acquirerTimeStamp, double amount, Currency currency, BankAccountDTO user) {
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimeStamp = acquirerTimeStamp;
		this.amount = amount;
		this.currency = currency;
		this.user = user;
	}

	public Long getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(Long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public LocalDateTime getAcquirerTimeStamp() {
		return acquirerTimeStamp;
	}

	public void setAcquirerTimeStamp(LocalDateTime acquirerTimeStamp) {
		this.acquirerTimeStamp = acquirerTimeStamp;
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

	public BankAccountDTO getUser() {
		return user;
	}

	public void setUser(BankAccountDTO user) {
		this.user = user;
	}
}
