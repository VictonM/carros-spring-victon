package com.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration (enforceUniqueMethods = false)
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter{
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	/*@Bean
	public SecurityFilterChain configure(HttpSecurity http, CustomBasicAuthFilter customBasicAuthFilter) throws Exception {
		http
		.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated())
		.sessionManagement((sm) -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(customBasicAuthFilter, BasicAuthenticationFilter.class);
		return http.build();
	}*/
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated()).httpBasic().and().csrf().disable();

		return http.build();
	}
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
		/*auth.inMemoryAuthentication().passwordEncoder(encoder)
		.withUser("user").password(encoder.encode("user")).roles("USER")
		.and()
		.withUser("admin").password(encoder.encode("admin")).roles("USER","ADMIN");*/
	}
}
