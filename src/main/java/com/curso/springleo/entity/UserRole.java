package com.curso.springleo.entity;

import javax.persistence.Column;
/*
 * Se encarga de almacenar los roles del usuario
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(
		                     columnNames = { "role" , "username" }))
public class UserRole {

	@Id
	@GeneratedValue
	@Column(name="user_role_id", unique=true, nullable= false)
	private Integer userRoleId;
	
	//FetchType.LAZY, significa que al acceder a un usuario no se van a cargar todos sus roles, 
	// solo se cargaran cuando se accedan a ellos en la lectura. Asi no cosumimos recursos del sistema
	//Joincolumn nos dice a que columna se va a unir
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="username" , nullable= false)
	private User user;
	
	@Column(name="role" , nullable= false , length = 45)
	private String role;

	public UserRole() {
	}

	public UserRole(Integer userRoleId, User user, String role) {
		super();
		this.user = user;
		this.role = role;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
