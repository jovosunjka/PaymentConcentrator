package com.rmj.PkiMicroservice.dto;

import java.util.ArrayList;
import java.util.List;

public class RegistrationResultDTOs {
	private List<RegistrationResultDTO> registrationResults;
	
	
	public RegistrationResultDTOs() {
		registrationResults = new ArrayList<RegistrationResultDTO>();
	}
	
	public void addRegistrationResult(String name, boolean result) {
		registrationResults.add(new RegistrationResultDTO(name, result));
	}
	
	public List<RegistrationResultDTO> getRegistrationResults() {
		return registrationResults;
	}

	public void setRegistrationResults(List<RegistrationResultDTO> registrationResults) {
		this.registrationResults = registrationResults;
	}
}
