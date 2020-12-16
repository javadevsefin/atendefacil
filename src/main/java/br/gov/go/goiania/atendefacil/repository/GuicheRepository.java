package br.gov.go.goiania.atendefacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Guiche;

@Repository
public interface GuicheRepository extends JpaRepository<Guiche, Long>{
	
	@Query("FROM Guiche WHERE UPPER(descricao) = UPPER(:descricao)")
	public Optional<Guiche> findByDescricao(String descricao);
	

}
