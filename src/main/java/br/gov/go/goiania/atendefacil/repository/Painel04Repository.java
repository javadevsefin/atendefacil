package br.gov.go.goiania.atendefacil.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Painel04;

@Repository
public interface Painel04Repository extends JpaRepository<Painel04, Long>{
	
	 @Query("FROM Painel04 WHERE fila_id = (:filaId) ")
	 public Optional<Painel04> findByFilaId(@Param("filaId") Long filaId);
	 
	 @Query(nativeQuery =true, value ="SELECT * FROM Painel_04 ORDER BY chamada DESC LIMIT 5")
	 public Iterable<Painel04> findByfive();
	 
	 @Query("FROM Painel04 WHERE chamada = (SELECT MAX(chamada) FROM Painel04) ")
	 public Optional<Painel04> findByChamada();

}