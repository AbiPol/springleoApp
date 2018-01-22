package com.curso.springleo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.curso.springleo.converter.ContactConverter;
import com.curso.springleo.entity.Contacts;
import com.curso.springleo.model.ContactModel;
import com.curso.springleo.repository.ContactRepository;
import com.curso.springleo.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{

	//Inyectamos el repositorio al que enviar los datos
	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	//Inyectamos el converter de entities a model y viceversa.
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	//Este metodo devuelve un objeto ConctactModel porque es el objeto que puede manejar en controller
	@Override
	public ContactModel addContacts(ContactModel contactsModel) {

		Contacts contacts = contactRepository.save(contactConverter.model2Entity(contactsModel));
		
		return contactConverter.entity2Model(contacts);
	}
	
	@Override
	public List<ContactModel> allContacts() {
		//Recogemos todos los contactos que nos envia el repositorio de la BD
		List<Contacts> contacts = contactRepository.findAll();
		//Creamos una lista de ContactModel para pasarlo al controller
		List<ContactModel> contacModel = new ArrayList<ContactModel>();
		//Transformamos todas las entities en models
		for(Contacts contact : contacts) {
			contacModel.add(contactConverter.entity2Model(contact));
		}
		return contacModel;
	}

}
