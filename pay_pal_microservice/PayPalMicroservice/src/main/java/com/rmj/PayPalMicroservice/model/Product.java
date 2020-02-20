package com.rmj.PayPalMicroservice.model;

import java.util.List;

import javax.persistence.*;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_plans",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "plan_id", referencedColumnName = "id"))
	private List<Plan> plans;

	public Product() {}

    public Product(String subscribeProductId, List<Plan> plans) {
        this.subscribeProductId = subscribeProductId;
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

	public void setSubscribeProductId(String subscribeProductId) {
		this.subscribeProductId = subscribeProductId;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}
	
	
	
	
}
