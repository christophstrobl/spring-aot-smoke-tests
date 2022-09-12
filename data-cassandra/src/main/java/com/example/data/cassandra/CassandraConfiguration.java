package com.example.data.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;

import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.cql.generator.CreateKeyspaceCqlGenerator;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;

@Configuration
class CassandraConfiguration {

	@Bean
	CqlSession cqlSession(CqlSessionBuilder cqlSessionBuilder, CassandraProperties properties) {
		// This creates the keyspace on startup
		try (CqlSession session = cqlSessionBuilder.withKeyspace((String) null).build()) {
			session.execute(CreateKeyspaceCqlGenerator
					.toCql(CreateKeyspaceSpecification.createKeyspace(properties.getKeyspaceName()).ifNotExists()));
		}
		return cqlSessionBuilder.withKeyspace(properties.getKeyspaceName()).build();
	}

}
