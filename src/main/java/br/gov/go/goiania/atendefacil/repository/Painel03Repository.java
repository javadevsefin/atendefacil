package br.gov.go.goiania.atendefacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Painel03;

@Repository
public interface Painel03Repository extends JpaRepository<Painel03, Long>{
	
	 @Query("FROM Painel03 WHERE fila_id = (:filaId) ")
	 public Optional<Painel03> findByFilaId(@Param("filaId") Long filaId);
	 
	 @Query(nativeQuery =true, value ="SELECT * FROM Painel_03 ORDER BY chamada DESC LIMIT 5")
	 public Iterable<Painel03> findByfive();
	 
	 @Query("FROM Painel03 WHERE chamada = (SELECT MAX(chamada) FROM Painel03) ")
	 public Optional<Painel03> findByChamada();

}