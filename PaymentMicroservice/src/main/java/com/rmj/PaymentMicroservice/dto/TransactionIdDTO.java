package com.rmj.PaymentMicroservice.dto;

public class TransactionIdDTO {
    private Long id;

    public TransactionIdDTO(Long id) {
    	this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
