package br.gov.go.goiania.atendefacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.DetalhamentoServico;

@Repository
public interface DetalhamentoServicoRepository extends JpaRepository<DetalhamentoServico, Long>{
	
	@Query("SELECT ds FROM DetalhamentoServico ds "
			+ "JOIN ds.servico s "
			+ "WHERE UPPER(s.descricao) = UPPER(:servico)")
	public Iterable<DetalhamentoServico> selDetServico(
			@Param("servico") String servico);
}
