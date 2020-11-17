package br.gov.go.goiania.atendefacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.goiania.atendefacil.domain.Role;
import br.gov.go.goiania.atendefacil.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository rr;
	
	public Iterable<Role> listarRole(){
		return rr.findAll();
	}
}
