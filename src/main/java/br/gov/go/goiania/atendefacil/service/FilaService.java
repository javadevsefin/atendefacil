package br.gov.go.goiania.atendefacil.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.go.goiania.atendefacil.domain.Agendamento;
import br.gov.go.goiania.atendefacil.domain.Fila;
import br.gov.go.goiania.atendefacil.repository.AgendamentoRepository;
import br.gov.go.goiania.atendefacil.repository.FilaRepository;

@Service
public class FilaService {
	
	@Autowired
	private FilaRepository fr;
	
	@Autowired
	private DataService ds;
	
	@Autowired
	private AgendamentoRepository ar;
	
	public Iterable<Fila> filaFindByAll(){
		return fr.findAll();
	}

	public Iterable<Fila> listarFilaAtivados(String unidade){
		return fr.listarFilaAtivados(LocalDate.now().toString(), unidade);
	}
	
	public List<Fila> listarFilaEspera(String matricula, String unidade){
		return fr.listarFilaEspera(LocalDate.now().toString(), matricula, unidade);
	
	}
	
	public List<Fila> findByIdentificador(Long identificador){
		return fr.findByIdentificador(identificador);
	}
	
	public List<Fila> findByFinalizado(){
		return fr.findByFinalizado();
	}
	
	public void gravar(Fila fila) {
		fila.setAtivacao(ds.now());
		fila.setStatusAgendamento("Ativado");
		fila.setStatusFila("Aguardando");
		fila.setAtendente(null);
		fr.save(fila);
	}
	
	public void avaliar(Long idFila, Long idAgenda, String nota) {
		
		Agendamento agendamento = ar.findById(idAgenda).orElseThrow(()->
		new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento não Encontrado"));
		
		agendamento.setStatusAgendamento("Finalizado");
		
		ar.save(agendamento);
		
		Fila fila = fr.findById(idFila).orElseThrow(()->
		new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fila não Encontrado"));
		
		fila.setStatusAgendamento("Finalizado");
		fila.setAvaliacao(nota);
		
		fr.save(fila);
		
	}

	public Optional<Fila> findById(Long id) {
		return fr.findById(id);
	}

}
