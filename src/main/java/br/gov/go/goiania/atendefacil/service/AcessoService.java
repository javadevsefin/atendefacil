package br.gov.go.goiania.atendefacil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.go.goiania.atendefacil.domain.Acesso;
import br.gov.go.goiania.atendefacil.domain.Role;
import br.gov.go.goiania.atendefacil.domain.Servidor;
import br.gov.go.goiania.atendefacil.domain.Unidade;
import br.gov.go.goiania.atendefacil.repository.AcessoRepository;
import br.gov.go.goiania.atendefacil.repository.RoleRepository;
import br.gov.go.goiania.atendefacil.repository.ServidorRepository;
import br.gov.go.goiania.atendefacil.repository.UnidadeRepository;

@Service
public class AcessoService {
	
	Acesso acesso;
	
	@Autowired
	private AcessoRepository ar;
	
	@Autowired 
	private ServidorRepository sr;
	
	@Autowired
	private RoleRepository rr;
	
	@Autowired
	private UnidadeRepository ur;
	
	public Iterable<Acesso> listarAcesso(){
		return ar.findAll(); 
	}
	
	public Optional<Acesso> listAcessoById(Long id){
		return ar.findById(id);
	}
	
	public Acesso logar(String matricula, String senha){
		
		Acesso a = ar.logar(matricula, senha);
		
		if(a.getServidor().getMatricula().equals(matricula) && a.getSenha().equals(senha)) {
			this.acesso = a;	
		}
		return this.acesso;
	}
	
	public void alterarSenha(String matricula, String senha, String novaSenha, String confirmarNovaSenha){
		
		Acesso a = ar.logar(matricula, senha);
		
		if(a.getServidor().getMatricula().equals(matricula) && a.getSenha().equals(senha)) {
			if(novaSenha.equals(confirmarNovaSenha)){
				a.setSenha(novaSenha);
				ar.save(a);
			}
		}
	}
	
	public void gravar(Acesso acesso) {
		
		Servidor servidor = sr.findByMatricula(acesso.getServidor().getMatricula()).orElseThrow(()->
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "Servidor não encontrado"));
		
		Role role = rr.findById(acesso.getRole().getId()).orElseThrow(()->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role não encontrado"));
		
		Unidade unidade = ur.findById(acesso.getUnidade().getId()).orElseThrow(()-> 
			new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unidade não encontrada"));
		
		acesso.setServidor(servidor);
		acesso.setRole(role);
		acesso.setUnidade(unidade);
		acesso.setSenha(acesso.getSenha());
		
		ar.save(acesso);
	}
	
	public void alterar(Acesso acesso) {
		Optional<Acesso> a = ar.findById(acesso.getId());
		
		if(a.isPresent()) {
			Servidor servidor = sr.findByMatricula(acesso.getServidor().getMatricula()).orElseThrow(()->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Servidor não encontrado"));
			
			Role role = rr.findById(acesso.getRole().getId()).orElseThrow(()->
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role não encontrado"));
			
			Unidade unidade = ur.findById(acesso.getUnidade().getId()).orElseThrow(()-> 
				new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unidade não encontrada"));
		
				acesso.setServidor(servidor);
				acesso.setRole(role);
				acesso.setUnidade(unidade);
				acesso.setSenha(acesso.getSenha());
				
				ar.save(acesso);
		}
	}
}
