package com.rmj.PayPalMicroservice.dto;

public class FrequencyDTO {
	private String interval_unit;
	private int interval_count;
	
	public FrequencyDTO(String interval_unit, int interval_count) {
		super();
		this.interval_unit = interval_unit;
		this.interval_count = interval_count;
	}
	
	public String getInterval_unit() {
		return interval_unit;
	}
	public void setInterval_unit(String interval_unit) {
		this.interval_unit = interval_unit;
	}
	public int getInterval_count() {
		return interval_count;
	}
	public void setInterval_count(int interval_count) {
		this.interval_count = interval_count;
	}
	
}
