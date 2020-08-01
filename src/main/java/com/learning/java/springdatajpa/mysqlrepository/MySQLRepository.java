package com.learning.java.springdatajpa.mysqlrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.java.springdatajpa.model.mysql.User;

public interface MySQLRepository extends JpaRepository<User, Integer>{

}
