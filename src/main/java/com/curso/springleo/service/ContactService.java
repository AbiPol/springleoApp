package com.curso.springleo.service;

import java.util.List;

import com.curso.springleo.model.ContactModel;

public interface ContactService {
	//devolvemos un ContactModel que es lo que entiende el controller
	public abstract ContactModel addContacts(ContactModel contactsModel);
	
	//Interfaz que nos devuelva todos los contactos que tengamos en la BD
	public abstract List<ContactModel> allContacts();
}
