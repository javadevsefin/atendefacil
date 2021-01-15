package br.gov.go.goiania.atendefacil.domain;

public class Aluno {
	
	int ra;
	String nome;
	
	
	public Aluno() {
		
	}

	public int getRa() {
		return ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Aluno [ra=" + ra + ", nome=" + nome + "]";
	}
	

}
