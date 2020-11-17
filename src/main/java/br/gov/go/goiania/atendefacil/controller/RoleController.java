package br.gov.go.goiania.atendefacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.goiania.atendefacil.domain.Role;
import br.gov.go.goiania.atendefacil.service.RoleService;

@RestController
@RequestMapping("/atendeFacil/api/role")
public class RoleController {
	
	@Autowired
	private RoleService rs;
	
	@GetMapping()
	public Iterable<Role> listarRole(){
		return rs.listarRole();
	}

}
