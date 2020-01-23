package com.rmj.PaymentMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmj.PaymentMicroservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);
}
