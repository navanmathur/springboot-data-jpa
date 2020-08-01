package com.learning.java.springdatajpa.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.learning.java.springdatajpa.mysqlrepository", entityManagerFactoryRef = "mysqlEntityManager", transactionManagerRef = "mysqlTransactionManager")
public class MySQLConfiguration {

	@Value("${spring.mysql.dialect}")
	private String dialect;
	
	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.mysql")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	// mysqlEntityManager bean
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean mysqlEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(mysqlDataSource());
		em.setPackagesToScan(new String[] { "com.learning.java.springdatajpa.model.mysql" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", dialect);
		em.setJpaPropertyMap(properties);

		return em;
	}

	// mysqlTransactionManager bean
	@Primary
	@Bean
	public PlatformTransactionManager mysqlTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(mysqlEntityManager().getObject());
		return transactionManager;
	}
}
