package br.gov.go.goiania.atendefacil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.goiania.atendefacil.domain.Calendario;
import br.gov.go.goiania.atendefacil.repository.CalendarioRepository;

@Service
public class CalendarioService {
	
	@Autowired
	private CalendarioRepository cr;
	
	public Iterable<Calendario> listarCalendario(){
		return cr.findAll();
	}
	
	public Optional<Calendario> listCalendarioById(Long id){
		return cr.findById(id);
	}
	
	public void gravar(Calendario calendario) {
		cr.save(calendario);
	}
	
	public void alterar(Calendario calendario) {
		Optional<Calendario> c = cr.findById(calendario.getId());
		if(c.isPresent()) {
			cr.save(calendario);
		}
	}
	
	public void delete(Long id) {
		Optional<Calendario> c = this.listCalendarioById(id);
		if(c.isPresent()) {
			cr.deleteById(id);
		}
	}
}
