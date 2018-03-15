package com.curso.springleo.repository;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.springleo.entity.UserRole;

@Repository("rolerepository")
public interface RoleRepository extends JpaRepository<UserRole, Serializable> {

	public abstract Set<UserRole> findByUserUsername(String username);
	
}
