package br.gov.go.goiania.atendefacil.domain;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name="detalhamento_servico")
@SuppressWarnings("serial")
public class DetalhamentoServico implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	
	@Column(name="descricao", nullable = false, length = 30)
	private String descricao;
	
	@Column(name="detalhamento", nullable = false, length = 100)
	private String detalhamento;
		
	
	@ManyToOne
	@JoinColumn(name="servico_id", nullable = false)
	private Servico servico = new Servico();
	
	public DetalhamentoServico() {
		
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

	public String getDetalhamento() {
		return detalhamento;
	}

	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}
