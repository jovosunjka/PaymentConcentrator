package com.rmj.PayPalMicroservice.repository;

import com.rmj.PayPalMicroservice.model.FormField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormFieldRepository extends JpaRepository<FormField, Long> {
}
