package com.rmj.PkiMicroservice.repository;

import com.rmj.PkiMicroservice.model.FtpApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FtpApplicationRepository extends JpaRepository<FtpApplication, Long> {

    FtpApplication findByOrganizationalUnitName(String organizationalUnitName);
}
