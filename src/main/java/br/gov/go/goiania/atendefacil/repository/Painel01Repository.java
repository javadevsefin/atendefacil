package br.gov.go.goiania.atendefacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Painel01;

@Repository
public interface Painel01Repository extends JpaRepository<Painel01, Long>{
	
	 @Query("FROM Painel01 WHERE fila_id = (:filaId) ")
	 public Optional<Painel01> findByFilaId(@Param("filaId") Long filaId);
	 
	 @Query(nativeQuery =true, value ="SELECT * FROM Painel_01 ORDER BY chamada DESC LIMIT 5")
	 public Iterable<Painel01> findByfive();
	 
	 @Query("FROM Painel01 WHERE chamada = (SELECT MAX(chamada) FROM Painel01) ")
	 public Optional<Painel01> findByChamada();

}
