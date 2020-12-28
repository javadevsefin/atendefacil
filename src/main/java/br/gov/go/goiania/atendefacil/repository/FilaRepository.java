package br.gov.go.goiania.atendefacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Fila;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Long> {
	
	@Query("FROM Fila WHERE data = (:data) AND statusFila = 'Aguardando' AND unidade = (:unidade) ")
	public Iterable<Fila> listarFilaAtivados(@Param("data") String data,
											 @Param("unidade") String unidade);
	
	@Query(nativeQuery =true, value ="SELECT * FROM fila as f WHERE f.data = (:data) AND f.status_Fila = 'Aguardando' "
					+ "AND unidade = (:unidade) "
					+ "AND f.detalhamento_Servico IN (SELECT ds.descricao FROM perfil_atendimento AS pa "
					+ "JOIN acesso AS a " 
					+ "ON a.id = pa.acesso_id "
					+ "JOIN detalhamento_servico AS ds "
					+ "ON ds.id = pa.detalhe_servico_id "
					+ "WHERE a.servidor_id = (:matricula)) ")
	public List<Fila> listarFilaEspera(@Param("data") String data, 
									   @Param("matricula") String matricula,
									   @Param("unidade") String unidade);
	
	@Query("SELECT f FROM Fila f WHERE f.identificador = :identificador")
	public List<Fila> findByIdentificador(@Param("identificador") Long identificador);
}
