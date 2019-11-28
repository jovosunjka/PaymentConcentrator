package com.rmj.IntegrationMicroservice.repository;

import com.rmj.IntegrationMicroservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
