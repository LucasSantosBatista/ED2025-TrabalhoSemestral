package model;

public class Inscricao {
	private int codigoInscricao;
	private long cpfProfessor;
	private int codigoDisciplina;

	public Inscricao() {
		super();
	}

	public Inscricao(int codigoInscricao, long cpfProfessor, int codigoDisciplina) {
		this.codigoInscricao = codigoInscricao;
		this.cpfProfessor = cpfProfessor;
		this.codigoDisciplina = codigoDisciplina;

	}

	public long getCpfProfessor() {
		return cpfProfessor;
	}

	public void setCpfProfessor(long cpfProfessor) {
		this.cpfProfessor = cpfProfessor;
	}

	public int getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(int codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public int getCodigoInscricao() {
		return codigoInscricao;
	}

	public void setCodigoInscricao(int codigoInscricao) {
		this.codigoInscricao = codigoInscricao;
	}

	@Override
	public String toString() {
		return cpfProfessor + " | " + codigoDisciplina + " | " + codigoInscricao;
	}

}
