package br.gov.go.goiania.atendefacil.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.gov.go.goiania.atendefacil.domain.Servico;
import br.gov.go.goiania.atendefacil.service.ServicoService;

@RestController
@RequestMapping("/atendeFacil/api/servico")
public class ServicoController {
	
	@Autowired
	private ServicoService ss;
	
	@GetMapping()
	public ResponseEntity<Iterable<Servico>> listarServico(){
		return ResponseEntity.ok(ss.listarServico());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Servico> listarServico(@PathVariable("id") Long id){
		Optional<Servico> servico = ss.listarServicoById(id);
		return servico.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void gravar(@RequestBody Servico servico) {
			ss.gravar(servico);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterar(@RequestBody Servico servico) {
		ss.alterar(servico);
	}
	
}
