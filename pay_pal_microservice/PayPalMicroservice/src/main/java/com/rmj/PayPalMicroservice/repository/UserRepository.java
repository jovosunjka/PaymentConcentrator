package com.rmj.PayPalMicroservice.repository;


import com.rmj.PayPalMicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	Optional<User> findByUsernameAndPassword(String username, String password);
	boolean existsByUsername(String username);

}
