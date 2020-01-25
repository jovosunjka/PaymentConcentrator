package com.rmj.CardPaymentMicroservice.dto;

public class CardNumberAndPinDTO {
	private int cardNumber;
	private int securityCode;
	private String cardHolder;
	private String expirationDate;
	
	public CardNumberAndPinDTO() {
		
	}
	
	public CardNumberAndPinDTO(int cardNumber, int securityCode,String cardHolder,String expirationDate) {
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
		this.cardHolder = cardHolder;
		this.expirationDate = expirationDate;
	}
	public String getCardHolder()
	{
		return cardHolder;
	}
	
	public void setCardHolder(String cardHolder)
	{
		this.cardHolder = cardHolder;
	}
	
	public String getExpirationDate()
	{
		return this.expirationDate;
	}
	
	public void setExpirationDate(String expirationDate)
	{
		this.expirationDate = expirationDate;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

}
