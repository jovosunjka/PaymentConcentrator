package com.rmj.NewPaymentMicroservice.dto;


import com.rmj.NewPaymentMicroservice.model.TransactionStatus;

public class TransactionCompletedDTO {
	private Long merchantOrderId;
	private TransactionStatus status;

	public TransactionCompletedDTO() {

	}

	public TransactionCompletedDTO(Long merchantOrderId, TransactionStatus status) {
		this.merchantOrderId = merchantOrderId;
		this.status = status;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	
}
