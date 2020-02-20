package com.rmj.PayPalMicroservice.dto;

public class PricingSchemeDTO {

	private FixedPriceDTO fixed_price;

	public PricingSchemeDTO(FixedPriceDTO fixed_price) {
		super();
		this.fixed_price = fixed_price;
	}

	public FixedPriceDTO getFixed_price() {
		return fixed_price;
	}

	public void setFixed_price(FixedPriceDTO fixed_price) {
		this.fixed_price = fixed_price;
	}
	
	
}
