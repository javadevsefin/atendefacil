package br.gov.go.goiania.atendefacil.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import br.gov.go.goiania.atendefacil.domain.Acesso;
import br.gov.go.goiania.atendefacil.domain.Atendente;
import br.gov.go.goiania.atendefacil.domain.Atendimento;
import br.gov.go.goiania.atendefacil.domain.DetalhamentoServico;
import br.gov.go.goiania.atendefacil.domain.Fila;
import br.gov.go.goiania.atendefacil.domain.Guiche;
import br.gov.go.goiania.atendefacil.dto.AtendimentoDto;
import br.gov.go.goiania.atendefacil.repository.AcessoRepository;
import br.gov.go.goiania.atendefacil.repository.AtendenteRepository;
import br.gov.go.goiania.atendefacil.repository.AtendimentoRepository;
import br.gov.go.goiania.atendefacil.repository.DetalhamentoServicoRepository;
import br.gov.go.goiania.atendefacil.repository.FilaRepository;
import br.gov.go.goiania.atendefacil.repository.GuicheRepository;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendenteRepository ar;
	
	@Autowired
	private GuicheRepository gr;
	
	@Autowired
	private AcessoRepository acr;
	
	@Autowired
	private FilaRepository fr;
	
	@Autowired
	private DataService ds;
	
	@Autowired
	private DetalhamentoServicoRepository dsr;
	
	@Autowired
	private AtendimentoRepository atr;
	
	@Autowired
	private PainelService ps;
	
	
	public Optional<Atendente> findByAtendenteMatricula(String matricula) {
		return ar.findByAtendenteMatricula(matricula);
	} 
	
	public void gerarAtendente(String matricula, String statusAtendimento, String guiche) {
			
		Optional<Atendente> a = ar.findByAtendenteMatricula(matricula);
		
		Guiche g = gr.findByDescricao(guiche).orElseThrow(()->
		new ResponseStatusException(HttpStatus.BAD_REQUEST, "Guiche não encontrado!"));
		
		Acesso ac = acr.findByServidor(matricula).orElseThrow(()->
		new ResponseStatusException(HttpStatus.BAD_REQUEST, "Acesso não encontrado!"));
		
		if(a.isPresent()) {
			
			a.get().setStatusAtendente(statusAtendimento);
			a.get().setAcesso(ac);
			a.get().setGuiche(g);
			ar.save(a.get());
			
		} else {
			
			Atendente atendente = new Atendente();
			atendente.setStatusAtendente(statusAtendimento);
			atendente.setAcesso(ac);
			atendente.setGuiche(g);
			ar.save(atendente);
		}
	}
	
	public void chamar(Long fila, String servidor){
		
		Optional<Fila> f =  fr.findById(fila);
		Optional<Atendente> a = ar.findByAtendenteMatricula(servidor);
		
		if(f.isPresent()) {
			f.get().setChamada(ds.now());
			f.get().setStatusFila("Chamando");
			f.get().setAtendente(a.get());
			
			fr.save(f.get());
			ps.gerarPainel(f);
		}
	}
	
	public void reagendar(Long fila, Long servico){
			
			Optional<Fila> f =  fr.findById(fila);
			Optional<DetalhamentoServico> ds = dsr.findById(servico);
			
			if(f.isPresent()) {
				Fila filaNova = new Fila();
				filaNova.setAtivacao(LocalDateTime.now());
				filaNova.setChamada(null);
				filaNova.setInicio(null);
				filaNova.setFinalizado(null);
				filaNova.setAvaliacao(null);
				filaNova.setStatusFila("Aguardando");
				filaNova.setIdentificador(f.get().getIdentificador());
				filaNova.setSenha(f.get().getSenha());
				filaNova.setNome(f.get().getNome());
				filaNova.setCpfCnpj(f.get().getCpfCnpj());
				filaNova.setUnidade(f.get().getUnidade());
				filaNova.setData(f.get().getData());
				filaNova.setHorario(f.get().getHorario());
				filaNova.setServico(f.get().getServico());
				filaNova.setDetalhamentoServico(ds.get().getDescricao());
				filaNova.setStatusAgendamento("Reagendado");
				filaNova.setPrioridade(f.get().getPrioridade());
				filaNova.setAtendente(null);
				fr.save(filaNova);
			}
		}
	
	public void comecar(Long fila){
		
		Optional<Fila> f =  fr.findById(fila);
		
		if(f.isPresent()) {
			f.get().setInicio(ds.now());
			f.get().setStatusFila("Atendendo");
			fr.save(f.get());
		}
	}
	
	public void finalizar(Long fila){
		
		Optional<Fila> f =  fr.findById(fila);
		
		if(f.isPresent()) {
			f.get().setFinalizado(ds.now());
			f.get().setStatusFila("Finalizado");
			fr.save(f.get());
		}
	}
	
	
	public void atendimento(AtendimentoDto atendimentoDto) {
		
		Optional<DetalhamentoServico> ds = dsr.findById(atendimentoDto.getDetalhamentoServico());
		Optional<Fila> f = fr.findById(atendimentoDto.getFila());
		
		 Atendimento atendimento = new Atendimento();
		 
		 atendimento.setRealizado(LocalDateTime.now());
		 atendimento.setServidor(atendimentoDto.getServidor());
		 atendimento.setDescricao(atendimentoDto.getDescricao());
		 atendimento.setDetalhamentoServico(ds.get());
		 atendimento.setFila(f.get());
		 
		 atr.save(atendimento);
		 
	}

}
