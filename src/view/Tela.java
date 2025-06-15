package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.CursoController;
import controller.DisciplinaController;
import controller.InscricoesController;
import controller.ProcessosAtivosController;
import controller.ProfessorController;

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
	private JTextField txtCodigoInscricao;

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
	 * 
	 * @throws Exception
	 */
	public Tela() throws Exception {
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
		lblCodigoCurso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigoCurso.setBounds(25, 93, 120, 14);
		tabCursos.add(lblCodigoCurso);

		JLabel lblNomeCurso = new JLabel("Nome: ");
		lblNomeCurso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeCurso.setBounds(25, 136, 120, 14);
		tabCursos.add(lblNomeCurso);

		JLabel lblAreaCurso = new JLabel("Área de Conhecimento: ");
		lblAreaCurso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAreaCurso.setBounds(10, 179, 135, 14);
		tabCursos.add(lblAreaCurso);

		JButton btnInserirCurso = new JButton("Adicionar Novo Curso");
		btnInserirCurso.setBounds(426, 58, 171, 23);
		tabCursos.add(btnInserirCurso);

		JButton btnAtualizarCurso = new JButton("Atualizar Curso");
		btnAtualizarCurso.setBounds(426, 98, 171, 23);
		tabCursos.add(btnAtualizarCurso);

		JButton btnRemoverCurso = new JButton("Remover Curso");
		btnRemoverCurso.setBounds(426, 138, 171, 23);
		tabCursos.add(btnRemoverCurso);

		JButton btnConsultarCursoCod = new JButton("Consultar por Código");
		btnConsultarCursoCod.setBounds(426, 178, 171, 23);
		tabCursos.add(btnConsultarCursoCod);

		JTextArea txaTabelaCurso = new JTextArea();
		txaTabelaCurso.setBounds(10, 241, 599, 161);
		tabCursos.add(txaTabelaCurso);

		txtCodigoCurso = new JTextField();
		txtCodigoCurso.setBounds(155, 90, 261, 20);
		tabCursos.add(txtCodigoCurso);
		txtCodigoCurso.setColumns(10);

		txtNomeCurso = new JTextField();
		txtNomeCurso.setColumns(10);
		txtNomeCurso.setBounds(155, 133, 261, 20);
		tabCursos.add(txtNomeCurso);

		txtAreaCurso = new JTextField();
		txtAreaCurso.setColumns(10);
		txtAreaCurso.setBounds(155, 176, 261, 20);
		tabCursos.add(txtAreaCurso);

		JPanel tabDisciplinas = new JPanel();
		tabbedPane.addTab("Disciplinas", null, tabDisciplinas, null);
		tabDisciplinas.setLayout(null);

		JLabel lblTituloDisciplinas = new JLabel("Disciplinas");
		lblTituloDisciplinas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTituloDisciplinas.setBounds(10, 11, 122, 24);
		tabDisciplinas.add(lblTituloDisciplinas);

		JLabel lblCodigoDisciplina = new JLabel("Código: ");
		lblCodigoDisciplina.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigoDisciplina.setBounds(10, 60, 122, 14);
		tabDisciplinas.add(lblCodigoDisciplina);

		JLabel lblNomeDisciplina = new JLabel("Nome: ");
		lblNomeDisciplina.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeDisciplina.setBounds(10, 99, 122, 14);
		tabDisciplinas.add(lblNomeDisciplina);

		JLabel lblDiaSemanaDisciplina = new JLabel("Dia Lecionado: ");
		lblDiaSemanaDisciplina.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiaSemanaDisciplina.setBounds(10, 138, 122, 14);
		tabDisciplinas.add(lblDiaSemanaDisciplina);

		JLabel lblHorarioInicio = new JLabel("Horário da aula: ");
		lblHorarioInicio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHorarioInicio.setBounds(10, 177, 122, 14);
		tabDisciplinas.add(lblHorarioInicio);

		JLabel lblCargaHoraria = new JLabel("Carga Horária: ");
		lblCargaHoraria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCargaHoraria.setBounds(10, 216, 122, 14);
		tabDisciplinas.add(lblCargaHoraria);

		JLabel lblCodigoCursoDisciplina = new JLabel("Código do Curso: ");
		lblCodigoCursoDisciplina.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigoCursoDisciplina.setBounds(10, 255, 122, 14);
		tabDisciplinas.add(lblCodigoCursoDisciplina);

		JButton btnInserirDisciplina = new JButton("Adicionar Nova Disciplina");
		btnInserirDisciplina.setBounds(404, 81, 186, 23);
		tabDisciplinas.add(btnInserirDisciplina);

		JButton btnAtualizarDisciplina = new JButton("Atualizar Disciplina");
		btnAtualizarDisciplina.setBounds(404, 118, 186, 23);
		tabDisciplinas.add(btnAtualizarDisciplina);

		JButton btnRemoverDisciplina = new JButton("Remover Disciplina");
		btnRemoverDisciplina.setBounds(404, 155, 186, 23);
		tabDisciplinas.add(btnRemoverDisciplina);

		JButton btnConsultarDisciplinaCod = new JButton("Consultar por Código");
		btnConsultarDisciplinaCod.setBounds(404, 192, 186, 23);
		tabDisciplinas.add(btnConsultarDisciplinaCod);

		JTextArea txaTabelaDisciplinas = new JTextArea();
		txaTabelaDisciplinas.setBounds(10, 294, 599, 102);
		tabDisciplinas.add(txaTabelaDisciplinas);

		txtCodigoDisciplina = new JTextField();
		txtCodigoDisciplina.setBounds(149, 57, 86, 20);
		tabDisciplinas.add(txtCodigoDisciplina);
		txtCodigoDisciplina.setColumns(10);

		txtNomeDisciplina = new JTextField();
		txtNomeDisciplina.setColumns(10);
		txtNomeDisciplina.setBounds(149, 96, 233, 20);
		tabDisciplinas.add(txtNomeDisciplina);

		JComboBox<String> cbxDiaSemanaDisciplina = new JComboBox<>(
				new String[] { "", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" });
		cbxDiaSemanaDisciplina.setBounds(149, 134, 233, 22);
		tabDisciplinas.add(cbxDiaSemanaDisciplina);

		JComboBox<String> cbxHorarioInicio = new JComboBox<>(new String[] { "", "8:00", "13:00", "19:20" });
		cbxHorarioInicio.setBounds(149, 173, 233, 22);
		tabDisciplinas.add(cbxHorarioInicio);

		JComboBox<String> cbxCargaHoraria = new JComboBox<>(new String[] { "", "1.4", "3.5", "5.0", "19:20" });
		cbxCargaHoraria.setBounds(149, 212, 140, 22);
		tabDisciplinas.add(cbxCargaHoraria);

		JLabel lblHorasaula = new JLabel("horas/aula");
		lblHorasaula.setBounds(299, 216, 83, 14);
		tabDisciplinas.add(lblHorasaula);

		JComboBox<String> cbxCodigoCursoDisciplina = new JComboBox<>(new CursoController().retornaCursos());
		cbxCodigoCursoDisciplina.setBounds(149, 251, 140, 22);
		tabDisciplinas.add(cbxCodigoCursoDisciplina);

		JPanel tabProfessores = new JPanel();
		tabbedPane.addTab("Professores", null, tabProfessores, null);
		tabProfessores.setLayout(null);

		JLabel lblTituloProfessores = new JLabel("Professores");
		lblTituloProfessores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTituloProfessores.setBounds(10, 11, 133, 25);
		tabProfessores.add(lblTituloProfessores);

		JLabel lblCPFProfessor = new JLabel("CPF: ");
		lblCPFProfessor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCPFProfessor.setBounds(10, 95, 133, 21);
		tabProfessores.add(lblCPFProfessor);

		JLabel lblNomeProfessor = new JLabel("Nome completo: ");
		lblNomeProfessor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeProfessor.setBounds(10, 142, 133, 21);
		tabProfessores.add(lblNomeProfessor);

		JLabel lblAreaProfessor = new JLabel("Área de Conhecimento: ");
		lblAreaProfessor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAreaProfessor.setBounds(0, 188, 143, 21);
		tabProfessores.add(lblAreaProfessor);

		JLabel lblPontuacaoProfessor = new JLabel("Pontuação: ");
		lblPontuacaoProfessor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPontuacaoProfessor.setBounds(10, 236, 133, 21);
		tabProfessores.add(lblPontuacaoProfessor);

		JTextArea txaTabelaProfessores = new JTextArea();
		txaTabelaProfessores.setBounds(10, 283, 599, 119);
		tabProfessores.add(txaTabelaProfessores);

		JButton btnInserirProfessor = new JButton("Adicionar Novo Professor");
		btnInserirProfessor.setBounds(428, 111, 181, 23);
		tabProfessores.add(btnInserirProfessor);

		JButton btnAtualizarProfessor = new JButton("Atualizar Professor");
		btnAtualizarProfessor.setBounds(428, 148, 181, 23);
		tabProfessores.add(btnAtualizarProfessor);

		JButton btnRemoverProfessor = new JButton("Remover Professor");
		btnRemoverProfessor.setBounds(428, 185, 181, 23);
		tabProfessores.add(btnRemoverProfessor);

		JButton btnConsultarProfessorCPF = new JButton("Consultar por CPF");
		btnConsultarProfessorCPF.setBounds(428, 222, 181, 23);
		tabProfessores.add(btnConsultarProfessorCPF);

		txtCPFProfessor = new JTextField();
		txtCPFProfessor.setBounds(163, 95, 255, 20);
		tabProfessores.add(txtCPFProfessor);
		txtCPFProfessor.setColumns(10);

		txtNomeProfessor = new JTextField();
		txtNomeProfessor.setColumns(10);
		txtNomeProfessor.setBounds(163, 142, 255, 20);
		tabProfessores.add(txtNomeProfessor);

		JComboBox<String> cbxAreaProfessor = new JComboBox<String>(new CursoController().retornaAreas());
		cbxAreaProfessor.setBounds(163, 188, 255, 20);
		tabProfessores.add(cbxAreaProfessor);

		txtPontuacaoProfessor = new JTextField();
		txtPontuacaoProfessor.setColumns(10);
		txtPontuacaoProfessor.setBounds(163, 236, 255, 20);
		tabProfessores.add(txtPontuacaoProfessor);

		JPanel tabInscricoes = new JPanel();
		tabbedPane.addTab("Inscrições", null, tabInscricoes, null);
		tabInscricoes.setLayout(null);

		JLabel lblInscricoes = new JLabel("Inscrições");
		lblInscricoes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInscricoes.setBounds(10, 18, 122, 25);
		tabInscricoes.add(lblInscricoes);

		JLabel lblDisciplinasInscricao = new JLabel("Disciplinas: ");
		lblDisciplinasInscricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDisciplinasInscricao.setBounds(10, 113, 79, 14);
		tabInscricoes.add(lblDisciplinasInscricao);

		JLabel lblProfessoresInscricao = new JLabel("Professores: ");
		lblProfessoresInscricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProfessoresInscricao.setBounds(10, 151, 79, 14);
		tabInscricoes.add(lblProfessoresInscricao);

		JTextArea txaTabelaInscricoes = new JTextArea();
		txaTabelaInscricoes.setBounds(10, 194, 599, 196);
		tabInscricoes.add(txaTabelaInscricoes);

		JComboBox<String> cbxDisciplinasInscricao = new JComboBox<String>(
				new DisciplinaController().retornaDisciplinas());
		cbxDisciplinasInscricao.setBounds(104, 109, 245, 22);
		tabInscricoes.add(cbxDisciplinasInscricao);

		JComboBox<String> cbxProfessoresInscricao = new JComboBox<String>(
				new ProfessorController().retornaProfessores());
		cbxProfessoresInscricao.setBounds(104, 147, 245, 22);
		tabInscricoes.add(cbxProfessoresInscricao);

		JButton btnInserirInscricao = new JButton("Adicionar Inscricao");
		btnInserirInscricao.setBounds(395, 58, 180, 23);
		tabInscricoes.add(btnInserirInscricao);

		JButton btnRemoverInscricao = new JButton("Remover Inscrição");
		btnRemoverInscricao.setBounds(395, 126, 180, 23);
		tabInscricoes.add(btnRemoverInscricao);

		JButton btnConsultarProfessorInscricao = new JButton("Consultar por Codigo");
		btnConsultarProfessorInscricao.setBounds(395, 160, 180, 23);
		tabInscricoes.add(btnConsultarProfessorInscricao);

		JLabel lblCodigoInscricao = new JLabel("Código: ");
		lblCodigoInscricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigoInscricao.setBounds(10, 78, 79, 14);
		tabInscricoes.add(lblCodigoInscricao);

		txtCodigoInscricao = new JTextField();
		txtCodigoInscricao.setBounds(104, 75, 245, 20);
		tabInscricoes.add(txtCodigoInscricao);
		txtCodigoInscricao.setColumns(10);

		JButton btnAtualizarInscricao = new JButton("Atualizar Inscrição");
		btnAtualizarInscricao.setBounds(395, 92, 180, 23);
		tabInscricoes.add(btnAtualizarInscricao);

		JPanel tabProcessosAtivos = new JPanel();
		tabbedPane.addTab("Processos Ativos", null, tabProcessosAtivos, null);
		tabProcessosAtivos.setLayout(null);

		JTextArea txaTabelaProcessosAtivos = new JTextArea();

		JScrollPane scrollPane = new JScrollPane(txaTabelaProcessosAtivos);
		scrollPane.setBounds(10, 85, 599, 317); // mesmo tamanho e posição do JTextArea anterior

		tabProcessosAtivos.add(scrollPane);

		JLabel lblProcessosAtivos = new JLabel("Processos Ativos");
		lblProcessosAtivos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProcessosAtivos.setBounds(10, 11, 158, 25);
		tabProcessosAtivos.add(lblProcessosAtivos);

		JButton btnAtualizarProcessosAtivos = new JButton("Atualizar");
		btnAtualizarProcessosAtivos.setBounds(10, 47, 89, 23);
		tabProcessosAtivos.add(btnAtualizarProcessosAtivos);

		CursoController cursoController = new CursoController(txtCodigoCurso, txtNomeCurso, txtAreaCurso,
				txaTabelaCurso);
		btnInserirCurso.addActionListener(cursoController);
		btnAtualizarCurso.addActionListener(cursoController);
		btnRemoverCurso.addActionListener(cursoController);
		btnConsultarCursoCod.addActionListener(cursoController);

		DisciplinaController disciplinaController = new DisciplinaController(txtCodigoDisciplina, txtNomeDisciplina,
				cbxDiaSemanaDisciplina, cbxHorarioInicio, cbxCargaHoraria, cbxCodigoCursoDisciplina,
				txaTabelaDisciplinas);
		btnInserirDisciplina.addActionListener(disciplinaController);
		btnAtualizarDisciplina.addActionListener(disciplinaController);
		btnRemoverDisciplina.addActionListener(disciplinaController);
		btnConsultarDisciplinaCod.addActionListener(disciplinaController);

		ProfessorController professorController = new ProfessorController(txtCPFProfessor, txtNomeProfessor,
				cbxAreaProfessor, txtPontuacaoProfessor, txaTabelaProfessores);
		btnInserirProfessor.addActionListener(professorController);
		btnAtualizarProfessor.addActionListener(professorController);
		btnRemoverProfessor.addActionListener(professorController);
		btnConsultarProfessorCPF.addActionListener(professorController);

		InscricoesController inscricoesController = new InscricoesController(txtCodigoInscricao,
				cbxDisciplinasInscricao, cbxProfessoresInscricao, txaTabelaInscricoes);
		btnInserirInscricao.addActionListener(inscricoesController);
		btnAtualizarInscricao.addActionListener(inscricoesController);
		btnRemoverInscricao.addActionListener(inscricoesController);
		btnConsultarProfessorInscricao.addActionListener(inscricoesController);

		ProcessosAtivosController processosAtivosController = new ProcessosAtivosController(txaTabelaProcessosAtivos);
		btnAtualizarProcessosAtivos.addActionListener(processosAtivosController);

	}
}
