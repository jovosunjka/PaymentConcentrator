package com.rmj.PayPalMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmj.PayPalMicroservice.model.PayPalResponse;

public interface PayPalResponseRepository extends JpaRepository<PayPalResponse, Long>{

}
