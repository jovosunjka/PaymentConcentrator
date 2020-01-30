package com.rmj.PaymentMicroservice.dto;

public class PaymentTypeDTO {
    private String name;
    private boolean currentlyActivated;


    public PaymentTypeDTO() {

    }

    public PaymentTypeDTO(String name, boolean currentlyActivated) {
        this.name = name;
        this.currentlyActivated = currentlyActivated;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCurrentlyActivated() { return currentlyActivated; }

    public void setCurrentlyActivated(boolean currentlyActivated) { this.currentlyActivated = currentlyActivated; }
}
