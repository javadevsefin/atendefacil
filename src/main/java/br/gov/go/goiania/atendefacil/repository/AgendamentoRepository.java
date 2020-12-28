package br.gov.go.goiania.atendefacil.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.gov.go.goiania.atendefacil.domain.Agendamento;


@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	
	@Query("SELECT a FROM Agendamento a "
			+ "JOIN a.grade g "
			+ "WHERE UPPER(g.unidade.descricao) LIKE UPPER(:unidade) "
			+ "AND g.calendario.dia BETWEEN :dataInicial AND :dataFinal "
			+ "AND UPPER(g.servico.descricao) LIKE UPPER(:servico) "
			+ "AND UPPER(a.statusAgendamento) LIKE UPPER(:statusAgendamento) ")
	public List<Agendamento> buscaAvancada(
			@Param("unidade") String unidade,
			@Param("dataInicial") String dataInicial,
			@Param("dataFinal") String dataFinal,
			@Param("servico") String servico,
			@Param("statusAgendamento") String statusAgendamento);
	
	
	@Query("SELECT a FROM Agendamento a "
			+ "JOIN a.grade g "
			+ "WHERE UPPER(g.unidade.descricao) LIKE UPPER(:unidade) "
			+ "AND UPPER(g.calendario.dia) LIKE UPPER(:calendario) "
			+ "AND a.statusAgendamento = 'Em espera' ")
	public List<Agendamento> agendaContribuinte(
			@Param("calendario") String calendario,
			@Param("unidade") String unidade);
	
	@Query("SELECT a FROM Agendamento a "
			+ "JOIN a.contribuinte c "
			+ "WHERE UPPER(c.cpfCnpj) = UPPER(:cpfCnpj) ")
	public List<Agendamento> findByAgendamentoMatricula(@Param("cpfCnpj") String cpfCnpj);
	
}
