package com.rmj.PayPalMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rmj.PayPalMicroservice.dto.transactionDTO;

public interface TransactionRepository extends JpaRepository<transactionDTO, Long>{

}
