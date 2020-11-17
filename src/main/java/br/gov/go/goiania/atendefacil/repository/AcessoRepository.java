package br.gov.go.goiania.atendefacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Acesso;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long>{

}
