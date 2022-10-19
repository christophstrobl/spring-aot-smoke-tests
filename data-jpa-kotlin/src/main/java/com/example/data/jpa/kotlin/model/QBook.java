package com.example.data.jpa.kotlin.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import javax.annotation.processing.Generated;

import org.springframework.aot.hint.annotation.Reflective;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
@Reflective
public class QBook extends EntityPathBase<Book> {

	private static final long serialVersionUID = 1918274675L;

	public static final QBook book = new QBook("book");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath title = createString("title");

	public QBook(String variable) {
		super(Book.class, forVariable(variable));
	}

	public QBook(Path<? extends Book> path) {
		super(path.getType(), path.getMetadata());
	}

	public QBook(PathMetadata metadata) {
		super(Book.class, metadata);
	}

}
