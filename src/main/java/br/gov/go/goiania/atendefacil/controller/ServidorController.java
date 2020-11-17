package br.gov.go.goiania.atendefacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.gov.go.goiania.atendefacil.domain.Servidor;
import br.gov.go.goiania.atendefacil.service.ServidorService;

@RestController
@RequestMapping("/atendeFacil/api/servidor")
public class ServidorController {
	
	@Autowired
	private ServidorService ss;
	
	@GetMapping()
	public Iterable<Servidor> listarServidor(){
		return ss.listarServidor();
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Servidor>> buscaAvancada(
			@RequestParam(value="matricula",  required = false, defaultValue = "") String matricula,
			@RequestParam(value="nome", required = false, defaultValue = "") String nome,
			@RequestParam(value="cpf", required = false, defaultValue = "") String cpf){
		return ResponseEntity.ok(ss.buscaAvancada("%"+matricula+"%", "%"+nome+"%", "%"+cpf+"%"));
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void gravar(@RequestBody Servidor servidor) {
		ss.gravar(servidor);
	}
}
