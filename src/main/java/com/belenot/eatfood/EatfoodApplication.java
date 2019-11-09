package com.belenot.eatfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableWebSecurity
public class EatfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatfoodApplication.class, args);
	}

}
