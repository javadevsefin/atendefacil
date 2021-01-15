package br.gov.go.goiania.atendefacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.goiania.atendefacil.service.RelatoriosService;

@RestController
@RequestMapping("/atendeFacil/api/relatorios")
public class RelatoriosController {
	
	@Autowired
	private RelatoriosService rs;
	
	@GetMapping("/grade/unidade")
	public ResponseEntity<Object[]> listarUnidade(
			@RequestParam(value ="unidade", required = false, defaultValue="") String unidade,
			@RequestParam(value ="dataInicial", required = false, defaultValue="") String dataInicial,
			@RequestParam(value ="dataFinal", required = false, defaultValue="") String dataFinal,
			@RequestParam(value ="servico", required = false, defaultValue="") String servico){
		return ResponseEntity.ok(rs.listarQuanUnidade("%" + unidade + "%",  dataInicial ,  dataFinal , "%" + servico + "%")); 
	}
	
	@GetMapping("/grade/servico")
	public  ResponseEntity<Object[]> listarServico(
			@RequestParam(value ="unidade", required = false, defaultValue="") String unidade,
			@RequestParam(value ="dataInicial", required = false, defaultValue="") String dataInicial,
			@RequestParam(value ="dataFinal", required = false, defaultValue="") String dataFinal,
			@RequestParam(value ="servico", required = false, defaultValue="") String servico){
		return ResponseEntity.ok(rs.listarQuanServico("%" + unidade + "%",  dataInicial ,  dataFinal , "%" + servico + "%")); 
	}
	
	@GetMapping("/agendamento/unidade")
	public ResponseEntity<Object[]> listarAgendUnidade(
			@RequestParam(value ="unidade", required = false, defaultValue="") String unidade,
			@RequestParam(value ="dataInicial", required = false, defaultValue="") String dataInicial,
			@RequestParam(value ="dataFinal", required = false, defaultValue="") String dataFinal,
			@RequestParam(value ="servico", required = false, defaultValue="") String servico,
			@RequestParam(value ="statusAgendamento", required = false, defaultValue="") String statusAgendamento) {
		return ResponseEntity.ok(rs.listarQuantAgendUnidade("%" + unidade + "%",  dataInicial ,  dataFinal , "%" + servico + "%", "%" + statusAgendamento + "%"));
	}
	
	@GetMapping("/agendamento/servico")
	public ResponseEntity<Object[]> listarAgendServico(
			@RequestParam(value ="unidade", required = false, defaultValue="") String unidade,
			@RequestParam(value ="dataInicial", required = false, defaultValue="") String dataInicial,
			@RequestParam(value ="dataFinal", required = false, defaultValue="") String dataFinal,
			@RequestParam(value ="servico", required = false, defaultValue="") String servico,
			@RequestParam(value ="statusAgendamento", required = false, defaultValue="") String statusAgendamento) {
		return ResponseEntity.ok(rs.listarQuantAgendServico("%" + unidade + "%",  dataInicial ,  dataFinal , "%" + servico + "%", "%" + statusAgendamento + "%"));
	}
}
