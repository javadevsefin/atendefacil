package br.gov.go.goiania.atendefacil.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.goiania.atendefacil.domain.Painel01;
import br.gov.go.goiania.atendefacil.service.PainelService;

@RestController
@RequestMapping("/atendeFacil/api/painel")
public class PainelController {
	
	@Autowired
	private PainelService ps;

	
	@GetMapping("/01")
	public ResponseEntity<Iterable<Painel01>> listarPainel01(){
		return ResponseEntity.ok(ps.listarPainel01());
	}
	
	@GetMapping("/01/chamada")
	public ResponseEntity<Optional<Painel01>> listarChamada(){
		return ResponseEntity.ok(ps.listarChamada());
	}

}
