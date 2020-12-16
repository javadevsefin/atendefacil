package br.gov.go.goiania.atendefacil.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class DataService {
	
	public LocalDateTime now() {
		LocalDateTime now = LocalDateTime.now();
		return now;
	}
	
	public String dataAtual() {
		LocalDateTime agora = LocalDateTime.now();
	    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = formatterData.format(agora);
		return dataFormatada;
	}

}
