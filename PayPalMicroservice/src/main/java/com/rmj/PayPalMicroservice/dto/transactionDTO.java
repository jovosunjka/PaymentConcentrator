package com.rmj.PayPalMicroservice.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_table")
public class transactionDTO {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "createTime", unique = false, nullable = false)
	private String create_time;
	
	@Column(name = "cart", unique = false, nullable = false)
	private String cart;
	
	public transactionDTO() {}
	 
	public transactionDTO(String id, String create_time, String cart) {
		this.id = id;
		this.create_time = create_time; 
		this.cart = cart; 
	}
	 
	public String getId() { return id; }
	public String getCreateTime() { return create_time; }
	public String getCart() { return cart; }
	
	//cart: "3VG18081MV5040101"
	// create_time: "2019-08-08T15:51:37Z"
	// id: "PAYID-LVGEKCQ1NP00636Y1192210E"
}
