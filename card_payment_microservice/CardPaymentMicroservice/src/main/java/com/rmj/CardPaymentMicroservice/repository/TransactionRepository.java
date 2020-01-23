package com.rmj.CardPaymentMicroservice.repository;

import com.rmj.CardPaymentMicroservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
