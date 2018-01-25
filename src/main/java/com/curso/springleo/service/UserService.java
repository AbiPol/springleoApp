package com.curso.springleo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
//UserDetailsSeervice, es un servicio que Spring tiene para autenticar usuarios
public class UserService implements UserDetailsService{

	//Este metodo llama al repositorio userRepository hace un findUserByUsername y ns devuelve una 
	//entidad user. Esta entidad debemos de transformarla en UserDetails.
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

}
