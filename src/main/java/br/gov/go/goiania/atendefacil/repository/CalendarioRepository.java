package br.gov.go.goiania.atendefacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Calendario;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Long>{
	
	@Query("FROM Calendario WHERE statusCalendario = 'Ativo' ORDER BY dia ASC ")
	public Iterable<Calendario> listarDiasAtivo();
	
	
	@Query("SELECT c FROM Calendario c "
			+ "WHERE c.dia BETWEEN :dataInicial AND :dataFinal "
			+ "AND UPPER(c.statusCalendario) = UPPER(:statusCalendario) "
			+ "AND UPPER(c.observacao) = UPPER(:observacao) ")
	public List<Calendario> buscaAvancada(
			@Param("dataInicial") String dataInicial,
			@Param("dataFinal") String dataFinal, 
			@Param("statusCalendario") String statusCalendario,
			@Param("observacao") String observacao);
}
