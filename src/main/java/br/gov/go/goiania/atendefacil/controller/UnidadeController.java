package br.gov.go.goiania.atendefacil.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.gov.go.goiania.atendefacil.domain.Unidade;
import br.gov.go.goiania.atendefacil.service.UnidadeService;

@RestController
@RequestMapping("/atendeFacil/api/unidade")
public class UnidadeController {
	
	@Autowired
	private UnidadeService us; 
	
	@GetMapping()
	public ResponseEntity<Iterable<Unidade>>  listarUnidade(){
		return ResponseEntity.ok(us.listarUnidade());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Unidade> listarUnidade(@PathVariable("id") Long id){
		Optional<Unidade> unidade = us.listUnidadeById(id);
		return unidade.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void gravar(@RequestBody Unidade unidade) {
		us.gravar(unidade);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterar(@RequestBody Unidade unidade){
		us.alterar(unidade);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		us.delete(id);
	}
}
