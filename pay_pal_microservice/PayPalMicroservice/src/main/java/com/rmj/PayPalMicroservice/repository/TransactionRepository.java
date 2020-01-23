package com.rmj.PayPalMicroservice.repository;

import com.rmj.PayPalMicroservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
