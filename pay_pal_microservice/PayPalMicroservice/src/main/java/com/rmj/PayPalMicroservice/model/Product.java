package com.rmj.PayPalMicroservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Ova klasa ce zapravo predstavljati naucni rad, kada su nasi klijenti naucne centrale
 * */
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "subscribe_product_id", unique = true, nullable = false)
	private String subscribeProductId;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "product_plans", joinColumns = {
			@JoinColumn(name = "product_id", nullable = false, updatable = false) }, inverseJoinColumns = {
			@JoinColumn(name = "plan_id", nullable = false, updatable = false) })
	private List<Plan> plans;

	public Product() {}


	public Product(String subscribeProductId, String name, List<Plan> plans) {
		this.subscribeProductId = subscribeProductId;
		this.name = name;
		this.plans = plans;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubscribeProductId() {
		return subscribeProductId;
	}

	public void setSubscribeProductID(String subscribeProductId) {
		this.subscribeProductId = subscribeProductId;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}