package com.rmj.PkiMicroservice.repository;

import com.rmj.PkiMicroservice.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findBySerialNumber(Long serialNumber);

    List<Certificate> findDistinctByRevoked(boolean revoked);

    Certificate save(Certificate c);

    Certificate findByOrganizationalUnitName(String organizationalUnitName);
}
