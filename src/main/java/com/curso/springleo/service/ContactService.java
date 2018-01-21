package com.curso.springleo.service;

import com.curso.springleo.model.ContactModel;

public interface ContactService {
	//devolvemos un ContactModel que es lo que entiende el controller
	public abstract ContactModel addContacts(ContactModel contactsModel);
}
