package br.gov.go.goiania.atendefacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.goiania.atendefacil.domain.Acesso;
import br.gov.go.goiania.atendefacil.repository.AcessoRepository;

@Service
public class AcessoService {
	
	@Autowired
	private AcessoRepository ar;
	
	public Iterable<Acesso> listarAcesso(){
		return ar.findAll(); 
	}
}
