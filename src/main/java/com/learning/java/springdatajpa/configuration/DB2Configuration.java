package com.learning.java.springdatajpa.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.learning.java.springdatajpa.db2repository", entityManagerFactoryRef = "db2EntityManager", transactionManagerRef = "db2TransactionManager")
public class DB2Configuration {

	@Value("${spring.db2.dialect}")
	private String dialect;
	
	@Value("${spring.db2.schema}")
	private String schema;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.db2")
	public DataSource db2DataSource() {
		return DataSourceBuilder.create().build();
	}

	// db2EntityManager bean
	@Bean
	public LocalContainerEntityManagerFactoryBean db2EntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(db2DataSource());
		em.setPackagesToScan(new String[] { "com.learning.java.springdatajpa.model.db2" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.default_schema", schema);
		properties.put("hibernate.dialect", dialect);
		em.setJpaPropertyMap(properties);

		return em;
	}

	// db2TransactionManager bean
	@Bean
	public PlatformTransactionManager db2TransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(db2EntityManager().getObject());
		return transactionManager;
	}
}