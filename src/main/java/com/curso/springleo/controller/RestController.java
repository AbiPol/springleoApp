package com.curso.springleo.controller;

import java.util.Set;
import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.springleo.entity.User;
import com.curso.springleo.entity.UserRole;
import com.curso.springleo.service.imp.UserService;

@org.springframework.web.bind.annotation.RestController
//@Controller
@RequestMapping("/rest")
public class RestController {
	
	private static final Log LOG = LogFactory.getLog(UserService.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@GetMapping("/checkrest")
	public ResponseEntity<String> checkRest() {
		
		return new ResponseEntity<String>("OK!", HttpStatus.OK);
	}
	
	@GetMapping("/checkrestobj")
	public ResponseEntity<Set<String>> checkRestObj() {
		//String datosEnviar = " ";
		//JSONArray datosEnviar = new JSONArray();
		Set<String> datosEnviar = new HashSet<String>();
		
		User usuario = userService.userByUsername("user1");
		
		LOG.info("Usuario: " + usuario + "//");
		for(UserRole userRole : usuario.getUserRole()) {
			//UserRole rolesUser = new UserRole();
			LOG.info("Role de usuario: " + userRole.getRole() + "//");
			//rolesUser.  ("username", userRole.getUser().getUsername());
			//objEnviar.put("role", userRole.getRole());
			//datosEnviar.add(e)   (usuario.getUsername());
			//datosEnviar.add(userRole.getRole());
			datosEnviar.add(userRole.getUser().getUsername());
			datosEnviar.add(userRole.getRole());
		}
		//return datosEnviar;

		return new ResponseEntity<Set<String>>(datosEnviar,HttpStatus.OK);
		
	}

}
