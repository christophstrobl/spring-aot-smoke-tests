package com.example.cloud.task;

import org.springframework.aot.smoketest.thirdpartyhints.HikariRuntimeHints;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@EnableTask
@ImportRuntimeHints(HikariRuntimeHints.class)
public class CloudTaskApplication {

	@Bean
	public ApplicationRunner successfulTask() {
		return args -> System.out.println("Task ran!");
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(CloudTaskApplication.class, args);
		Thread.currentThread().join(); // To be able to measure memory consumption
	}

}
