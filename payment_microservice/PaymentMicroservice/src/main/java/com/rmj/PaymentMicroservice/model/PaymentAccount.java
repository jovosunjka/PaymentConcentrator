package com.rmj.PaymentMicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment_account")
public class PaymentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "type", unique = false, nullable = false)
    private String type; // card payment, paypal, bitcoin, ...

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Property> properties;

    @Column(name = "currency", unique = false, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;


    public PaymentAccount() {

    }

    public PaymentAccount(String type, List<Property> properties, Currency currency) {
        this.type = type;
        this.properties = properties;
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setIdentifier(List<Property> properties) {
        this.properties = properties;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
