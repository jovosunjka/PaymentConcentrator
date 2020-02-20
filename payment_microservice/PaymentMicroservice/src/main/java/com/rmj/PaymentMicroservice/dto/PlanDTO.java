package com.rmj.PaymentMicroservice.dto;

public class PlanDTO {

	private String intervalUnit;
	private String intervalCount;
	private	double price;
	
	public PlanDTO() {}
	
	public PlanDTO(String intervalUnit, String intervaCount) {
		this.intervalCount = intervaCount;
		this.intervalUnit = intervalUnit;
	}

	public String getIntervalUnit() {
		return intervalUnit;
	}

	public void setIntervalUnit(String intervalUnit) {
		this.intervalUnit = intervalUnit;
	}

	public String getIntervalCount() {
		return intervalCount;
	}

	public void setIntervalCount(String intervalCount) {
		this.intervalCount = intervalCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
