package com.burhan.creditmanagement.aplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MailBatch {

	public static void main(String[] args) {
		SpringApplication.run(MailBatch.class, args);
	}


}
