package com.example.data.jpa.kotlin;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

import com.example.data.jpa.kotlin.DataJpaKotlinApplication.Hints;
import com.example.data.jpa.kotlin.model.QAuthor;
import com.example.data.jpa.kotlin.model.QBook;

@SpringBootApplication
@ImportRuntimeHints(Hints.class)
public class DataJpaKotlinApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DataJpaKotlinApplication.class, args);
		Thread.currentThread().join(); // To be able to measure memory consumption
	}

	static class Hints implements RuntimeHintsRegistrar {

		@Override
		public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
			hints.reflection().registerType(QAuthor.class, MemberCategory.INVOKE_PUBLIC_METHODS,
					MemberCategory.DECLARED_FIELDS);
			hints.reflection().registerType(QBook.class, MemberCategory.INVOKE_PUBLIC_METHODS,
					MemberCategory.DECLARED_FIELDS);
		}

	}

}
