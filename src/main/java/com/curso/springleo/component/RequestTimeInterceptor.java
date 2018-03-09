/*
 * Es una clase interceptor, lo que hace es que por cada peticion que se haga va a entrar por estas clases
 * Es un interceptor de peticiones. Cada vez que haya una peticion HTTP pasa por aqui e intercepta la transaccion
 */
package com.curso.springleo.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.curso.springleo.repository.LogRepository;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter  {

	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	
	//Definimos-habilitamos el LOG
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);
		
	//PRIMERO
	//Este metodo se ejecuta antes de entrar en el metodo del controlador
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//SetAttribute añade un atributo a vble. request que nos viene como parametro. 
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}
	
	//SEGUNDO
	//Se ejecuta justo antes de enviar la vista al navegador, es decir, antes del return del metodo del controlador
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		Long starTime = (Long) request.getAttribute("startTime");
		
		String url = request.getRequestURL().toString();
		Date date = new Date();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String username = "";
		if (auth != null && auth.isAuthenticated()) {
			username = auth.getName();
		}   
		
		String details = auth.getDetails().toString();
		
		com.curso.springleo.entity.Log log = new com.curso.springleo.entity.Log(date, details, username, url);
		
		//Para insertar en la entidad Log
		logRepository.save(log);
		
		LOG.info("'-- REQUEST URL: '" + url + "' -- TOTALTIME: '" + (System.currentTimeMillis() - starTime) + " ' ms." );
		
	}

}
