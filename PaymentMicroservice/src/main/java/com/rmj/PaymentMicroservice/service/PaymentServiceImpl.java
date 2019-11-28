package com.rmj.PaymentMicroservice.service;

import com.netflix.discovery.shared.Applications;
import com.rmj.PaymentMicroservice.dto.PaymentTypeDTO;
import com.rmj.PaymentMicroservice.dto.PaymentTypesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final String microservicesUrl = "http://localhost:1111/registration/microservices";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<PaymentTypeDTO> getPaymentTypes() {
        List<PaymentTypeDTO> paymentTypes = new ArrayList<PaymentTypeDTO>();
        // paymentTypes.add(new PaymentTypeDTO("aaa", "aaa_url"));
        // paymentTypes.add(new PaymentTypeDTO("aaa", "bbb_url"));

        // PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
        ResponseEntity<PaymentTypesDTO> responseEntity = restTemplate.getForEntity(microservicesUrl, PaymentTypesDTO.class);
        return responseEntity.getBody().getTypes();
    }
}
