package com.rmj.PayPalMicroservice.dto;

public class BillingCyclesDTO {

	private FrequencyDTO frequency;
	private String tenure_type;
	private int sequence;
	private int total_cycles;
	private PricingSchemeDTO pricing_scheme;
	
	public BillingCyclesDTO(FrequencyDTO frequency, String tenure_type, int sequence, int total_cycles,
			PricingSchemeDTO pricing_scheme) {
		super();
		this.frequency = frequency;
		this.tenure_type = tenure_type;
		this.sequence = sequence;
		this.total_cycles = total_cycles;
		this.pricing_scheme = pricing_scheme;
	}

	public FrequencyDTO getFrequency() {
		return frequency;
	}

	public void setFrequency(FrequencyDTO frequency) {
		this.frequency = frequency;
	}

	public String getTenure_type() {
		return tenure_type;
	}

	public void setTenure_type(String tenure_type) {
		this.tenure_type = tenure_type;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getTotal_cycles() {
		return total_cycles;
	}

	public void setTotal_cycles(int total_cycles) {
		this.total_cycles = total_cycles;
	}

	public PricingSchemeDTO getPricing_scheme() {
		return pricing_scheme;
	}

	public void setPricing_scheme(PricingSchemeDTO pricing_scheme) {
		this.pricing_scheme = pricing_scheme;
	}
	
	
}
