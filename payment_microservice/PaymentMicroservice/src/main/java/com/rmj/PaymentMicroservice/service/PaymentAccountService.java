package com.rmj.PaymentMicroservice.service;

import com.rmj.PaymentMicroservice.dto.RegistrationPaymentAccountDTO;
import com.rmj.PaymentMicroservice.model.PaymentAccount;

public interface PaymentAccountService {
	
    PaymentAccount getPaymentAccount(String username);

    PaymentAccount loginPaymentAccount(PaymentAccount paymentAccount);

    void registerPaymentAccount(String paymentType, RegistrationPaymentAccountDTO registrationPaymentAccountDTO);

    PaymentAccount getPaymentAccount(Long id);
}
