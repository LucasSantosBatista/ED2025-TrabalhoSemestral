package model;

public class Disciplina {
	private int codigo;
	private String nome;
	private String diaSemana;
	private String horarioInicio;
	private double cargaHoraria;
	private int codCursoDisciplina;
	private static int codProcesso;

	public Disciplina() {
		super();
	}

	public Disciplina(int codigo, String nome, String diaSemana, String horarioInicio, double cargaHoraria,
			int codCurso) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.diaSemana = diaSemana;
		this.horarioInicio = horarioInicio;
		this.cargaHoraria = cargaHoraria;
		this.codCursoDisciplina = codCurso;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public double getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(double cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public int getCodCursoDisciplina() {
		return codCursoDisciplina;
	}

	public void setCodCursoDisciplina(int codCurso) {
		this.codCursoDisciplina = codCurso;
	}

	public int getCodProcesso() {
		return codProcesso;
	}

	@Override
	public String toString() {
		return "[" + codigo + " | " + nome + " | " + diaSemana + " | " + horarioInicio + " | " + cargaHoraria + " hrs/aula | Curso "
				+ codCursoDisciplina + " | " + codProcesso + "]";

	}
}