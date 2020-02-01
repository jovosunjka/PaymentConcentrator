package com.rmj.BitcoinMicroservice.dto;

public class RedirectUrlDTO {
	
	private String redirectUrl;
	private Long idTransaction;


	public RedirectUrlDTO() {
    	
    }
    
    public RedirectUrlDTO(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public RedirectUrlDTO(String redirectUrl, Long id) {
        this.redirectUrl = redirectUrl;
        this.idTransaction = id;
    }
    
    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Long getIdTransaction() {
    	return idTransaction;
    }
    
    public void setIdTransaction(Long idTransaction) {
    	this.idTransaction = idTransaction;
    }
}
