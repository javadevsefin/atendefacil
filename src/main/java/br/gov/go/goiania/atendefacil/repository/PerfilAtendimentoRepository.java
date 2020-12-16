package br.gov.go.goiania.atendefacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.PerfilAtendimento;

@Repository
public interface PerfilAtendimentoRepository extends JpaRepository<PerfilAtendimento, Long> {
	
	@Query("SELECT pa FROM PerfilAtendimento pa "
			+ "JOIN pa.acesso a "
			+ "WHERE UPPER(a.servidor.matricula) = UPPER(:matricula) ")
	public List<PerfilAtendimento> listarPerfil(@Param("matricula") String matricula);
	
	
 }
