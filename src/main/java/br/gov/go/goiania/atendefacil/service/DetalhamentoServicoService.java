package br.gov.go.goiania.atendefacil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.go.goiania.atendefacil.domain.DetalhamentoServico;
import br.gov.go.goiania.atendefacil.domain.Servico;
import br.gov.go.goiania.atendefacil.repository.DetalhamentoServicoRepository;
import br.gov.go.goiania.atendefacil.repository.ServicoRepository;

@Service
public class DetalhamentoServicoService {
	
	@Autowired
	private DetalhamentoServicoRepository dsr;
	
	@Autowired
	private ServicoRepository sr;
	
	public Iterable<DetalhamentoServico> listarServico(){
		return dsr.findAll();
	}
	
	public Iterable<DetalhamentoServico> selectDetServico(String servico){
		return dsr.selDetServico(servico);
	}
	
	public Optional<DetalhamentoServico> listarDetServicoById(Long id){
		return dsr.findById(id);
	}
	
	public void gravar(DetalhamentoServico detServico) {
		
		Servico servico = sr.findById(detServico.getServico().getId()).orElseThrow(()->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serviço não Encontrado"));
		
		detServico.setServico(servico);
		
		dsr.save(detServico);
		
	}
	
	public void alterar(DetalhamentoServico detServico) {
		Servico servico = sr.findById(detServico.getServico().getId()).orElseThrow(()->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serviço não Encontrado"));
		
		detServico.setServico(servico);
		
		dsr.save(detServico);
	}
}
