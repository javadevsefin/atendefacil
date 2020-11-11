package br.gov.go.goiania.atendefacil.dto;

import br.gov.go.goiania.atendefacil.domain.Agendamento;

public class EmissorDeSenhaDto {
	
	private Long identificador;
	private String senha;
	private String nome;
	private String cpfCnpj;
	private String unidade;
	private String endereco;
	private String data;
	private String horario;
	private String servico;
	private String detalhamentoSercivo;
	private String status;
	private String prioridade;
	
	public EmissorDeSenhaDto(Agendamento a) {
		this.identificador = a.getId();
		this.senha = a.getSenha();
		this.nome = a.getContribuinte().getNome();
		this.cpfCnpj = a.getContribuinte().getCpfCnpj();
		this.unidade = a.getGrade().getUnidade().getDescricao();
		this.endereco = a.getGrade().getUnidade().getEndereco();
		this.data = a.getGrade().getCalendario().getDia();
		this.servico = a.getGrade().getServico().getDescricao();
		this.horario = a.getHorario();
		this.detalhamentoSercivo = a.getDetalhamentoServico().getDescricao();
		this.status = a.getStatusAgendamento();
		this.prioridade = a.getPrioridade();
		
	}

	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getDetalhamentoSercivo() {
		return detalhamentoSercivo;
	}

	public void setDetalhamentoSercivo(String detalhamentoSercivo) {
		this.detalhamentoSercivo = detalhamentoSercivo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return "EmissorDeSenhaDto [identificador=" + identificador + ", senha=" + senha + ", nome=" + nome
				+ ", cpfCnpj=" + cpfCnpj + ", unidade=" + unidade + ", endereco=" + endereco + ", data=" + data
				+ ", horario=" + horario + ", servico=" + servico + ", detalhamentoSercivo=" + detalhamentoSercivo
				+ ", status=" + status + ", prioridade=" + prioridade + "]";
	}
	
	
}
