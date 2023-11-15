package com.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carros.domain.User;
import com.carros.domain.UserRepository;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = userRep.findByLogin(username);
		
		if(user != null) {
			return user;
			/*
			if(username.equals("user"))
				return User.withUsername(username).password(user.getSenha()).roles("USER").build();
			else if(username.equals("admin"))
				return User.withUsername("admin").password(user.getSenha()).roles("USER","ADMIN").build();
			else if(username.equals("vmalcolm"))
				return User.withUsername("vmalcolm").password(user.getSenha()).roles("USER","ADMIN").build();
				*/
		}

		throw new UsernameNotFoundException("Usuário não encontrado");
	}

}
