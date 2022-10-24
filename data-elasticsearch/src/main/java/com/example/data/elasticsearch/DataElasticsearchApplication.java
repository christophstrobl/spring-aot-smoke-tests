package com.example.data.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;

@SpringBootApplication
@EnableElasticsearchAuditing(auditorAwareRef = "fixedAuditor")
public class DataElasticsearchApplication {

	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(DataElasticsearchApplication.class, args);
		Thread.currentThread().join();
	}

	@Bean
	AuditorAware<String> fixedAuditor() {
		return () -> java.util.Optional.of("Douglas Adams");
	}

}
