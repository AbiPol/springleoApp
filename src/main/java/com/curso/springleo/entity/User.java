package com.curso.springleo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	private String username;
	private String password;
	private boolean disponible;
	private Set<UserRole> userRole = new HashSet<UserRole>();
}
