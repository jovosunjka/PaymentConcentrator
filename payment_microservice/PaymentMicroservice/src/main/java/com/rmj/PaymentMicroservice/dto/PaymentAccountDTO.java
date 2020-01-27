package com.rmj.PaymentMicroservice.dto;

import com.rmj.PaymentMicroservice.model.Currency;
import com.rmj.PaymentMicroservice.model.Property;

import javax.persistence.*;
import java.util.List;


public class PaymentAccountDTO {

    private String type; // card payment, paypal, bitcoin, ...
    private List<Property> properties;
    private Currency currency;

    public PaymentAccountDTO() {

    }

    public PaymentAccountDTO(String type, List<Property> properties, Currency currency) {
        this.type = type;
        this.properties = properties;
        this.currency = currency;
    }


    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
