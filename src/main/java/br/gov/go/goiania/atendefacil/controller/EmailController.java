package br.gov.go.goiania.atendefacil.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.goiania.atendefacil.service.EmailService;

@RestController
@RequestMapping("/atendeFacil/api/email")
public class EmailController {
	
	@Autowired
	private EmailService es; 

	    @GetMapping("/enviar")
	    public void enviar() { 
	    	es.enviar();
	    }
}
