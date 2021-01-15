package br.gov.go.goiania.atendefacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.goiania.atendefacil.repository.RelatoriosAgendamentoRepository;
import br.gov.go.goiania.atendefacil.repository.RelatoriosGradeRepository;

@Service
public class RelatoriosService {
	
	@Autowired
	private RelatoriosGradeRepository rgr;
	
	@Autowired
	private RelatoriosAgendamentoRepository rar;
	
	public Object[] listarQuanUnidade(String unidade, String dataInicial, String dataFinal, String servico) {
		return rgr.relGradeUnidade(unidade, dataInicial, dataFinal, servico);
	}
	
	public Object[] listarQuanServico(String unidade, String dataInicial, String dataFinal, String servico) {
		return rgr.relGradeServico(unidade, dataInicial, dataFinal, servico);
	}
	
	public Object[] listarQuantAgendUnidade(String unidade, String dataInicial, String dataFinal, String servico, String statusAgendamento) {
	 	return rar.relAgendUnidade(unidade, dataInicial, dataFinal, servico, statusAgendamento);
	}
	
	public Object[] listarQuantAgendServico(String unidade, String dataInicial, String dataFinal, String servico, String statusAgendamento) {
	 	return rar.relAgendServico(unidade, dataInicial, dataFinal, servico, statusAgendamento);
	}

}
