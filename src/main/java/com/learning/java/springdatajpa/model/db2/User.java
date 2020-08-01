package com.learning.java.springdatajpa.model.db2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class User {

	@Id
	@Column(name = "ID")
	private int id;
	@Column(name = "DEPT")
	private String dept;
	@Column(name = "NAME")
	private String name;
	@Column(name = "SALARY")
	private int salary;
	
}
