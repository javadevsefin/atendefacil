package br.gov.go.goiania.atendefacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.goiania.atendefacil.domain.Contribuinte;
import br.gov.go.goiania.atendefacil.repository.ContribuinteRepository;

@Service
public class ContribuinteService {
	
	@Autowired
	private ContribuinteRepository cr;
	
	
	public Iterable<Contribuinte> listarContribuinte(){
			return cr.findAll();
	}
	
	public Optional<Contribuinte> contribuinteById(Long id){
		return cr.findById(id);
	}
	
	public List<Contribuinte> buscaAvancada(String nome, String cpfCnpj){
		return cr.findByNomeCpfCnpj(nome, cpfCnpj);
	}
	
	public Contribuinte login(String cpfCnpj, String senha) {
		return cr.findByLogin(cpfCnpj, senha);
	}
	
	public void gravar(Contribuinte contribuinte) {
		cr.save(contribuinte);
	}
	
	public void alterar(Contribuinte contribuinte) {
		Optional<Contribuinte> c = cr.findById(contribuinte.getId());
		if(c.isPresent()) {
			cr.save(contribuinte);
		}
	}
}
