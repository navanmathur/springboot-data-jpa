package com.learning.java.springdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.java.springdatajpa.model.User;
import com.learning.java.springdatajpa.repository.UserRepository;

@RestController
public class Controller {

	@Autowired
	private UserRepository repo;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return repo.findAll();
	}
	
	@GetMapping("/user")
	public List<User> getUserByName(@RequestParam String name) {
		return repo.findByName(name);
	}
	
}
