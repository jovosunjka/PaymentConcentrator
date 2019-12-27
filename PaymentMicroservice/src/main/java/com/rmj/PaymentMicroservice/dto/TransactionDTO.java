package com.rmj.PaymentMicroservice.dto;

public class TransactionDTO {
    private Long id;

    public TransactionDTO(Long id) {
    	this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
