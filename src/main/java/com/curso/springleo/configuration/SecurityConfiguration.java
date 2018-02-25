package com.curso.springleo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity  /*Para integrar y poner disponible el security en spring*/
@EnableGlobalMethodSecurity(prePostEnabled=true) //Es una anotacion que nos permite crear anotaciones para el acceso a los 
                                                 // y diferentes metodos y recursos del aplicativo dependiendo de los roles que tenga 
                                                 //el usuario conectado 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	//Importamos nuestro service
	@Autowired
	@Qualifier("userService")
	private UserDetailsService userService;
	
	
    @Autowired
	// Metodo que se implementa de la clase que se extiende y se sobreescribe aqui
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .antMatchers("/css/*", "/imgs/*").permitAll() //request autorizadas sin necesidad de ningun login. Perimite que se 
                                                          //cargen los css e imagenes estaticos
		    .anyRequest().authenticated()  //El resto de request con autenticacion
            .and()  //Añadimos mas
	    .formLogin()
	        .loginPage("/login").loginProcessingUrl("/logincheck") //la pagina que muestra el login es la '/login' 
	                                                               //y es procesada por '/logincheck'
	        .usernameParameter("username").passwordParameter("password")
	        .defaultSuccessUrl("/loginsuccess").permitAll() //Una vez autenticado el usuario, a que pagina va a ir el aplicativo
	        .and() //añadimos mas
	    .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")//Cuando recibe una url 'logout', esto envia al controler la url
	                                                                    // 'login?logout' que es gestionada por le controlador 'LoginControler'
	    .permitAll();
	}
	
}
