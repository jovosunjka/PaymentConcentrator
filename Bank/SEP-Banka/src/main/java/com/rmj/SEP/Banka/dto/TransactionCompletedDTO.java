package com.rmj.SEP.Banka.dto;


import com.rmj.SEP.Banka.models.TransactionStatus;

import java.time.LocalDateTime;

public class TransactionCompletedDTO {
	private Long acquirerOrderId;
	private LocalDateTime acquirerTimeStamp;
	private Long issuerOrderId;
	private LocalDateTime issuerTimeStamp;
	private TransactionStatus status;
 
	public TransactionCompletedDTO() {
		
	}

	public TransactionCompletedDTO(Long acquirerOrderId, LocalDateTime acquirerTimeStamp, Long issuerOrderId,
                                   LocalDateTime issuerTimeStamp, TransactionStatus status) {
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimeStamp = acquirerTimeStamp;
		this.issuerOrderId = issuerOrderId;
		this.issuerTimeStamp = issuerTimeStamp;
		this.status = status;
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

	public Long getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(Long issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public LocalDateTime getIssuerTimeStamp() {
		return issuerTimeStamp;
	}

	public void setIssuerTimeStamp(LocalDateTime issuerTimeStamp) {
		this.issuerTimeStamp = issuerTimeStamp;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
}
