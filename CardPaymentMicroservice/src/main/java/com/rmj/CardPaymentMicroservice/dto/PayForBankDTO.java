package com.rmj.CardPaymentMicroservice.dto;

import com.rmj.CardPaymentMicroservice.model.Currency;

import java.time.LocalDateTime;

public class PayForBankDTO {
    private String merchantId;
    private String merchantPassword;
    private Long merchantOrderId;
    private double amount;
    private Currency currency;
    private LocalDateTime merchantTimestamp;
    private String callbackUrl;
    private String redirectUrl;

    public PayForBankDTO() {

    }

    public PayForBankDTO(String merchantId, String merchantPassword, Long merchantOrderId, double amount, Currency currency,
                  LocalDateTime merchantTimestamp, String callbackUrl, String redirectUrl) {
        this.merchantId = merchantId;
        this.merchantPassword = merchantPassword;
        this.merchantOrderId = merchantOrderId;
        this.amount = amount;
        this.currency = currency;
        this.merchantTimestamp = merchantTimestamp;
        this.callbackUrl = callbackUrl;
        this.redirectUrl = redirectUrl;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public Long getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(Long merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDateTime getMerchantTimestamp() {
        return merchantTimestamp;
    }

    public void setMerchantTimestamp(LocalDateTime merchantTimestamp) {
        this.merchantTimestamp = merchantTimestamp;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
