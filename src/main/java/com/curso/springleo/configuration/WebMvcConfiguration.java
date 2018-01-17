/*
 * Esta clase se crea para configurar elementos en esta clase, ya que aveces no nos deja en el archivo Aplication.yml
 */

package com.curso.springleo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.curso.springleo.component.RequestTimeInterceptor;

//Vamos a dar de alta aqui el interceptor que hemos creado (RequestTimeInterceptor),
//para que cada vez que se levante spring sea capaz de configurar el interceptor.
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{

	//Inyectamos el interceptor para poder usarlo en el metodo de configuracion.
	
	@Autowired //Le decimos que vamos a buscar algo dentro de la configuracion de spring
	@Qualifier("requestTimeInterceptor")//Le decimos que eslo que buscamos exactamente.
		
	private RequestTimeInterceptor requestTimeInterceptor;// decalramos una vble. del tipo objeto que hemos inyectado.
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		//Añadimos en el registro nuestro interceptor.
		registry.addInterceptor(requestTimeInterceptor);
	}
	
}
