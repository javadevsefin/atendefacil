package br.gov.go.goiania.atendefacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.goiania.atendefacil.domain.Unidade;
import br.gov.go.goiania.atendefacil.repository.UnidadeRepository;

@Service
public class UnidadeService {
	
	@Autowired
	private UnidadeRepository ur;
	
	public Iterable<Unidade> listarUnidade(){
		return ur.findAll();
	}
	
	public List<Unidade> listarUnidadeGenerica(String role, Long unidadeId){
		
		List<Unidade> saida = null;
		
		if(role.equals("Administrador")) {
			saida = ur.unidadeGeral();
		} else {
			saida = ur.unidadeId(unidadeId);
		}
		
		return saida;
	}

	public void gravar(Unidade unidade) {
		ur.save(unidade);
	}

	public void alterar(Unidade unidade) {
		Optional<Unidade> u = ur.findById(unidade.getId());
		if(u.isPresent()) {
			ur.save(unidade);
		}
	}

	public Optional<Unidade> listUnidadeById(Long id) {
		return ur.findById(id);
	}

	public void delete(Long id) {
		Optional<Unidade> u = this.listUnidadeById(id);
		if(u.isPresent()) {
			ur.deleteById(id);
		}
	}
}
