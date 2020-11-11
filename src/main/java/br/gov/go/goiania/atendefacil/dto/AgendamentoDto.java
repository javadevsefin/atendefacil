package br.gov.go.goiania.atendefacil.dto;

public class AgendamentoDto {
	
	private Long id;
	private Long contribuinte; 
	private Long detalhamentoServico;
	private String prioridade;
	
	public AgendamentoDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(Long contribuinte) {
		this.contribuinte = contribuinte;
	}

	public Long getDetalhamentoServico() {
		return detalhamentoServico;
	}

	public void setDetalhamentoServico(Long detalhamentoServico) {
		this.detalhamentoServico = detalhamentoServico;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	
}
