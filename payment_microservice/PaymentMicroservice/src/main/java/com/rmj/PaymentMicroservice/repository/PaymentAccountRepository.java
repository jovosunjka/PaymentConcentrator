package com.rmj.PaymentMicroservice.repository;

import com.rmj.PaymentMicroservice.model.PaymentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, Long> {

	Optional<PaymentAccount> findByUsername(String username);
}
