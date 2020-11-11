package br.gov.go.goiania.atendefacil.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.goiania.atendefacil.domain.Grade;
import br.gov.go.goiania.atendefacil.service.GradeService;

@RestController
@RequestMapping("/atendeFacil/api/grade")
public class GradeController {
	
	@Autowired
	private GradeService gs;
	
	@GetMapping()
	public ResponseEntity<Iterable<Grade>> listarGrade(){
		return ResponseEntity.ok(gs.listarGrade());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Grade> gradeById(@PathVariable("id") Long id) {
		Optional<Grade> grade = gs.gradeById(id);
		return grade.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Grade>> buscaAvancada(
			@RequestParam(value ="unidade", required = false, defaultValue="") String unidade,
			@RequestParam(value ="dataInicial", required = false, defaultValue="") String dataInicial,
			@RequestParam(value ="dataFinal", required = false, defaultValue="") String dataFinal,
			@RequestParam(value ="servico", required = false, defaultValue="") String servico){
		
		return ResponseEntity.ok(gs.buscaAvancada("%" + unidade + "%",  dataInicial ,  dataFinal , "%" + servico + "%"));
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void gravar(@RequestBody Grade grade) {
		gs.gravar(grade);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterar(@RequestBody Grade grade) {
		gs.alterar(grade);
	}
}
