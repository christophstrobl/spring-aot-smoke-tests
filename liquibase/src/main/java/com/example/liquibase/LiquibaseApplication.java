package com.example.liquibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiquibaseApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(LiquibaseApplication.class, args);
		Thread.currentThread().join(); // To be able to measure memory consumption
	}

}
