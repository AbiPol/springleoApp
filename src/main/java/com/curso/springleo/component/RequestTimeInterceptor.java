/*
 * Es una clase interceptor, lo que hace es que por cada peticion que se haga va a entrar por estas clases
 */
package com.curso.springleo.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter  {

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
		
		LOG.info("'-- REQUEST URL: '" + request.getRequestURL().toString() + "' -- TOTALTIME: '" 
		                                           + (System.currentTimeMillis() - starTime) + " ' ms." );
		
	}

}
