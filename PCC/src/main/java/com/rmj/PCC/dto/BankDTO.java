package com.rmj.PCC.dto;

public class BankDTO {
    private String name;
    private int bin;
    private String redirectUrl;
    private String transactionCompletedUrl;

    public BankDTO() {

    }

    public BankDTO(String name, int bin, String redirectUrl, String transactionCompletedUrl) {
        this.name = name;
        this.bin = bin;
        this.redirectUrl = redirectUrl;
        this.transactionCompletedUrl = transactionCompletedUrl;
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
