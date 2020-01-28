package com.rmj.BitcoinMicroservice.repository;

import com.rmj.BitcoinMicroservice.models.FormField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormFieldRepository extends JpaRepository<FormField, Long> {
}
