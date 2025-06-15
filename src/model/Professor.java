package model;

public class Professor {
	private long CPF;
	private String nome;
	private String area;
	private int pontuacao;

	public Professor() {
		super();
	}

	public Professor(long CPF, String nome, String area, int pontuacao) {
		this.CPF = CPF;
		this.nome = nome;
		this.area = area;
		this.pontuacao = pontuacao;
	}

	public long getCPF() {
		return CPF;
	}

	public void setCPF(long CPF) {
		this.CPF = CPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	@Override
	public String toString() {
		return CPF + " | " + nome + " | " + area + " | " + pontuacao;
	}
}
