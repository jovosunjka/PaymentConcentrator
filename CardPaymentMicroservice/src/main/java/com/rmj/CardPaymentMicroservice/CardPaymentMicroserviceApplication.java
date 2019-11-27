package com.rmj.CardPaymentMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CardPaymentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardPaymentMicroserviceApplication.class, args);
	}

}
