package com.curso.springleo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/*
 * Controller para el formulario de contacto
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.curso.springleo.constant.ViewConstant;
//import com.curso.springleo.entity.User;
import com.curso.springleo.model.ContactModel;
import com.curso.springleo.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final Log LOG = LogFactory.getLog(ContactController.class);
	// Inyectamos el servicio en el controlador.
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;

	//El contactform nos puede venir desde añadir contacto o desde modificar contacto
	//Preautorize sirve para dejar paso solo a aquel usuario logueado con este rol
	// Tambbien se pueden formar lo siguiente - "hasRole('ROLE_USER') or/and "hasRole('ROLE_ADMIN')"
	//                                        - "PermitAll()"
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/contactform")
	public String redirectContactForm(Model model,
			@RequestParam(name = "id", required = false) int id) {
		//Definimos un contacto nuevo en blanco
		ContactModel contactModel = new ContactModel();
		
		if (id != 0) {
			//Si nos llega un id buscamos la info y la llevamos hasta la vista.
			contactModel = contactService.buscarPorIdModel(id);
		}
		
		// Este metodo gestiona un formulario le debemos enviar un atributo Model inicializado
		model.addAttribute("contactModel", contactModel);
		return ViewConstant.CONTACT_FORM;
	}

	@GetMapping("/showform")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		//Obtenemos en nombre del usuario logeado para pasarlo al formulario de la vista de contactos.
		//Tratamos con el objeto User de Spring, no con el de nuestra entidad
		org.springframework.security.core.userdetails.User user = 
				(org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Enviamos el usuario principal logeado a la vista
		mav.addObject("username", user.getUsername());
		mav.addObject("contacts", contactService.allContacts());
		return mav;
	}

	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showform";
		// return ViewConstant.CONTACTS;
	}

	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name = "contactModel") ContactModel contactModel, Model model) {
		LOG.info("Añadiendo Contacto en elformulario--> Datos recibidos: " + contactModel.toString());

		if (null != contactService.addContacts(contactModel)) {
			model.addAttribute("result", 1);// Enviamos un atributo a la vista para que nos muestre un mensaje de OK
		} else {
			model.addAttribute("result", 0);// Enviamos un atributo a la vista para que nos muestre un mensaje de ERROR
		}
		return "redirect:/contacts/showform";
		// return ViewConstant.CONTACTS;
	}

	// Recibimos un parametro 'ID' por la URL, lo recogemos y lo enviamos al
	// service.
	// devolvemos el modelandview del showform
	// Un modelandview devuelve la vista y los datos del modelo
	@GetMapping("/removecontacts")
	public ModelAndView removeContacts(@RequestParam(name = "id", required = true) int id) {
		contactService.removeContacts(id);
		return showForm();
	}

}
