package br.gov.go.goiania.atendefacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Acesso;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long>{
	
	@Query("FROM Acesso WHERE UPPER(servidor) = UPPER(:matricula)")
	public Optional<Acesso> findByServidor(@Param("matricula") String matricula);
}
