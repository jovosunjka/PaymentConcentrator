package com.rmj.PaymentMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmj.PaymentMicroservice.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
}
