package com.rmj.SEP.Banka.dto;


import com.rmj.SEP.Banka.models.TransactionStatus;

import java.time.LocalDateTime;

public class TransactionCompletedForCpmsDTO {
	private Long merchantOrderId;
	private Long acquirerOrderId;
	private LocalDateTime acquirerTimeStamp;
	private TransactionStatus status;

	public TransactionCompletedForCpmsDTO() {

	}

	public TransactionCompletedForCpmsDTO(Long merchantOrderId, Long acquirerOrderId, LocalDateTime acquirerTimeStamp,
										  TransactionStatus status) {
		this.merchantOrderId = merchantOrderId;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimeStamp = acquirerTimeStamp;
		this.status = status;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
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

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
}