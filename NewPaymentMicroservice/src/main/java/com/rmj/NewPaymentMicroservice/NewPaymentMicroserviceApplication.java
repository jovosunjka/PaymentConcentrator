package com.rmj.NewPaymentMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NewPaymentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewPaymentMicroserviceApplication.class, args);
	}

}
