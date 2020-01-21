package com.rmj.PaymentMicroservice.repository;

import com.rmj.PaymentMicroservice.model.FormFieldsForPaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormFieldsForPaymentTypeRepository extends JpaRepository<FormFieldsForPaymentType, Long> {

    Optional<FormFieldsForPaymentType> findByPaymentType(String paymentType);
}
