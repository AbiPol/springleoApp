package com.curso.springleo.model;

public class UserCredentials {

	// Definimos los campos de esta clase
	private String user;
	private String password;

	public UserCredentials(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public UserCredentials() {
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserCredentials [user=" + user + ", password=" + password + "]";
	}

}
