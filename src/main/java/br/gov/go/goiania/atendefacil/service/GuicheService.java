package br.gov.go.goiania.atendefacil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.goiania.atendefacil.domain.Guiche;
import br.gov.go.goiania.atendefacil.repository.GuicheRepository;

@Service
public class GuicheService {
	
	@Autowired
	private GuicheRepository gr;
	
	public Iterable<Guiche> listarGuiche(){
		return gr.findAll();
	}

	public Optional<Guiche> listGuicheById(Long id) {
		return gr.findById(id);
	}
	
	public void gravar(Guiche guiche) {
		gr.save(guiche);
	}
	
	public void alterar(Guiche guiche) {
		Optional<Guiche> g = gr.findById(guiche.getId());
		if(g.isPresent()) {
			gr.save(guiche);
		}
	}

	public void delete(Long id) {
		Optional<Guiche> g = this.listGuicheById(id);
		if(g.isPresent()) {
			gr.deleteById(id);
		}
	}
}
