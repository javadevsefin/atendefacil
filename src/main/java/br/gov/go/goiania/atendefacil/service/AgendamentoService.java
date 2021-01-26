package br.gov.go.goiania.atendefacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.go.goiania.atendefacil.domain.Agendamento;
import br.gov.go.goiania.atendefacil.domain.Contribuinte;
import br.gov.go.goiania.atendefacil.domain.DetalhamentoServico;
import br.gov.go.goiania.atendefacil.domain.Grade;
import br.gov.go.goiania.atendefacil.dto.AgendamentoDto;
import br.gov.go.goiania.atendefacil.dto.EmissorDeSenhaDto;
import br.gov.go.goiania.atendefacil.repository.AgendamentoNativeReposiroty;
import br.gov.go.goiania.atendefacil.repository.AgendamentoRepository;
import br.gov.go.goiania.atendefacil.repository.ContribuinteRepository;
import br.gov.go.goiania.atendefacil.repository.DetalhamentoServicoRepository;
import br.gov.go.goiania.atendefacil.repository.GradeRepository;

@Service
public class AgendamentoService {
	
	private Agendamento agendamento = new Agendamento();
	
	@Autowired
	private GradeRepository gr;
	
	@Autowired
	private AgendamentoRepository ar;
	
	@Autowired
	private AgendamentoNativeReposiroty anr;
	
	@Autowired
	private ContribuinteRepository cr;
	
	@Autowired
	private DetalhamentoServicoRepository dsr;
	
	public Optional<Agendamento> AgendamentoById(Long id){
		return ar.findById(id);
	}
	
	public Optional<EmissorDeSenhaDto> AgendaById(Long id){
		return ar.findById(id).map(EmissorDeSenhaDto::new);
	}
	
	public void gerarAgenda(Long id) {
		
		Grade grade = gr.findById(id).orElseThrow(()->
		new ResponseStatusException(HttpStatus.BAD_REQUEST, "Grade não Encontrada"));
		
		agendamento.setStatusAgendamento("Em espera");
		
		String horaI = grade.getHoraInicial();
		String horaIH = horaI.substring(0, 2);
		String horaIM = horaI.substring(3, 5);
		Integer horaIHM = Integer.parseInt(horaIH) * 60;
		Integer horaIMP = Integer.parseInt(horaIM);
		Integer horaITM = horaIHM + horaIMP;
		Integer interVagas = grade.getCorrecaoHora();
		
		/*System.out.println("Identificador: " + grade.getId());
		System.out.println("Hora Inicial: " + grade.getHoraInicial());
		System.out.println("Intervalo : " + grade.getIntervalo());
		System.out.println("Quantidade: " + grade.getQuantidade());		
		System.out.println("Hora:" + horaIH + " Minuto:" + horaIM);
		System.out.println("Horas em minutos: " + horaITM);
		System.out.println("Intervalo: " +  interVagas);*/
		
		for(Integer i = 1; i <= grade.getQuantidade(); i++) {
			//System.out.println("id:"+ i + " Hora: " +  ajustaHora(horaITM));
			String seq = i.toString();
			grade.setGerado("g");
			agendamento.setGrade(grade);
			agendamento.setSequencial(seq);
			agendamento.setSenha(grade.getServico().getSigla() + "-" + ajustaHora(horaITM));
			agendamento.setHorario(ajustaHora(horaITM));
			horaITM = horaITM + interVagas;
			
			anr.saveQueryNative(agendamento);
			//System.out.println(agendamento);
		}
	}
	
	public Iterable<Agendamento> listarAgendamento(){
		return ar.findAll();
	}
	
	public List<Agendamento> buscaAvancada(String unidade, String dataInicial, String dataFinal, String servico, String statusAgendamento) {
		return ar.buscaAvancada(unidade, dataInicial, dataFinal, servico, statusAgendamento);
	}
	
	public List<Agendamento> agendaContribuinte(String calendario, String unidade){
		return ar.agendaContribuinte(calendario, unidade);
	}
	
	public List<Agendamento> findByAgendamentoMatricula(String cpfCnpj){
		return ar.findByAgendamentoMatricula(cpfCnpj);
	}
	
	public Page<Agendamento> listarAgendamento(Integer page, Integer size){
		
		PageRequest pageRequest = PageRequest.of(page, size);
		return ar.findAll(pageRequest) ;
	}
	
	public void enviar(AgendamentoDto agendamentoDto) {

		Contribuinte contribuinte = cr.findById(agendamentoDto.getContribuinte()).orElseThrow(()->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contribuinte não encontrado"));
		
		DetalhamentoServico detalhamentoServico = dsr.findById(agendamentoDto.getDetalhamentoServico()).orElseThrow(()->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Detalhamento de servico não encontrado")); 
		
		Agendamento agendamento = ar.findById(agendamentoDto.getId()).orElseThrow(()->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento não encontrado"));
		
		agendamento.setPrioridade(agendamentoDto.getPrioridade());
		agendamento.setStatusAgendamento("Agendado");
		agendamento.setContribuinte(contribuinte);
		agendamento.setDetalhamentoServico(detalhamentoServico);
		
		ar.save(agendamento);
	}
	
	public void gerarFila(Long id) {
		Agendamento agendamento = ar.findById(id).orElseThrow(()->
		new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento não encontrado"));
		
		agendamento.setStatusAgendamento("Ativado");
		
		ar.save(agendamento);
	}
	
	public void reclassifcarPrioridade(Long id, String prioridade) {
		Agendamento agendamento = ar.findById(id).orElseThrow(()->
		new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento não encontrado"));
		
		agendamento.setPrioridade(prioridade);
		
		ar.save(agendamento);
	}
	 
	public String ajustaHora(Integer horaM) {
		
		String hora =" ";
		Integer difHoraI = horaM/60;
		Integer difHoraM = horaM%60;
		String h = difHoraI.toString();
		String m = difHoraM.toString(); 
		
		if(h.length() == 1) {
			h = "0"+h;
		}
		
		if(m.length() == 1) {
			m = "0"+m;
		}
		hora = h + ":" + m;
		return hora;
	}


}
