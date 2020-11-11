package br.gov.go.goiania.atendefacil.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.goiania.atendefacil.domain.Calendario;
import br.gov.go.goiania.atendefacil.service.CalendarioService;

@RestController
@RequestMapping("/atendeFacil/api/calendario")
public class CalendarioController {
	
	@Autowired
	private CalendarioService cs;
	
	@GetMapping()
	public ResponseEntity<Iterable<Calendario>> listarCalendario() {
		return ResponseEntity.ok(cs.listarCalendario());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Calendario> listarCalendario(@PathVariable("id") Long id){
		Optional<Calendario> calendario = cs.listCalendarioById(id);
		return calendario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void gravar(@RequestBody Calendario calendario) {
		cs.gravar(calendario);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterar(@RequestBody Calendario calendario) {
		cs.alterar(calendario);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		cs.delete(id);
	}
}
