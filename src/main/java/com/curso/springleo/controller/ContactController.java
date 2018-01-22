package com.curso.springleo.controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/*
 * Controller para el formulario de contacto
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.curso.springleo.constant.ViewConstant;
import com.curso.springleo.model.ContactModel;
import com.curso.springleo.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	private static final Log LOG = LogFactory.getLog(ContactController.class);
	//Inyectamos el servicio en el controlador.
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;

	@GetMapping("/contactform")
	public String redirectContactForm(Model model) {
		//Este metodo gestiona un formulario le debemos enviar un atributo Model inicializado
		model.addAttribute("contactModel", new ContactModel());

		return ViewConstant.CONTACT_FORM;
	}
	
	@GetMapping("/showform")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		mav.addObject("contacts", contactService.allContacts());
		return mav;
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showform";
		//return ViewConstant.CONTACTS;
	}
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name="contactModel") ContactModel contactModel,
			                 Model model) {
		LOG.info("Añadiendo Contacto en elformulario--> Datos recibidos: " + contactModel.toString());
		
		if( null != contactService.addContacts(contactModel)){
			model.addAttribute("result", 1);//Enviamos un atributo a la vista para que nos muestre un mensaje de OK
		}else {
			model.addAttribute("result", 0);//Enviamos un atributo a la vista para que nos muestre un mensaje de ERROR
		}
		return "redirect:/contacts/showform";
		//return ViewConstant.CONTACTS;
	}
}
