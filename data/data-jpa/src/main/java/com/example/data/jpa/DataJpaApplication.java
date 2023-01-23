package com.example.data.jpa;

import java.util.Set;

import com.example.data.jpa.DataJpaApplication.Hints;
import com.example.data.jpa.model.Author;
import com.example.data.jpa.model.Book;
import com.example.data.jpa.model.Category;
import com.example.data.jpa.model.Product;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(Hints.class)
public class DataJpaApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DataJpaApplication.class, args);
		// Thread.currentThread().join(); // To be able to measure memory consumption
	}

	static class Hints implements RuntimeHintsRegistrar {

		@Override
		public void registerHints(RuntimeHints hints, ClassLoader classLoader) {

			hints.reflection().registerType(org.hibernate.proxy.HibernateProxy.class,
					MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.DECLARED_FIELDS,
					MemberCategory.INVOKE_DECLARED_METHODS);
			registerHibernateProxy(hints, Product.class);
			registerHibernateProxy(hints, Book.class);
			registerHibernateProxy(hints, Author.class);
			registerHibernateProxy(hints, Category.class);
		}

		private static void registerHibernateProxy(RuntimeHints hints, Class<?> type) {

			org.springframework.data.jpa.support.hibernate.SpringHibernateProxyFactory springHibernateProxyFactory = new org.springframework.data.jpa.support.hibernate.SpringHibernateProxyFactory();
			springHibernateProxyFactory.postInstantiate(type.getName(), type,
					Set.of(org.hibernate.proxy.HibernateProxy.class), null, null, null);
			springHibernateProxyFactory.getProxy(null, null);
			hints.reflection().registerType(springHibernateProxyFactory.getProxyClass(),
					MemberCategory.INVOKE_DECLARED_CONSTRUCTORS, MemberCategory.DECLARED_FIELDS,
					MemberCategory.INVOKE_DECLARED_METHODS);
		}

	}

}
