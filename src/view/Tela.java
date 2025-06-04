package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigoCurso;
	private JTextField txtNomeCurso;
	private JTextField txtAreaCurso;
	private JTextField txtCodigoDisciplina;
	private JTextField txtNomeDisciplina;
	private JTextField txtCPFProfessor;
	private JTextField txtNomeProfessor;
	private JTextField txtPontuacaoProfessor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		setTitle("FED - Faculdade de Estrutura de Dados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 624, 441);
		contentPane.add(tabbedPane);
		
		JPanel tabCursos = new JPanel();
		tabbedPane.addTab("Cursos", null, tabCursos, null);
		tabCursos.setLayout(null);
		
		JLabel lblTituloCursos = new JLabel("Cursos");
		lblTituloCursos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTituloCursos.setBounds(10, 11, 116, 35);
		tabCursos.add(lblTituloCursos);
		
		JLabel lblCodigoCurso = new JLabel("Código: ");
		lblCodigoCurso.setBounds(10, 93, 120, 14);
		tabCursos.add(lblCodigoCurso);
		
		JLabel lblNomeCurso = new JLabel("Nome: ");
		lblNomeCurso.setBounds(10, 136, 120, 14);
		tabCursos.add(lblNomeCurso);
		
		JLabel lblAreaCurso = new JLabel("Área de Conhecimento:  ");
		lblAreaCurso.setBounds(10, 179, 120, 14);
		tabCursos.add(lblAreaCurso);
		
		JButton btnInserirCurso = new JButton("Adicionar Novo Curso");
		btnInserirCurso.setBounds(462, 58, 135, 23);
		tabCursos.add(btnInserirCurso);
		
		JButton btnAtualizarCurso = new JButton("Atualizar Curso");
		btnAtualizarCurso.setBounds(462, 98, 135, 23);
		tabCursos.add(btnAtualizarCurso);
		
		JButton btnRemoverCurso = new JButton("Remover Curso");
		btnRemoverCurso.setBounds(462, 138, 135, 23);
		tabCursos.add(btnRemoverCurso);
		
		JButton btnConsultarCursoCod = new JButton("Consultar por Código");
		btnConsultarCursoCod.setBounds(462, 178, 135, 23);
		tabCursos.add(btnConsultarCursoCod);
		
		JTextArea txaTabelaCurso = new JTextArea();
		txaTabelaCurso.setBounds(10, 222, 599, 161);
		tabCursos.add(txaTabelaCurso);
		
		txtCodigoCurso = new JTextField();
		txtCodigoCurso.setBounds(140, 87, 86, 20);
		tabCursos.add(txtCodigoCurso);
		txtCodigoCurso.setColumns(10);
		
		txtNomeCurso = new JTextField();
		txtNomeCurso.setColumns(10);
		txtNomeCurso.setBounds(140, 130, 276, 20);
		tabCursos.add(txtNomeCurso);
		
		txtAreaCurso = new JTextField();
		txtAreaCurso.setColumns(10);
		txtAreaCurso.setBounds(140, 173, 276, 20);
		tabCursos.add(txtAreaCurso);
		
		JPanel tabDisciplinas = new JPanel();
		tabbedPane.addTab("Disciplinas", null, tabDisciplinas, null);
		tabDisciplinas.setLayout(null);
		
		JLabel lblTituloDisciplinas = new JLabel("Disciplinas");
		lblTituloDisciplinas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTituloDisciplinas.setBounds(10, 11, 122, 24);
		tabDisciplinas.add(lblTituloDisciplinas);
		
		JLabel lblCodigoDisciplina = new JLabel("Código: ");
		lblCodigoDisciplina.setBounds(10, 60, 60, 14);
		tabDisciplinas.add(lblCodigoDisciplina);
		
		JLabel lblNomeDisciplina = new JLabel("Nome: ");
		lblNomeDisciplina.setBounds(10, 99, 60, 14);
		tabDisciplinas.add(lblNomeDisciplina);
		
		JLabel lblDiaSemanaDisciplina = new JLabel("Dia Lecionado:");
		lblDiaSemanaDisciplina.setBounds(10, 138, 83, 14);
		tabDisciplinas.add(lblDiaSemanaDisciplina);
		
		JLabel lblHorarioInicio = new JLabel("Horário da aula:");
		lblHorarioInicio.setBounds(10, 177, 83, 14);
		tabDisciplinas.add(lblHorarioInicio);
		
		JLabel lblCargaHoraria = new JLabel("Carga Horária: ");
		lblCargaHoraria.setBounds(10, 216, 83, 14);
		tabDisciplinas.add(lblCargaHoraria);
		
		JLabel lblCodigoCursoDisciplina = new JLabel("Código do Curso: ");
		lblCodigoCursoDisciplina.setBounds(10, 255, 97, 14);
		tabDisciplinas.add(lblCodigoCursoDisciplina);
		
		JButton btnInserirDisciplina = new JButton("Adicionar Nova Disciplina");
		btnInserirDisciplina.setBounds(458, 72, 151, 23);
		tabDisciplinas.add(btnInserirDisciplina);
		
		JButton btnAtualizarDisciplina = new JButton("Atualizar Disciplina");
		btnAtualizarDisciplina.setBounds(458, 109, 151, 23);
		tabDisciplinas.add(btnAtualizarDisciplina);
		
		JButton btnRemoverDisciplina = new JButton("Remover Disciplina");
		btnRemoverDisciplina.setBounds(458, 146, 151, 23);
		tabDisciplinas.add(btnRemoverDisciplina);
		
		JButton btnConsultarDisciplinaCod = new JButton("Consultar por Código");
		btnConsultarDisciplinaCod.setBounds(458, 183, 151, 23);
		tabDisciplinas.add(btnConsultarDisciplinaCod);
		
		JTextArea txaTabelaDisciplinas = new JTextArea();
		txaTabelaDisciplinas.setBounds(10, 294, 599, 102);
		tabDisciplinas.add(txaTabelaDisciplinas);
		
		txtCodigoDisciplina = new JTextField();
		txtCodigoDisciplina.setBounds(103, 57, 86, 20);
		tabDisciplinas.add(txtCodigoDisciplina);
		txtCodigoDisciplina.setColumns(10);
		
		txtNomeDisciplina = new JTextField();
		txtNomeDisciplina.setColumns(10);
		txtNomeDisciplina.setBounds(103, 96, 279, 20);
		tabDisciplinas.add(txtNomeDisciplina);
		
		JComboBox cbxDiaSemanaDisciplina = new JComboBox();
		cbxDiaSemanaDisciplina.setBounds(103, 134, 279, 22);
		tabDisciplinas.add(cbxDiaSemanaDisciplina);
		
		JComboBox cbxHorarioInicio = new JComboBox();
		cbxHorarioInicio.setBounds(103, 173, 279, 22);
		tabDisciplinas.add(cbxHorarioInicio);
		
		JComboBox cbxCargaHoraria = new JComboBox();
		cbxCargaHoraria.setBounds(103, 212, 140, 22);
		tabDisciplinas.add(cbxCargaHoraria);
		
		JLabel lblHorasaula = new JLabel("horas/aula");
		lblHorasaula.setBounds(253, 216, 83, 14);
		tabDisciplinas.add(lblHorasaula);
		
		JComboBox cbxCodigoCursoDisciplina = new JComboBox();
		cbxCodigoCursoDisciplina.setBounds(103, 251, 140, 22);
		tabDisciplinas.add(cbxCodigoCursoDisciplina);
		
		JPanel tabProfessores = new JPanel();
		tabbedPane.addTab("Professores", null, tabProfessores, null);
		tabProfessores.setLayout(null);
		
		JLabel lblTituloProfessores = new JLabel("Professores");
		lblTituloProfessores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTituloProfessores.setBounds(10, 11, 133, 25);
		tabProfessores.add(lblTituloProfessores);
		
		JLabel lblCPFProfessor = new JLabel("CPF: ");
		lblCPFProfessor.setBounds(10, 95, 78, 21);
		tabProfessores.add(lblCPFProfessor);
		
		JLabel lblNomeProfessor = new JLabel("Nome completo: ");
		lblNomeProfessor.setBounds(10, 142, 93, 21);
		tabProfessores.add(lblNomeProfessor);
		
		JLabel lblAreaProfessor = new JLabel("Área de Conhecimento: ");
		lblAreaProfessor.setBounds(10, 189, 116, 21);
		tabProfessores.add(lblAreaProfessor);
		
		JLabel lblPontuacaoProfessor = new JLabel("Pontuação: ");
		lblPontuacaoProfessor.setBounds(10, 236, 116, 21);
		tabProfessores.add(lblPontuacaoProfessor);
		
		JTextArea txaTabelaProfessores = new JTextArea();
		txaTabelaProfessores.setBounds(10, 283, 599, 119);
		tabProfessores.add(txaTabelaProfessores);
		
		JButton btnInserirProfessor = new JButton("Adicionar Novo Professor");
		btnInserirProfessor.setBounds(446, 124, 163, 23);
		tabProfessores.add(btnInserirProfessor);
		
		JButton btnAtualizarProfessor = new JButton("Atualizar Professor");
		btnAtualizarProfessor.setBounds(446, 161, 163, 23);
		tabProfessores.add(btnAtualizarProfessor);
		
		JButton btnRemoverProfessor = new JButton("Remover Professor");
		btnRemoverProfessor.setBounds(446, 198, 163, 23);
		tabProfessores.add(btnRemoverProfessor);
		
		JButton btnConsultarProfessorCPF = new JButton("Consultar por CPF");
		btnConsultarProfessorCPF.setBounds(446, 235, 163, 23);
		tabProfessores.add(btnConsultarProfessorCPF);
		
		txtCPFProfessor = new JTextField();
		txtCPFProfessor.setBounds(146, 95, 272, 20);
		tabProfessores.add(txtCPFProfessor);
		txtCPFProfessor.setColumns(10);
		
		txtNomeProfessor = new JTextField();
		txtNomeProfessor.setColumns(10);
		txtNomeProfessor.setBounds(146, 142, 272, 20);
		tabProfessores.add(txtNomeProfessor);
		
		JComboBox cbxAreaProfessor = new JComboBox();
		cbxAreaProfessor.setBounds(146, 188, 272, 20);
		tabProfessores.add(cbxAreaProfessor);
		
		txtPontuacaoProfessor = new JTextField();
		txtPontuacaoProfessor.setColumns(10);
		txtPontuacaoProfessor.setBounds(146, 236, 272, 20);
		tabProfessores.add(txtPontuacaoProfessor);
		
		JPanel tabInscricoes = new JPanel();
		tabbedPane.addTab("Inscrições", null, tabInscricoes, null);
		tabInscricoes.setLayout(null);
		
		JLabel lblInscricoes = new JLabel("Inscrições");
		lblInscricoes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInscricoes.setBounds(10, 18, 122, 25);
		tabInscricoes.add(lblInscricoes);
		
		JLabel lblDisciplinasInscricao = new JLabel("Disciplinas: ");
		lblDisciplinasInscricao.setBounds(10, 90, 79, 14);
		tabInscricoes.add(lblDisciplinasInscricao);
		
		JLabel lblProfessoresInscricao = new JLabel("Professores: ");
		lblProfessoresInscricao.setBounds(10, 162, 79, 14);
		tabInscricoes.add(lblProfessoresInscricao);
		
		JTextArea txaTabelaInscricoes = new JTextArea();
		txaTabelaInscricoes.setBounds(10, 194, 599, 196);
		tabInscricoes.add(txaTabelaInscricoes);
		
		JComboBox cbxDisciplinasInscricao = new JComboBox();
		cbxDisciplinasInscricao.setBounds(91, 86, 258, 22);
		tabInscricoes.add(cbxDisciplinasInscricao);
		
		JComboBox cbxProfessoresInscricao = new JComboBox();
		cbxProfessoresInscricao.setBounds(91, 158, 258, 22);
		tabInscricoes.add(cbxProfessoresInscricao);
		
		JButton btnInscreverProfessorDisciplina = new JButton("Inscrever Professor em Disciplina");
		btnInscreverProfessorDisciplina.setBounds(404, 128, 205, 23);
		tabInscricoes.add(btnInscreverProfessorDisciplina);
		
		JButton btnRemoverProfessorDisciplina = new JButton("Remover Professor de Disciplina");
		btnRemoverProfessorDisciplina.setBounds(404, 158, 205, 23);
		tabInscricoes.add(btnRemoverProfessorDisciplina);
		
		JButton btnConsultarProfessorDisciplina = new JButton("Consultar Professor da Disciplina");
		btnConsultarProfessorDisciplina.setBounds(404, 86, 205, 23);
		tabInscricoes.add(btnConsultarProfessorDisciplina);
		
		JPanel tabConsultaGeral = new JPanel();
		tabbedPane.addTab("Processos Ativos", null, tabConsultaGeral, null);
		tabConsultaGeral.setLayout(null);
		
		JTextArea txaTabelaProcessosAtivos = new JTextArea();
		txaTabelaProcessosAtivos.setBounds(10, 85, 599, 317);
		tabConsultaGeral.add(txaTabelaProcessosAtivos);
		
		JLabel lblProcessosAtivos = new JLabel("Processos Ativos");
		lblProcessosAtivos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProcessosAtivos.setBounds(10, 11, 158, 25);
		tabConsultaGeral.add(lblProcessosAtivos);
		
		JButton btnAtualizarProcessosAtivos = new JButton("Atualizar");
		btnAtualizarProcessosAtivos.setBounds(10, 47, 89, 23);
		tabConsultaGeral.add(btnAtualizarProcessosAtivos);
	}
}
