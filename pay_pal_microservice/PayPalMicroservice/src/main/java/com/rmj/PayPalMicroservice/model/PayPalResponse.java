package com.rmj.PayPalMicroservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payPal_response")
public class PayPalResponse {
	//cart: "3VG18081MV5040101"
	// create_time: "2019-08-08T15:51:37Z"
	// id: "PAYID-LVGEKCQ1NP00636Y1192210E"
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "createTime", unique = false, nullable = false)
	private String create_time;
	
	@Column(name = "cart", unique = false, nullable = false)
	private String cart;
	
	@Column(name = "state", unique = false, nullable = false)
	private String state;

	public PayPalResponse() {}
	 
	public PayPalResponse(String id, String create_time, String cart, String state) {
		this.id = id;
		this.create_time = create_time; 
		this.cart = cart; 
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
