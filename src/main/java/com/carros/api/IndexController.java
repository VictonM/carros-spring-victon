package com.carros.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	//TODO Formas de mapear
	@GetMapping
	//@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return "API de Carros";
		//return "API DE CARROS";
	}
	//TODO Mapeamento por path
	@GetMapping("/teste")
	public String teste() {
		return "Teste Spring 1";
	}
	//TODO Tipos de mapeamento - POST, PUT, DELETE
	@PostMapping
	public String post() {
		return "post Spring 1";
	}
	@PutMapping
	public String put() {
		return "put Spring 1";
	}
	@DeleteMapping
	public String delete() {
		return "delete Spring 1";
	}
	//TODO passando parametros
	@GetMapping("/login")
	public String login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		return "Login: "+login+", Senha: "+senha;
	}
	
	//TODO passando variavel como path
	@GetMapping("/login/{login}/senha/{senha}")
	public String login2(@PathVariable("login") String login, @PathVariable("senha") String senha) {
		return "Login Path: "+login+", Senha Path: "+senha;
	}
	//TODO passando parametros pelo post
	@PostMapping("/login")
	public String loginComPost(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		return "Login: "+login+", Senha: "+senha;
	}
	
	@GetMapping("/userInfo")
	public UserDetails userDetails(@AuthenticationPrincipal UserDetails user) {
		return user;
	}
}
