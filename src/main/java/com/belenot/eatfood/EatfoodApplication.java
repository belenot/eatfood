package com.belenot.eatfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EatfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatfoodApplication.class, args);
	}

}
