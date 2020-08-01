package com.learning.java.springdatajpa.db2repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.java.springdatajpa.model.db2.User;

public interface DB2Repository extends JpaRepository<User, Integer> {

}
