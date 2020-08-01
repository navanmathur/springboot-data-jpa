package com.learning.java.springdatajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.java.springdatajpa.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findAll();
	
	@Query("FROM User WHERE name = ?1")
	List<User> findByName(String name);

}
