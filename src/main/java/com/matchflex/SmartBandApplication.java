package com.matchflex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.matchflex.security")
public class SmartBandApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmartBandApplication.class, args);
	}

}
	