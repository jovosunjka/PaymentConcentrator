package com.rmj.PaymentMicroservice.dto;

import java.util.List;

public class RegistrationUserDTOs {
	
	private List<RegistrationUserDTO> users;
	
	
	public RegistrationUserDTOs() {
		
	}
	
	
	public List<RegistrationUserDTO> getUsers() {
		return users;
	}
	
	public void setUsers(List<RegistrationUserDTO> users) {
		this.users = users;
	}
	
}

