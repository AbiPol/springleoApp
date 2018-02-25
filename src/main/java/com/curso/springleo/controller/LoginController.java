package com.curso.springleo.controller;
/*
 * Creamos esta clase para controlar el acceso a la pagina.
 * 1º- Creamos un model para definir los campos y clase que vamos a gestionar desde este controller.
 * 2º- Creamos los metodos que controlan las vistas. Uno que redirecciona, otro que recibe una peticion post 
 *     y otro que envia una peticion get a la vista. Nos e hace una requestMapping ya que esta pagina tiene que ser la primera
 *     en lanzarse y se hace desde localhost:8080/
 * 3º- 
 */
//**********************************************************
//*** Modificacion hecha para incluir el spring security ***
//**********************************************************

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.springleo.constant.ViewConstant;

@Controller
public class LoginController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);
	
	//@GetMapping("/")
	/*public String redirectLogin() {
		LOG.info("METODO: redirectLogin()");
		return "redirect:/login";
	}*/
	
	//@GetMapping("")
	/*public String redirectLoginB() {
		LOG.info("METODO: redirectLoginB()");
		return "redirect:/login";
	}*/
	
	//Este metodo al retornar un string(al retornar un string siempre se gestiona un objeto Model),
	//debe gestionar un objeto Model y enviar a la vista el valor de dos atributos.
	//el 'error' y el 'logout'
	//Los requestparam son los distintos parametros que recibe por URL esta direccion(todo lo que va despues del caracter '?')
	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout) {
		LOG.info(" --- Metodo GetMapping de /login. PARAMETROS: Error= " + error + ", Logout: " + logout);
		//Añadimos el atributo 'error' que esta tambien en la vista y que llevara el valor de la vble 'error' que se le pasa por 
		//parametro que viene del metodo del postmapping.
		model.addAttribute("error", error);
		//Añadios el prametro que nos viene del logout
		model.addAttribute("logout", logout);
		//Este atributo lo añadimos porque el form esta gestionando un objeto UserCredentials, por lo que lo añadimos 
		//como parametro que recibe el metodo.
		//***model.addAttribute("usercredentials", new UserCredentials());
		
		LOG.info("retornamos a /login");
		return ViewConstant.LOGIN;
	}
	//Este metodo es un metodo Post, que recibe de las vistas un objeto "usercredentials" 
	// y es gestionado por el Model del mismo nombre.
	//*** @PostMapping("/logincheck")
	//***public String loginCheck(@ModelAttribute(name="usercredentials") UserCredentials userCredentials) {
	@GetMapping({"/loginsuccess", "/"})//Atiende a dos url's
	public String loginCheck() {
		
		//***LOG.info(" --- Metodo loginCheck() -- PARAMETROS: " + userCredentials.toString());
		LOG.info(" --- Metodo loginCheck() --");
		
		//*** if(userCredentials.getUser().equals("user") && userCredentials.getPassword().equals("user")) {
			
		LOG.info("retornamos a /contacts/showform");
		return "redirect:/contacts/showform";
			//return ViewConstant.CONTACTS;
		//*** }
		//Retorna a la pagina de login con un error como parametro, para que se muestre en pantalla.
		//***LOG.info("retornamos a /login?error");
		//***return "redirect:/login?error";//con esto obligamos al metodo getting a recoger un parametro que viene(?error)
	}
}
