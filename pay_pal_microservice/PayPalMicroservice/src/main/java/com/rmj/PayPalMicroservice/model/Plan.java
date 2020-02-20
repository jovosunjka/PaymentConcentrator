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
	private String intervalUnit;
	
	@Column(name = "interval_count", unique = false, nullable = false)
	private int intervalCount;
	
	@Column(name = "subscribe", unique = false, nullable = false)
	private	boolean subscribe;
	
	public Plan() {}

    public Plan(String planId, String intervalUnit, int intervalCount, boolean subscribe) {
        this.planId = planId;
        this.intervalUnit = intervalUnit;
        this.intervalCount = intervalCount;
        this.subscribe = subscribe;
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
		return intervalUnit;
	}

	public void setIntervalUnit(String intervalUnit) {
		this.intervalUnit = intervalUnit;
	}

	public int getIntervalCount() {
		return intervalCount;
	}

	public void setIntervalCount(int intervalCount) {
		this.intervalCount = intervalCount;
	}

	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}
	
}
