package com.curso.springleo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.springleo.constant.ViewConstant;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	@RequestMapping("/contactfrom")
	private String redirectForm() {
		return ViewConstant.CONTACT_FORM;
	}
	
}
