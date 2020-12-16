package br.gov.go.goiania.atendefacil.dto;

public class AtendimentoDto {
	
	private Long id;
	private String descricao;
	private String servidor;
	private Long detalhamentoServico;
	private Long fila;
	
	public AtendimentoDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public Long getDetalhamentoServico() {
		return detalhamentoServico;
	}

	public void setDetalhamentoServico(Long detalhamentoServico) {
		this.detalhamentoServico = detalhamentoServico;
	}

	public Long getFila() {
		return fila;
	}

	public void setFila(Long fila) {
		this.fila = fila;
	}

	@Override
	public String toString() {
		return "AtendimentoDto [id=" + id + ", descricao=" + descricao + ", servidor=" + servidor
				+ ", detalhamentoServico=" + detalhamentoServico + ", fila=" + fila + "]";
	}
	
}
