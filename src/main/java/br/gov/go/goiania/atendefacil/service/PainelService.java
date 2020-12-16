package br.gov.go.goiania.atendefacil.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.goiania.atendefacil.domain.Fila;
import br.gov.go.goiania.atendefacil.domain.Painel01;
import br.gov.go.goiania.atendefacil.repository.Painel01Respository;

@Service
public class PainelService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private Painel01Respository pr01;
		
	public Iterable<Painel01> listarPainel01(){
		return pr01.findByfive();
	}
	
	public Optional<Painel01> listarChamada(){
		
		Optional<Painel01> getChamada = null;
		Optional<Painel01> getPainel = pr01.findByChamada();
		
		if(getPainel.get().getChamar().equals(true)) {
			getChamada = getPainel;
			this.som();
			this.atualizarPainel01(getPainel.get().getFilaId());
		} else {
			getChamada = getPainel;
		}
		
		return getChamada;
	}
	
	public void gerarPainel01(Optional<Fila> fila) {
	
		Optional<Painel01> p01 = pr01.findByFilaId(fila.get().getId());
		
		System.out.println(p01);
		
		if(p01.isPresent()) {
			p01.get().setChamada(LocalDateTime.now());
			p01.get().setChamar(true);
			pr01.save(p01.get());
 		} else {
			Painel01 p = new Painel01();
			p.setFilaId(fila.get().getId());
			p.setSenha(fila.get().getSenha());
			p.setGuiche(fila.get().getAtendente().getGuiche().getDescricao());
			p.setServico(fila.get().getDetalhamentoServico());
			p.setUnidade(fila.get().getUnidade());
			p.setChamada(LocalDateTime.now());
			p.setPrioridade(fila.get().getPrioridade());
			p.setChamar(true);
				
			pr01.save(p);
		}
	}
	
	public void atualizarPainel01(Long filaId) {
		Optional<Painel01> p01 = pr01.findByFilaId(filaId);
		if(p01.isPresent()) {
			p01.get().setChamar(false);
			pr01.save(p01.get());
 		}
	}
	
	public void som() {
		try {
		    File yourFile = new File("C:\\java-spring\\spring-workspace\\eatendefacil\\teste02.wav");
		    
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(yourFile);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e) {
			 e.printStackTrace();
	         System.exit(1);
		}
    }
	


}
