package com.rmj.IntegrationMicroservice.model;

import javax.persistence.*;
import java.util.List;

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "base_url", unique = true, nullable = false)
    private String baseUrl;

    @OneToMany
    private List<PaymentAccount> paymentAccounts;


    public Client() {

    }

    public Client(String name, String baseUrl, List<PaymentAccount> paymentAccounts) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.paymentAccounts = paymentAccounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<PaymentAccount> getPaymentAccounts() {
        return paymentAccounts;
    }

    public void setPaymentAccounts(List<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
    }
}
