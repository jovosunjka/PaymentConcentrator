package com.rmj.PaymentMicroservice.model;

import javax.persistence.*;

@Entity
@Table(name = "payment_account")
public class PaymentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "type", unique = false, nullable = false)
    private String type; // card payment, paypal, bitcoin, ...

    @Column(name = "identifier", unique = false, nullable = false)
    private String identifier; // card number, paypal identifier, bitcoin identifier, ...

    @Column(name = "currency", unique = false, nullable = false)
    private String currency;


    public PaymentAccount() {

    }

    public PaymentAccount(String type, String identifier, String currency) {
        this.type = type;
        this.identifier = identifier;
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
