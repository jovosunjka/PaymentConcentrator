package com.rmj.PCC.repository;

import com.rmj.PCC.models.Bank;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank,Long> {
	
	Optional<Bank> findByBin(int bin);
}
