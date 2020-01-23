package com.rmj.BitcoinMicroservice.repository;

import com.rmj.BitcoinMicroservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
