package br.gov.go.goiania.atendefacil.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Painel05;

@Repository
public interface Painel05Repository extends JpaRepository<Painel05, Long>{
	
	 @Query("FROM Painel05 WHERE fila_id = (:filaId) ")
	 public Optional<Painel05> findByFilaId(@Param("filaId") Long filaId);
	 
	 @Query(nativeQuery =true, value ="SELECT * FROM Painel_05 ORDER BY chamada DESC LIMIT 5")
	 public Iterable<Painel05> findByfive();
	 
	 @Query("FROM Painel05 WHERE chamada = (SELECT MAX(chamada) FROM Painel05) ")
	 public Optional<Painel05> findByChamada();

}