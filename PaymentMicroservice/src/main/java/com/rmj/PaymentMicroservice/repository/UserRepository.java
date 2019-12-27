package com.rmj.PaymentMicroservice.repository;


import java.util.Optional;

import com.rmj.PaymentMicroservice.model.User;

import org.springframework.data.jpa.repository.JpaRepository;;


public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	Optional<User> findByUsernameAndPassword(String username, String password);
	boolean existsByUsername(String username);

}
