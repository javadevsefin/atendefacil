package br.gov.go.goiania.atendefacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Contribuinte;

@Repository
public interface ContribuinteRepository extends JpaRepository<Contribuinte, Long>{
	
	@Query("FROM  Contribuinte WHERE UPPER(nome) LIKE UPPER(:nome)"
			+ " AND UPPER(cpfCnpj) LIKE UPPER(:cpfCnpj)")
	public List<Contribuinte> findByNomeCpfCnpj(
			@Param("nome") String nome,
			@Param("cpfCnpj") String cpfCnpj);
	
	
	@Query("FROM  Contribuinte WHERE UPPER(cpfCnpj) = UPPER(:cpfCnpj)"
			+ " AND UPPER(senha) = UPPER(:senha)")
	public Contribuinte findByLogin(
			@Param("cpfCnpj") String cpfCnpj,
			@Param("senha") String senha);
	
}
