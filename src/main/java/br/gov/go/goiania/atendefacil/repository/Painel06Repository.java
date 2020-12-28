package br.gov.go.goiania.atendefacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Painel06;

@Repository
public interface Painel06Repository extends JpaRepository<Painel06, Long> {
	
	 @Query("FROM Painel06 WHERE fila_id = (:filaId) ")
	 public Optional<Painel06> findByFilaId(@Param("filaId") Long filaId);
	 
	 @Query(nativeQuery =true, value ="SELECT * FROM Painel_06 ORDER BY chamada DESC LIMIT 5")
	 public Iterable<Painel06> findByfive();
	 
	 @Query("FROM Painel06 WHERE chamada = (SELECT MAX(chamada) FROM Painel06) ")
	 public Optional<Painel06> findByChamada();

}
