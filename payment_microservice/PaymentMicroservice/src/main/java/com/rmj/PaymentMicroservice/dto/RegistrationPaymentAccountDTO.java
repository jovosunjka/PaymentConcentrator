package com.rmj.PaymentMicroservice.dto;

import com.rmj.PaymentMicroservice.model.Currency;
import com.rmj.PaymentMicroservice.model.Property;

import java.util.List;


public class RegistrationPaymentAccountDTO {
    private String username;
    private String password;
    private String repeatedPassword;
    private List<Property> properties;
    private Currency currency;

    public RegistrationPaymentAccountDTO() {

    }

    public RegistrationPaymentAccountDTO(String username, String password, String repeatedPassword, List<Property> properties, Currency currency) {
        this.username = username;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.properties = properties;
        this.currency = currency;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRepeatedPassword() { return repeatedPassword; }

    public void setRepeatedPassword(String repeatedPassword) { this.repeatedPassword = repeatedPassword; }

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
