package com.rmj.BitcoinMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rmj.BitcoinMicroservice.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
