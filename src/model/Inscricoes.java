package model;

public class Inscricoes {
	private long cpfProfessor;
	private int codigoDisciplina;
	private int codigoProcesso;

	public Inscricoes() {
		super();
	}

	public Inscricoes(long cpfProfessor, int codigoDisciplina, int codigoProcesso) {
		this.cpfProfessor = cpfProfessor;
		this.codigoDisciplina = codigoDisciplina;
		this.codigoProcesso = codigoProcesso;
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

	public int getCodigoProcesso() {
		return codigoProcesso;
	}

	public void setCodigoProcesso(int codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}

	@Override
	public String toString() {
		return "[" + cpfProfessor + " | " + codigoDisciplina + " | " + codigoProcesso + "]";
	}

}
