package com.rmj.PayPalMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PayPalMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayPalMicroserviceApplication.class, args);
	}

}
