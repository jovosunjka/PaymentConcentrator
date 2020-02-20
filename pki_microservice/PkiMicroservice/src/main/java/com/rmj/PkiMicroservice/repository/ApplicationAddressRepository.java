package com.rmj.PkiMicroservice.repository;

import com.rmj.PkiMicroservice.model.ApplicationAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationAddressRepository extends JpaRepository<ApplicationAddress, Long> {

    ApplicationAddress findByOrganizationalUnitName(String rganizationalUnitName);
}
