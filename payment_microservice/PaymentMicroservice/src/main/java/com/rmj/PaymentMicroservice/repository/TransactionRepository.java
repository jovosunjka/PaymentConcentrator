package com.rmj.PaymentMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmj.PaymentMicroservice.model.Transaction;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    Optional<Transaction> findByMerchantOrderId(Long merchantOrderId);
}
