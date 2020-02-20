package com.rmj.PayPalMicroservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plan")
public class Plan { // ovom klasom je predstavljeno i obicno i subscribe (pretplata) paypal placanje

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "plan_id", unique = false, nullable = true)
	private String planId;

	@Column(name = "interval_unit", unique = false, nullable = false)
	private String interval_unit;

	@Column(name = "interval_count", unique = false, nullable = false)
	private int interval_count;

	@Column(name = "price", unique = false, nullable = false)
	private	double price;

	public Plan() {}


	public Plan(String planId, String interval_unit, int interval_count, double price) {
		super();
		this.planId = planId;
		this.interval_unit = interval_unit;
		this.interval_count = interval_count;
		this.price = price;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getIntervalUnit() {
		return interval_unit;
	}

	public void setIntervalUnit(String intervalUnit) {
		this.interval_unit = intervalUnit;
	}

	public int getIntervalCount() {
		return interval_count;
	}

	public void setIntervalCount(int intervalCount) {
		this.interval_count = intervalCount;
	}

	public double isPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}



}