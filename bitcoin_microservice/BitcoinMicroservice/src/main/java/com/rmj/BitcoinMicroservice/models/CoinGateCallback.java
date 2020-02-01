package com.rmj.BitcoinMicroservice.models;

public class CoinGateCallback {
	private int id;
	private String order_id;
	private String status;
	private Long price_amount;
	private String price_currency;
	private Long receive_amount;
	private String receive_currency;
	private Long pay_amount;
	private String pay_currency;
	private String created_at;
	private String token;
	private Long underpaid_amount;
	private Long overpaid_amount;
	private String is_refundable;
	
	public CoinGateCallback() {}
	
	public CoinGateCallback(int id, String order_id, String status, Long price_amount, String price_currency, Long receive_amount, String receive_currency, Long pay_amount,String pay_currency,String created_at, String token, Long underpaid_amount, Long overpaid_amount, String is_refundable) 
	{
		this.id = id;
		this.order_id = order_id;
		this.status = status;
		this.price_amount = price_amount;
		this.price_currency = price_currency; 
		this.receive_amount = receive_amount; 
		this.receive_currency = receive_currency; 
		this.pay_amount = pay_amount;
		this.pay_currency = pay_currency;
		this.created_at = created_at;
		this.token = token;
		this.underpaid_amount = underpaid_amount;
		this.overpaid_amount = overpaid_amount;
		this.is_refundable = is_refundable;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getPrice_amount() {
		return price_amount;
	}

	public void setPrice_amount(Long price_amount) {
		this.price_amount = price_amount;
	}

	public String getPrice_currency() {
		return price_currency;
	}

	public void setPrice_currency(String price_currency) {
		this.price_currency = price_currency;
	}

	public Long getReceive_amount() {
		return receive_amount;
	}

	public void setReceive_amount(Long receive_amount) {
		this.receive_amount = receive_amount;
	}

	public String getReceive_currency() {
		return receive_currency;
	}

	public void setReceive_currency(String receive_currency) {
		this.receive_currency = receive_currency;
	}

	public Long getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(Long pay_amount) {
		this.pay_amount = pay_amount;
	}

	public String getPay_currency() {
		return pay_currency;
	}

	public void setPay_currency(String pay_currency) {
		this.pay_currency = pay_currency;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUnderpaid_amount() {
		return underpaid_amount;
	}

	public void setUnderpaid_amount(Long underpaid_amount) {
		this.underpaid_amount = underpaid_amount;
	}

	public Long getOverpaid_amount() {
		return overpaid_amount;
	}

	public void setOverpaid_amount(Long overpaid_amount) {
		this.overpaid_amount = overpaid_amount;
	}

	public String getIs_refundable() {
		return is_refundable;
	}

	public void setIs_refundable(String is_refundable) {
		this.is_refundable = is_refundable;
	}

}
