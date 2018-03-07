package com.curso.springleo.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.curso.springleo.controller.LoginController;
import com.curso.springleo.entity.User;
import com.curso.springleo.entity.UserRole;
import com.curso.springleo.repository.UserRepository;


//UserDetailService spring lo tiene para autenticar usuarios
@Service("userService")
public class UserService implements UserDetailsService{
	
	private static final Log LOG = LogFactory.getLog(UserService.class);
	
	@Autowired 
	@Qualifier("userRepository")
	private UserRepository userRepository;

	/*
	 * Este metodo se va a encargar de ir a nuestro repositorio y busca findByUsername, ya que recibe por parametro un string con el username.
	 * Nuestro repositorio devuleve una entidad User que debemos convertir en un USerDetails. Para ello se crean dos metodos privados
	 * 
	 */
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
				
		//construye un user con sus roles.
		return buildUser(user, authorities);
	}
	
	/*
	 * Se encarga de construir el usuario 
	 */
	private org.springframework.security.core.userdetails.User buildUser (User user, List<GrantedAuthority> authorities) {
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isDisponible(), 
				                                                       true, true, true, authorities);
	}
	
	/*
	 * convierte el roles en una lista del objeto grantedAuthority
	 * List<GrantedAuthority> es el objeto que spring security necesita para saber los roles que tiene nuestra aplicacion
	 * Se el pasan por parametro los roles del usuario que esta en la entidad User
	 * Coge los roles del usuario de la tabla y lo transforma en una lista de grantedAuthority que le sirve a spring
	 * para saber los roles que tiene el usuario conectado.
	 */
	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles){
		
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		//Nos recoremos mediante un for el UserRole. Por cada role del usuario lo añadimos a la lista auths 
		
		for(UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		
		//Retornamos un arraylist con los roles de un usuario
		LOG.info("--UserSevice-- devuelve: " + auths + "." );
		return new ArrayList<GrantedAuthority>(auths);
	}

}
