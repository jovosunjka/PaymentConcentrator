package com.rmj.PaymentMicroservice.model;

import com.rmj.PaymentMicroservice.security.AttributeEncryptor;

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

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "access_token", unique = true, nullable = true)
    private String accessToken; //jwt token

    @Column(name = "currency", unique = false, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;


    public PaymentAccount() {

    }

    public PaymentAccount(String type, String username, String password, Currency currency) {
        this.type = type;
        this.username = username;
        this.password = password;
        this.accessToken = null;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
