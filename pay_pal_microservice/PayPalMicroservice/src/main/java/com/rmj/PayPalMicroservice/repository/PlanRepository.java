package com.rmj.PayPalMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmj.PayPalMicroservice.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>{

}
