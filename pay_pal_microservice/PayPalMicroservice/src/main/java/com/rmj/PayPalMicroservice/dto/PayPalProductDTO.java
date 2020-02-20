package com.rmj.PayPalMicroservice.dto;

public class PayPalProductDTO {
	private String name;
	private String description;
	private String type;
	private String category;
	
	public PayPalProductDTO() {}
	
	public PayPalProductDTO(String name, String description, String type, String category) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
