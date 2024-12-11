package com.burhan.creditmanagement.creditapp_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApplicationBff {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBff.class, args);
	}

}
