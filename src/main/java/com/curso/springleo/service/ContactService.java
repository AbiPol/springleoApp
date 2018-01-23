package com.curso.springleo.service;

import java.util.List;

import com.curso.springleo.entity.Contacts;
import com.curso.springleo.model.ContactModel;

public interface ContactService {
	//devolvemos un ContactModel que es lo que entiende el controller
	public abstract ContactModel addContacts(ContactModel contactsModel);
	
	//Interfaz que nos devuelva todos los contactos que tengamos en la BD
	public abstract List<ContactModel> allContacts();
	
	//Realizamos una busqueda de un registro por ID, como parametro recibe un id
	public abstract Contacts buscaPorId(int id);
	
	//Borramos el registro con ID que nos envian por parametro
	public abstract void removeContacts(int id);

	public abstract ContactModel buscarPorIdModel(int id);
}