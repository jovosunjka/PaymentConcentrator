package com.rmj.CardPaymentMicroservice.repository;

import com.rmj.CardPaymentMicroservice.model.FormField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormFieldRepository extends JpaRepository<FormField, Long> {
}
