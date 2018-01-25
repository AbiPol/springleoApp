package com.curso.springleo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.springleo.entity.User;

//La entidad que va a manejar este repositorio es 'User'
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable>{

	public abstract User findByUsername(String username);
}
