package br.gov.go.goiania.atendefacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Atendente;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long>{
	
	@Query("SELECT a FROM Atendente a "
			+ "JOIN a.acesso ac "
			+ "WHERE UPPER(ac.servidor.matricula) = UPPER(:matricula) ")
	public Optional<Atendente> findByAtendenteMatricula(@Param("matricula") String matricula);

}
