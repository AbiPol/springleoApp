package com.curso.springleo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name="username" , unique = true , nullable = false , length = 45)
	private String username;
	
	@Column(name="password" , nullable = false , length = 60)
	private String password;
	
	@Column(name = "disponible" , nullable = false)
	private boolean disponible;
	
	// mappedBy Es un parámetro hace referencia a que la relación 
	//ya fue construida por la otra clase “UserRole” a traves de su variable 'user'
	@OneToMany(fetch=FetchType.LAZY , mappedBy = "user")
	// Es un conjunto de elmentos que no se pueden repetir entre ellos.
	// HashSet es la clase que vamos a utilizar para implementar la interfaz SET
	private Set<UserRole> userRole = new HashSet<UserRole>();

	public User() {}

	public User(String username, String password, boolean disponible) {
		super();
		this.username = username;
		this.password = password;
		this.disponible = disponible;
	}

	public User(String username, String password, boolean disponible, Set<UserRole> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.disponible = disponible;
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

}
