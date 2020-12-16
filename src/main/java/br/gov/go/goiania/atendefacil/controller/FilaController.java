package br.gov.go.goiania.atendefacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.goiania.atendefacil.domain.Fila;
import br.gov.go.goiania.atendefacil.service.FilaService;


@RestController
@RequestMapping("/atendeFacil/api/fila")
public class FilaController {
	
	@Autowired
	private FilaService fs;
	
	
	@GetMapping("/ativados/{unidade}")
	public ResponseEntity<Iterable<Fila>> listarFila(@PathVariable("unidade") String unidade){
		return ResponseEntity.ok(fs.listarFilaAtivados(unidade));
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Fila> listarFilaId(@PathVariable("id") Long id){
		Optional<Fila> fila = fs.findById(id);
		return fila.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/espera/{matricula}/{unidade}")
	public ResponseEntity<List<Fila>> listarFilaEspera(@PathVariable("matricula") String matricula,
													   @PathVariable("unidade") String unidade) {
		return ResponseEntity.ok(fs.listarFilaEspera(matricula, unidade));
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void gravar(@RequestBody Fila fila) {
		fs.gravar(fila);
	}

}
