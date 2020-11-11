package br.gov.go.goiania.atendefacil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.go.goiania.atendefacil.domain.Orgao;
import br.gov.go.goiania.atendefacil.domain.Servico;
import br.gov.go.goiania.atendefacil.repository.OrgaoRepository;
import br.gov.go.goiania.atendefacil.repository.ServicoRepository;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository sr;
	
	@Autowired
	private OrgaoRepository or;
	
	public Iterable<Servico> listarServico(){
		return sr.findAll();
	}
	
	public Optional<Servico> listarServicoById(Long id){
		return sr.findById(id);
	}
	
	public void gravar(Servico servico) {
		Orgao orgao = or.findById(servico.getOrgao().getId()).orElseThrow(()->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Orgão não Encontrado"));
		servico.setOrgao(orgao);
		
		sr.save(servico);
	}
	
	public void alterar(Servico servico) {
		Optional<Servico> s = sr.findById(servico.getId());
		
		Orgao orgao = or.findById(servico.getOrgao().getId()).orElseThrow(()->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Orgão não Encontrado"));
		
		servico.setOrgao(orgao);
		
		
		if(s.isPresent()) {
			sr.save(servico);
		}
		
	}
}
