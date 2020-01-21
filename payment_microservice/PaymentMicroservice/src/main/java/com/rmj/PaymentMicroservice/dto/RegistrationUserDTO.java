package com.rmj.PaymentMicroservice.dto;


import com.rmj.PaymentMicroservice.model.PaymentAccount;

import java.util.ArrayList;
import java.util.List;

public class RegistrationUserDTO {
	
	private String name;
	private String username;
	private String password;
	private String repeatedPassword;
	private List<PaymentAccount> payments = new ArrayList<PaymentAccount>();
	
	public RegistrationUserDTO() {
		
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public List<PaymentAccount> getPayments() { return payments; }

	public void setPayments(List<PaymentAccount> payments) { this.payments = payments; }
}

