package br.gov.go.goiania.atendefacil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.goiania.atendefacil.domain.Servidor;
import br.gov.go.goiania.atendefacil.repository.ServidorRepository;

@Service
public class ServidorService {
	
	@Autowired
	private ServidorRepository sr;
	
	public Iterable<Servidor> listarServidor(){
		return sr.findAll();
	}
	
	public List<Servidor> buscaAvancada(String matricula, String nome, String cpf){
		return sr.findByMatriculaNomeCpf(matricula, nome, cpf);
	}
}


