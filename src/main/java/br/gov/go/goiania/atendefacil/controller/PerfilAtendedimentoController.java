package br.gov.go.goiania.atendefacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.goiania.atendefacil.domain.PerfilAtendimento;
import br.gov.go.goiania.atendefacil.dto.PerfilAtendimentoDto;
import br.gov.go.goiania.atendefacil.service.PerfilAtendimentoService;

@RestController
@RequestMapping("/atendeFacil/api/perfilatendimento")
public class PerfilAtendedimentoController {
	
	@Autowired
	private PerfilAtendimentoService pas;
	
	@GetMapping()
	public ResponseEntity<Iterable<PerfilAtendimento>> listarPerfil(){
		return ResponseEntity.ok(pas.listarPerfilAtendimento());
	}
	
	@GetMapping("/{matricula}")
	public ResponseEntity<List<PerfilAtendimentoDto>> listarPerfil(@PathVariable("matricula")  String matricula){
		List<PerfilAtendimentoDto> perfil =  pas.listarPerfil(matricula);
		return  perfil.isEmpty()?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(perfil);
	}
	
	
	@GetMapping("/gerarPerfil") 
	@ResponseStatus(HttpStatus.CREATED)
	public void gerarPerfil(@RequestParam("matricula") String matricula, 
							@RequestParam("servico") Long servico) {
		pas.gerarPerfil(matricula, servico);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		pas.delete(id);
	}
}
