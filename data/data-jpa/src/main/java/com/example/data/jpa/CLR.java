package com.example.data.jpa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.data.jpa.model.Author;
import com.example.data.jpa.model.Book;

import com.example.data.jpa.model.Category;
import com.example.data.jpa.model.Product;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class CLR implements CommandLineRunner {

	private final AuthorRepository authorRepository;

	private final BookRepository bookRepository;

	ProductRepository productRepository;

	CategoryRepository categoryRepository;

	private final EntityManager entityManager;

	CLR(AuthorRepository authorRepository, BookRepository bookRepository, ProductRepository productRepository,
			CategoryRepository categoryRepository, EntityManager entityManager) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void run(String... args) {
		var authors = insertAuthors();
		listAllAuthors();
		findById(authors);
		findByPartialName();
		queryFindByName();
		deleteAll();
		entityGraph();
		lazyLoading();
	}

	private void deleteAll() {
		this.authorRepository.deleteAll();
		long count = this.authorRepository.count();
		System.out.printf("deleteAll(): count = %d%n", count);
	}

	private void queryFindByName() {
		Author author1 = this.authorRepository.queryFindByName("Josh Long").orElse(null);
		Author author2 = this.authorRepository.queryFindByName("Martin Kleppmann").orElse(null);

		System.out.printf("queryFindByName(): author1 = %s%n", author1);
		System.out.printf("queryFindByName(): author2 = %s%n", author2);
	}

	private void findByPartialName() {
		Author author1 = this.authorRepository.findByNameContainingIgnoreCase("sh lo").orElse(null);
		Author author2 = this.authorRepository.findByNameContainingIgnoreCase("in kl").orElse(null);

		System.out.printf("findByPartialName(): author1 = %s%n", author1);
		System.out.printf("findByPartialName(): author2 = %s%n", author2);
	}

	private void findById(List<Author> authors) {
		Author author1 = this.authorRepository.findById(authors.get(0).getId()).orElse(null);
		Author author2 = this.authorRepository.findById(authors.get(1).getId()).orElse(null);

		System.out.printf("findById(): author1 = %s%n", author1);
		System.out.printf("findById(): author2 = %s%n", author2);
	}

	private void entityGraph() {

		Book book = new Book(null, "Spring in Action");
		Author author = new Author(null, "Craig Walls", Collections.emptySet());
		book.getAuthors().add(author);
		bookRepository.save(book);

		Book loaded = bookRepository.findByTitleWithAdHocGraph("Spring in Action");
		System.out.println("namedEntityGraph: " + loaded);
	}

	private void listAllAuthors() {
		List<Author> authors = this.authorRepository.findAll();
		for (Author author : authors) {
			System.out.printf("listAllAuthors(): author = %s%n", author);
			for (Book book : author.getBooks()) {
				System.out.printf("\t%s%n", book);
			}
		}
	}

	private List<Author> insertAuthors() {
		Author author1 = this.authorRepository.save(new Author(null, "Josh Long",
				Set.of(new Book(null, "Reactive Spring"), new Book(null, "Cloud Native Java"))));
		Author author2 = this.authorRepository.save(
				new Author(null, "Martin Kleppmann", Set.of(new Book(null, "Designing Data Intensive Applications"))));

		System.out.printf("insertAuthors(): author1 = %s%n", author1);
		System.out.printf("insertAuthors(): author2 = %s%n", author2);

		return Arrays.asList(author1, author2);
	}

	private void lazyLoading() {

		Product product = new Product();
		product.setName("lazy-loading-proxy-works :)");
		productRepository.save(product);
		Category category = new Category(product);
		categoryRepository.save(category);

		productRepository.flush();
		categoryRepository.flush();

		entityManager.flush();

		clearHibernateCache();
		Optional<Category> loaded = categoryRepository.findAll().stream().findFirst();

		System.out.println(loaded.get().getProduct().getName());
	}

	public void clearHibernateCache() {
		Session s = (Session) entityManager.getDelegate();

		SessionFactory sf = s.getSessionFactory();
		sf.getCache().evictEntityData();

		s.clear();
	}

}
