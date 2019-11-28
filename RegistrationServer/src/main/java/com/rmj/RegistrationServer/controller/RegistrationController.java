package com.rmj.RegistrationServer.controller;

import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import com.rmj.RegistrationServer.dto.PaymentTypeDTO;
import com.rmj.RegistrationServer.dto.PaymentTypesDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {

    @RequestMapping(value = "/microservices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentTypesDTO> getPaymentTypes() {
        List<PaymentTypeDTO> types = new ArrayList<PaymentTypeDTO>();
        // paymentTypes.add(new PaymentTypeDTO("aaa", "aaa_url"));
        // paymentTypes.add(new PaymentTypeDTO("aaa", "bbb_url"));

        PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
        Applications applications = registry.getApplications();

        applications.getRegisteredApplications().forEach((registeredApplication) -> {
            registeredApplication.getInstances().forEach((instance) -> {
                types.add(new PaymentTypeDTO(instance.getAppName(), instance.getHomePageUrl()));
                System.out.println(instance.getAppName() + " (" + instance.getHomePageUrl() + ")");
            });
        });

        PaymentTypesDTO paymentTypes = new PaymentTypesDTO(types);
        return new ResponseEntity<PaymentTypesDTO>(paymentTypes, HttpStatus.OK);
    }
}
