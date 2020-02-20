package com.rmj.PayPalMicroservice.dto;

public class PlanRequestDTO {

	private String product_id;
	private String name;
	private String description;
	private String status;
	private BillingCyclesDTO billing_cycles;
	private PaymentPreferencesDTO payment_preferences;
	
	
	public PlanRequestDTO(String product_id, String name, String description, String status,
			BillingCyclesDTO billing_cycles, PaymentPreferencesDTO payment_preferences) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.billing_cycles = billing_cycles;
		this.payment_preferences = payment_preferences;
	}
	
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BillingCyclesDTO getBilling_cycles() {
		return billing_cycles;
	}
	public void setBilling_cycles(BillingCyclesDTO billing_cycles) {
		this.billing_cycles = billing_cycles;
	}
	public PaymentPreferencesDTO getPayment_preferences() {
		return payment_preferences;
	}
	public void setPayment_preferences(PaymentPreferencesDTO payment_preferences) {
		this.payment_preferences = payment_preferences;
	}
	
}
