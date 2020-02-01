package com.rmj.PCC.models;

import javax.persistence.*;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bin")
    private int bin;
    
    @Column(name ="redirect_url")
    private String redirectUrl;

	@Column(name = "transaction_completed_url")
    private String transactionCompletedUrl;


    public Bank(){}

    public Bank(String name, int bin, String redirect,String transactionCompleted)
    {
        this.name = name;
        this.bin = bin;
        this.redirectUrl = redirect;
        this.transactionCompletedUrl = transactionCompleted;
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

    public int getBin() {
        return bin;
    }

    public void setBin(int bin) {
        this.bin = bin;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getTransactionCompletedUrl() {
        return transactionCompletedUrl;
    }

    public void setTransactionCompletedUrl(String transactionCompletedUrl) {
        this.transactionCompletedUrl = transactionCompletedUrl;
    }


}
