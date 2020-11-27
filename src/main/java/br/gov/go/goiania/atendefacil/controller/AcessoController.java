package br.gov.go.goiania.atendefacil.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.goiania.atendefacil.domain.Acesso;
import br.gov.go.goiania.atendefacil.service.AcessoService;

@RestController
@RequestMapping("/atendeFacil/api/acesso")
public class AcessoController {
	
	@Autowired
	private AcessoService as;
	
	@GetMapping()
	public ResponseEntity<Iterable<Acesso>> listarAcesso(){
		return ResponseEntity.ok(as.listarAcesso());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Acesso> listarAcesso(@PathVariable("id") Long id){
		Optional<Acesso> acesso = as.listAcessoById(id);
		return acesso.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void gravar(@RequestBody Acesso acesso) {
		as.gravar(acesso);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterar(@RequestBody Acesso acesso) {
		as.alterar(acesso);
	}
}
