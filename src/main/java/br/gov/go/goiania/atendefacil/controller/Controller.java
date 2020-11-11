package br.gov.go.goiania.atendefacil.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class Controller {
	
	@GetMapping
	public String teste() {
		return "ApI eAtendeFacil Subiu";
	}
}
