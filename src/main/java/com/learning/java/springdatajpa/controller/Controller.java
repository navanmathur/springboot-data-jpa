package com.learning.java.springdatajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.java.springdatajpa.db2repository.DB2Repository;
import com.learning.java.springdatajpa.mysqlrepository.MySQLRepository;

@RestController
public class Controller {

	@Autowired
	private MySQLRepository mysqlrepo;
	
	@Autowired
	private DB2Repository db2repo;
	
	@GetMapping("/users")
	public long getUsers() {
		return mysqlrepo.count();
	}
	
	@GetMapping("/user")
	public long getUser() {
		return db2repo.count();
	}
	
}
