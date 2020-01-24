package com.rmj.CardPaymentMicroservice.dto;

public class CardNumberAndPinDTO {
	private int cardNumber;
	private int pin;
	
	public CardNumberAndPinDTO() {
		
	}
	
	public CardNumberAndPinDTO(int cardNumber, int pin) {
		this.cardNumber = cardNumber;
		this.pin = pin;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

}
