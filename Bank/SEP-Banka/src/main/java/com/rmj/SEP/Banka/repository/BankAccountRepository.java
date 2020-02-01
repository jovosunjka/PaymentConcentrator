package com.rmj.SEP.Banka.repository;

import com.rmj.SEP.Banka.models.BankAccount;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
	
	Optional<BankAccount> findByCardNumber(Long cardNumber);

	Optional<BankAccount> findByMerchantId(String merchantId);
}
