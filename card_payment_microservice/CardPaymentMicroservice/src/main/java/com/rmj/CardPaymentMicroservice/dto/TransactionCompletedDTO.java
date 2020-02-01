package com.rmj.CardPaymentMicroservice.dto;


import com.rmj.CardPaymentMicroservice.model.TransactionStatus;

import java.time.LocalDateTime;

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
