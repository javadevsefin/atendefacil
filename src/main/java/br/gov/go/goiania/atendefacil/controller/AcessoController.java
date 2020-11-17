package br.gov.go.goiania.atendefacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.goiania.atendefacil.domain.Acesso;
import br.gov.go.goiania.atendefacil.service.AcessoService;

@RestController
@RequestMapping("/atendeFacil/api/acesso")
public class AcessoController {
	
	@Autowired
	private AcessoService as;
	
	@GetMapping()
	public Iterable<Acesso> listarAcesso(){
		return as.listarAcesso();
	}
}
