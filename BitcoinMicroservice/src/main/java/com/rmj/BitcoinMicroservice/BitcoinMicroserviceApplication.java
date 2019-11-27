package com.rmj.BitcoinMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BitcoinMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinMicroserviceApplication.class, args);
	}

}
