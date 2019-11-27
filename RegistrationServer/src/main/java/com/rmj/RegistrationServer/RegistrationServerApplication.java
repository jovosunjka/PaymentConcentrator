package com.rmj.RegistrationServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistrationServerApplication {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 *
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for registration-server.properties or registration-server.yml
		// System.setProperty("spring.config.name", "registration-server");

		SpringApplication.run(RegistrationServerApplication.class, args);
	}
}
