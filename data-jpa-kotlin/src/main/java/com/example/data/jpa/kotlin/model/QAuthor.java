package com.example.data.jpa.kotlin.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import javax.annotation.processing.Generated;

import org.springframework.aot.hint.annotation.Reflective;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.dsl.PathInits;

/**
 * QAuthor is a Querydsl query type for Author
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
@Reflective
public class QAuthor extends EntityPathBase<Author> {

	private static final long serialVersionUID = 898054293L;

	public static final QAuthor author = new QAuthor("author");

	public final SetPath<Book, QBook> books = this.<Book, QBook>createSet("books", Book.class, QBook.class,
			PathInits.DIRECT2);

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath name = createString("name");

	public QAuthor(String variable) {
		super(Author.class, forVariable(variable));
	}

	public QAuthor(Path<? extends Author> path) {
		super(path.getType(), path.getMetadata());
	}

	public QAuthor(PathMetadata metadata) {
		super(Author.class, metadata);
	}

}
