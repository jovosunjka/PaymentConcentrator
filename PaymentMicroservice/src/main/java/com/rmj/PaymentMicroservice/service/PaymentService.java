package com.rmj.PaymentMicroservice.service;

import com.rmj.PaymentMicroservice.dto.PaymentTypeDTO;

import java.util.List;

public interface PaymentService {
    List<PaymentTypeDTO> getPaymentTypes();
}
