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

import br.gov.go.goiania.atendefacil.domain.DetalhamentoServico;
import br.gov.go.goiania.atendefacil.service.DetalhamentoServicoService;

@RestController
@RequestMapping("/atendeFacil/api/detalhamentoservico")
public class DetalhamentoServicoController {

	@Autowired
	private DetalhamentoServicoService dss;
	
	@GetMapping()
	public ResponseEntity<Iterable<DetalhamentoServico>> listarDetServico(){
		return ResponseEntity.ok(dss.listarServico());
	}
	
	@GetMapping("/select/{servico}")
	public ResponseEntity<Iterable<DetalhamentoServico>> selectDetServico(@PathVariable("servico") String servico){
		
		Iterable<DetalhamentoServico> select = null;
		
		if( servico.equals("GERAL")) {
			select = dss.listarServico();
		} else if(servico.equals("Geral")){
			select = dss.listarServico();
		} else {
			select = dss.selectDetServico(servico);
		}
		
		return  ResponseEntity.ok(select);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhamentoServico> listarDetServico(@PathVariable("id") Long id){
		Optional<DetalhamentoServico> detServico = dss.listarDetServicoById(id);
		return detServico.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void gravar(@RequestBody DetalhamentoServico detServico) {
		dss.gravar(detServico);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterar(@RequestBody DetalhamentoServico detServico) {
		dss.alterar(detServico);
	}
}
