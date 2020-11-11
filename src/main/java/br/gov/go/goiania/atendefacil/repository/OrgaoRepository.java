package br.gov.go.goiania.atendefacil.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.go.goiania.atendefacil.domain.Orgao;

@Repository
public interface OrgaoRepository extends JpaRepository<Orgao, Long> {

}
