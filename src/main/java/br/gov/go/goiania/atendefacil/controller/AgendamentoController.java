package br.gov.go.goiania.atendefacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.gov.go.goiania.atendefacil.domain.Agendamento;
import br.gov.go.goiania.atendefacil.dto.AgendamentoDto;
import br.gov.go.goiania.atendefacil.dto.EmissorDeSenhaDto;
import br.gov.go.goiania.atendefacil.service.AgendamentoService;

@RestController
@RequestMapping("/atendeFacil/api/agendamento")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoService as;
	
	@GetMapping()
	public ResponseEntity<Iterable<Agendamento>> listarAgendamento(){
		return ResponseEntity.ok(as.listarAgendamento());
	}
	
	@GetMapping("/busca/{id}")
	public ResponseEntity<Agendamento> agendamentoById(@PathVariable("id") Long id){
		Optional<Agendamento> agendamento = as.AgendamentoById(id);
		return agendamento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/senha/{id}")
	public ResponseEntity<EmissorDeSenhaDto> agendaById(@PathVariable("id") Long id){
		Optional<EmissorDeSenhaDto> agendamento = as.AgendaById(id);
		return agendamento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Agendamento>> buscaAvancada(
			@RequestParam(value ="unidade", required = false, defaultValue="") String unidade,
			@RequestParam(value ="dataInicial", required = false, defaultValue="") String dataInicial,
			@RequestParam(value ="dataFinal", required = false, defaultValue="") String dataFinal,
			@RequestParam(value ="servico", required = false, defaultValue="") String servico,
			@RequestParam(value ="statusAgendamento", required = false, defaultValue="") String statusAgendamento ){
		
		return ResponseEntity.ok(as.buscaAvancada("%" + unidade + "%",  dataInicial ,  dataFinal , "%" + servico + "%", "%" + statusAgendamento + "%" ));
	}
	
	@GetMapping("/reclassificarPrioridade/{id}/{prioridade}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void reclassificarPrioridade(@PathVariable("id") Long id,
										@PathVariable("prioridade") String prioridade) {
		as.reclassifcarPrioridade(id, prioridade);
	}
	
	@GetMapping("/agendar")
	public ResponseEntity<List<Agendamento>> agendaContribuinte(
			@RequestParam(value="calendario", required = false, defaultValue = "") String calendario,
			@RequestParam(value="unidade", required = false, defaultValue = "") String unidade){
		return ResponseEntity.ok(as.agendaContribuinte("%"+ calendario +"%", "%"+ unidade + "%"));
	}
	
	@GetMapping("/agendaPage")
	public Page<Agendamento> agendaPaginada(
			@RequestParam(value="page", defaultValue = "0") Integer page, 
			@RequestParam(value="size", defaultValue = "10") Integer size
			){

		return as.listarAgendamento(page, size);
	}
	
	@GetMapping("/mobile/consulta/{cpfCnpj}")
	public ResponseEntity<List<Agendamento>> findByAgendamentoCpfCnpj(@PathVariable("cpfCnpj") String cpfCnpj){
		return ResponseEntity.ok(as.findByAgendamentoCpfCnpj(cpfCnpj));
	}
	
	@GetMapping("/ativado/{cpfCnpj}")
	public ResponseEntity<List<Agendamento>> findByAgendamentoCpfCnpjAtivado(@PathVariable("cpfCnpj") String cpfCnpj){
		return ResponseEntity.ok(as.findByAgendamentoCpfCnpjAtivado(cpfCnpj));
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void gravar(@PathVariable("id") Long id) {
		as.gerarAgenda(id);
	}
	
	@GetMapping("/agendamentoFila/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void gerarFila(@PathVariable("id") Long id) {
		as.gerarFila(id);
	}
	
	@PatchMapping("/enviar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void enviar(@RequestBody AgendamentoDto agendamentoDto) {
		as.enviar(agendamentoDto);
	}

}
