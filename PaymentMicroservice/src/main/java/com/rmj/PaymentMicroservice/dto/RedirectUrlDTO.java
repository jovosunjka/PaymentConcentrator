package com.rmj.PaymentMicroservice.dto;

public class RedirectUrlDTO {
    private String redirectUrl;

    public RedirectUrlDTO() {
    	
    }
    
    public RedirectUrlDTO(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
