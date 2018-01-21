package com.curso.springleo.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.curso.springleo.entity.Contacts;
import com.curso.springleo.model.ContactModel;

/*
 * En este componente lo que vamos a hacer es pasar la informacion del model a la entity y viceversa.
 * Vamos a utilizar los metodos getter y setters para pasar la info de una clase a otra.
 * Este componente vamos a inyectarlo en el servicio para coja la entity y lo transforme en model y lo envie al controller
 * y viceversa, coja el model que viene del controller y lo transforme en entity para enviarlo al repitory
 */
@Component("contactConverter")
public class ContactConverter {
	
	private static final Log LOG = LogFactory.getLog(ContactConverter.class);

	//Metodo para pasar de entity --> model
	public ContactModel entity2Model(Contacts contacts) {
		LOG.info("--- Hemos convertido de Contacts a ContactModel");
		ContactModel contactsModel = new ContactModel();
		//Cogemos la info de la clase entity y se la enviamos a la model.
				
		contactsModel.setFirstname(contacts.getFirstname());
		contactsModel.setLastname(contacts.getLastname());
		contactsModel.setTelephone(contacts.getTelephone());
		contactsModel.setCity(contacts.getCity());
		return contactsModel;
	}
	
	//Metodo para pasar de Model --> Entity
	public Contacts model2Entity(ContactModel contactsModel) {
		LOG.info("--- Hemos convertido de ContactModel a Contacts");
		Contacts contacts = new Contacts();
		//Cogemos info de la clase Model y se la enviamos a la Entity
		contacts.setFirstname(contactsModel.getFirstname());
		contacts.setLastname(contactsModel.getLastname());
		contacts.setTelephone(contactsModel.getTelephone());
		contacts.setCity(contactsModel.getCity());
		return contacts;
	}
}
