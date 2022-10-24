package com.example.data.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
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

	@Configuration
	static class Config extends ElasticsearchConfiguration {

		@Override
		public ClientConfiguration clientConfiguration() {
			return ClientConfiguration.localhost();
		}

	}

}
