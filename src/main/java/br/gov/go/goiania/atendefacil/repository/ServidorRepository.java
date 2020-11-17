package br.gov.go.goiania.atendefacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Servidor;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Long> {
	
	@Query("FROM Servidor WHERE UPPER(matricula) LIKE UPPER(:matricula)"
			+ "AND UPPER(nome) LIKE UPPER(:nome) AND UPPER(cpf) LIKE UPPER(:cpf)")
	public List<Servidor> findByMatriculaNomeCpf(
			@Param("matricula") String matricula,
			@Param("nome") String nome,
			@Param("cpf") String cpf);
}
