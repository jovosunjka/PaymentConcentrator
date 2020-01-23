package com.rmj.BitcoinMicroservice.models;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
public class Order {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "status", unique = false, nullable = false)
    private String status;

	@Column(name = "price_currency", unique = false, nullable = false)
    private String price_currency;

	@Column(name = "price_amount", unique = false, nullable = false)
    private String price_amount;

    private String receive_currency;

    private String receive_amount;

    private String createdAt;

    @Column(name = "order_id", unique = false, nullable = false)
    private String order_id;

    private String payment_url;

    @Column(name = "token", unique = false, nullable = false)
    private String token;

    public Order() {}

    public Order(Long id, String status, String price_currency, String price_amount,
                                    String receive_currency, String receive_amount, String createdAt,
                                    String order_id, String payment_url, String token) {
        this.id = id;
        this.status = status;
        this.price_currency = price_currency;
        this.price_amount = price_amount;
        this.receive_currency = receive_currency;
        this.receive_amount = receive_amount;
        this.createdAt = createdAt;
        this.order_id = order_id;
        this.payment_url = payment_url;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice_currency() {
        return price_currency;
    }

    public void setPrice_currency(String price_currency) {
        this.price_currency = price_currency;
    }

    public String getPrice_amount() {
        return price_amount;
    }

    public void setPrice_amount(String price_amount) {
        this.price_amount = price_amount;
    }

    public String getReceive_currency() {
        return receive_currency;
    }

    public void setReceive_currency(String receive_currency) {
        this.receive_currency = receive_currency;
    }

    public String getReceive_amount() {
        return receive_amount;
    }

    public void setReceive_amount(String receive_amount) {
        this.receive_amount = receive_amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPayment_url() {
        return payment_url;
    }

    public void setPayment_url(String payment_url) {
        this.payment_url = payment_url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
