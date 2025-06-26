/*
 * Copyright 2022-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.data.jpa;

//import com.example.data.jpa.DataJpaApplication.Hints;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector.DefaultQueryEnhancerSelector;

@SpringBootApplication
// @ImportRuntimeHints(Hints.class)
public class DataJpaApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DataJpaApplication.class, args);
		Thread.currentThread().join(); // To be able to measure memory consumption
	}

	// static class Hints implements RuntimeHintsRegistrar {
	//
	// @Override
	// public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
	// // hints.reflection()//
	// // .registerType(TypeReference.of(DefaultQueryEnhancerSelector.class),
	// // hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
	// // MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
	// // MemberCategory.INVOKE_PUBLIC_METHODS));
	// }
	//
	// }

}
