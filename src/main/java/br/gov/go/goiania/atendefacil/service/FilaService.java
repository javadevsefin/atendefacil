package br.gov.go.goiania.atendefacil.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.goiania.atendefacil.domain.Fila;
import br.gov.go.goiania.atendefacil.repository.FilaRepository;

@Service
public class FilaService {
	
	@Autowired
	private FilaRepository fr;
	
	@Autowired
	private DataService ds;

	
	public Iterable<Fila> listarFilaAtivados(String unidade){
		return fr.listarFilaAtivados(LocalDate.now().toString(), unidade);
	}
	
	public List<Fila> listarFilaEspera(String matricula, String unidade){
		return fr.listarFilaEspera(LocalDate.now().toString(), matricula, unidade);
	
	}
	
	public List<Fila> findByIdentificador(Long identificador){
		return fr.findByIdentificador(identificador);
	}
	
	public void gravar(Fila fila) {
		fila.setAtivacao(ds.now());
		fila.setStatusAgendamento("Ativado");
		fila.setStatusFila("Aguardando");
		fila.setAtendente(null);
		fr.save(fila);
	}

	public Optional<Fila> findById(Long id) {
		return fr.findById(id);
	}

}
