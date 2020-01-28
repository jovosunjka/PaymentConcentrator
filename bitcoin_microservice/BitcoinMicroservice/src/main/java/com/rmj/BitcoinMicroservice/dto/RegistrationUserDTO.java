package com.rmj.BitcoinMicroservice.dto;


import com.rmj.BitcoinMicroservice.models.Currency;
import com.rmj.BitcoinMicroservice.models.Property;

import java.util.ArrayList;
import java.util.List;

public class RegistrationUserDTO {
	private String username;
	private String password;
	private String repeatedPassword;
	private List<Property> properties = new ArrayList<Property>();
	private Currency currency;
	
	public RegistrationUserDTO() {
		
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

	public List<Property> getProperties() { return properties; }

	public void setProperties(List<Property> properties) { this.properties = properties; }

	public Currency getCurrency() { return currency; }

	public void setCurrency(Currency currency) { this.currency = currency; }
}

