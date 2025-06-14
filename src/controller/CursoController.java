package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.lucassbatista.ed.FilaDinamica;
import br.lucassbatista.ed.ListaEncadeada;
import model.Curso;
import model.Disciplina;
import view.Tela;

public class CursoController implements ActionListener {
	private JTextField txtCodigoCurso;
	private JTextField txtNomeCurso;
	private JTextField txtAreaCurso;
	private JTextArea txaTabelaCurso;
	private String caminho = System.getProperty("user.home") + File.separator + "LSB-TrabalhoSemestral"
			+ File.separator;
	private String nomeArquivo = "cursos.csv";

	public CursoController() {
		super();
	}

	public CursoController(JTextField txtCodigoCurso, JTextField txtNomeCurso, JTextField txtAreaCurso,
			JTextArea txaTabelaCurso) {
		super();
		this.txtCodigoCurso = txtCodigoCurso;
		this.txtNomeCurso = txtNomeCurso;
		this.txtAreaCurso = txtAreaCurso;
		this.txaTabelaCurso = txaTabelaCurso;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar Novo Curso")) {
			adicionarCurso();

			try {
				Tela.atualizarComboBox();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			limpaCampos();
		} else if (cmd.equals("Atualizar Curso")) {
			atualizarCurso();

			try {
				Tela.atualizarComboBox();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			limpaCampos();
		} else if (cmd.equals("Remover Curso")) {
			removerCurso();

			try {
				Tela.atualizarComboBox();

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			limpaCampos();
		} else if (cmd.equals("Consultar por Código")) {
			consultarCursoPorCodigo();

			limpaCampos();
		}
	}

	private void adicionarCurso() { // Adiciona novo curso no arquivo de cursos
		try {
			validaCampos();

			Curso novoCurso = new Curso();
			novoCurso.setCodigo(Integer.parseInt(txtCodigoCurso.getText()));
			novoCurso.setNome(txtNomeCurso.getText());
			novoCurso.setArea(txtAreaCurso.getText());

			validaExistente(novoCurso);

			File dir = new File(caminho);
			if (!dir.exists()) {
				dir.mkdir();
			}

			StringBuilder conteudo = new StringBuilder();

			File arq = new File(caminho, nomeArquivo);
			if (!arq.exists()) {
				conteudo.append("Codigo;Nome do Curso;Area de Conhecimento\n");
			}

			conteudo.append(novoCurso.getCodigo());
			conteudo.append(";");
			conteudo.append(novoCurso.getNome());
			conteudo.append(";");
			conteudo.append(novoCurso.getArea());
			conteudo.append("\n");

			PrintWriter print = new PrintWriter(new FileWriter(arq, arq.exists() ? true : false));
			print.write(conteudo.toString());
			print.flush();
			print.close();

			txaTabelaCurso.setText("");
			txaTabelaCurso.setText("Curso criado com sucesso! \n" + novoCurso.toString());

		} catch (Exception e) {
			txaTabelaCurso.setText("");
			txaTabelaCurso.setText(e.getMessage());
		}

	}

	private void atualizarCurso() {
		try {
			validaCampos();

			Curso novoCurso = new Curso();
			novoCurso.setCodigo(Integer.parseInt(txtCodigoCurso.getText()));
			novoCurso.setNome(txtNomeCurso.getText());
			novoCurso.setArea(txtAreaCurso.getText());

			ListaEncadeada<Curso> lista = listarCursos();
			boolean houveAtualizacao = false;

			for (int i = 0; i < lista.size(); i++) {
				Curso c = lista.get(i);
				if (c.getCodigo() == novoCurso.getCodigo()) {
					lista.remove(i);
					lista.add(novoCurso, i);
					houveAtualizacao = true;
					break;
				}
			}

			if (houveAtualizacao == false) {
				throw new Exception("Curso não encontrado! Por favor verificar código ou criar novo curso");
			}

			int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que seja atualizar este curso?",
					"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if (confirmacao == JOptionPane.YES_OPTION) {

				File dir = new File(caminho);
				if (!dir.exists()) {
					dir.mkdir();
				}

				File arq = new File(caminho, nomeArquivo);

				StringBuilder conteudo = new StringBuilder();
				conteudo.append("Codigo;Nome do Curso;Area de Conhecimento\n");

				while (!lista.isEmpty()) {
					Curso remove = lista.get(0);
					conteudo.append(remove.getCodigo());
					conteudo.append(";");
					conteudo.append(remove.getNome());
					conteudo.append(";");
					conteudo.append(remove.getArea());
					conteudo.append("\n");
					lista.removeFirst();
				}

				PrintWriter print = new PrintWriter(new FileWriter(arq, false));
				print.write(conteudo.toString());
				print.flush();
				print.close();

				txaTabelaCurso.setText("");
				txaTabelaCurso.setText("Curso atualizado com sucesso! \n" + novoCurso.toString());

			} else {
				txaTabelaCurso.setText("");
				txaTabelaCurso.setText("Operação de atualização interrompida!");
			}

		} catch (Exception e) {
			txaTabelaCurso.setText("");
			txaTabelaCurso.setText(e.getMessage());
		}
	}

	private void removerCurso() {
		try {
			validaCampos(false);
			int cod = Integer.parseInt(txtCodigoCurso.getText());

			ListaEncadeada<Curso> lista = listarCursos();
			boolean houveAtualizacao = false;
			Curso c = new Curso();

			for (int i = 0; i < lista.size(); i++) {
				c = lista.get(i);
				if (c.getCodigo() == cod) {
					lista.remove(i);
					houveAtualizacao = true;
					break;
				}
			}

			if (!houveAtualizacao) {
				throw new Exception("Curso não encontrado! Por favor verificar código ou criar novo curso");
			}

			int confirmacao = JOptionPane.showConfirmDialog(null,
					"Tem certeza que deseja excluir este curso? A exclusão deletará também qualquer disciplina, professor e inscrição atrelada a ele!",
					"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if (confirmacao == JOptionPane.YES_OPTION) {
				File dir = new File(caminho);
				if (!dir.exists()) {
					dir.mkdir();
				}

				File arq = new File(caminho, nomeArquivo);

				StringBuilder conteudo = new StringBuilder();
				conteudo.append("Codigo;Nome do Curso;Area de Conhecimento\n");

				while (!lista.isEmpty()) {
					Curso remove = lista.get(0);
					conteudo.append(remove.getCodigo()).append(";");
					conteudo.append(remove.getNome()).append(";");
					conteudo.append(remove.getArea()).append("\n");
					lista.removeFirst();
				}

				PrintWriter print = new PrintWriter(new FileWriter(arq, false));
				print.write(conteudo.toString());
				print.flush();
				print.close();

				txaTabelaCurso.setText("");
				txaTabelaCurso.setText("Curso removido com sucesso! \n" + c.toString());

				ListaEncadeada<Disciplina> listaDisciplinasApagar = new DisciplinaController().listarDisciplinas();
				for (int i = listaDisciplinasApagar.size() - 1; i >= 0; i--) {
					Disciplina d = listaDisciplinasApagar.get(i);
					if (d.getCodCursoDisciplina() == c.getCodigo()) {
						JTextField CodigoDisciplina = new JTextField(String.valueOf(d.getCodigo()));
						DisciplinaController disciplinaController = new DisciplinaController(CodigoDisciplina,
								new JTextArea());

						disciplinaController.actionPerformed(
								new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Remover Disciplina"));
					}
				}

			} else {
				txaTabelaCurso.setText("");
				txaTabelaCurso.setText("Operação de exclusão interrompida!");
			}

		} catch (Exception e) {
			txaTabelaCurso.setText("");
			txaTabelaCurso.setText(e.getMessage());
		}
	}

	private void consultarCursoPorCodigo() {
		try {
			validaCampos(false); // valida o campo antes de tentar converter
			int cod = Integer.parseInt(txtCodigoCurso.getText());

			ListaEncadeada<Curso> lista = listarCursos();
			FilaDinamica<Curso> fila = new FilaDinamica<>();

			for (int i = 0; i < lista.size(); i++) {
				Curso c = lista.get(i);
				if (c.getCodigo() == cod) {
					fila.insert(c);
				}
			}

			if (!fila.isEmpty()) {
				txaTabelaCurso.setText("Código | Nome do Curso | Área de Conhecimento\n");
				while (!fila.isEmpty()) {
					Curso c = fila.remove();
					txaTabelaCurso.append(c.toString() + "\n");
				}
			} else {
				throw new Exception("Não foi encontrado nenhum curso com o código informado!");
			}

		} catch (Exception e) {
			txaTabelaCurso.setText("");
			txaTabelaCurso.setText(e.getMessage());
		}
	}

	public ListaEncadeada<Curso> listarCursos() throws Exception { // Cria lista com conteúdo do arquivo
		ListaEncadeada<Curso> lista = new ListaEncadeada<>();

		File dir = new File(caminho);
		if (!dir.exists()) {
			dir.mkdir();
		}

		File arq = new File(caminho, nomeArquivo);

		if (arq.exists() && arq.isFile()) {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));

			String linha = buffer.readLine();

			while ((linha = buffer.readLine()) != null) {
				if (!linha.trim().isEmpty()) {
					String[] celulas = linha.split(";");

					Curso curso = new Curso();
					curso.setCodigo(Integer.parseInt(celulas[0]));
					curso.setNome(celulas[1]);
					curso.setArea(celulas[2]);

					lista.addLast(curso);
				}
			}

			buffer.close();

		}

		return lista;
	}

	private boolean validaCampos() throws Exception { // Valida se campos estão preenchidos corretamente
		// Campo Código
		if (txtCodigoCurso.getText().isBlank()) {
			throw new Exception("Código do curso vazio");
		}
		try {
			Integer.parseInt(txtCodigoCurso.getText());
		} catch (NumberFormatException e) {
			throw new Exception("Código do curso inválido");
		}

		// Campo Nome
		if (txtNomeCurso.getText().isBlank()) {
			throw new Exception("Nome do curso vazio");
		} else if (txtNomeCurso.getText().matches(".*\\d.*")) {
			throw new Exception("Nome do curso inválido");
		}

		// Campo Area
		if (txtAreaCurso.getText().isBlank()) {
			throw new Exception("Área do curso vazia");
		} else if (txtAreaCurso.getText().matches(".*\\d.*")) {
			throw new Exception("Área do curso inválida");
		}

		return true;
	}

	private boolean validaCampos(boolean v) throws Exception {
		// Campo Código
		if (txtCodigoCurso.getText().isBlank()) {
			throw new Exception("Código do curso vazio");
		}
		try {
			Integer.parseInt(txtCodigoCurso.getText());
		} catch (NumberFormatException e) {
			throw new Exception("Código do curso inválido");
		}

		return true;
	}

	private void validaExistente(Curso novoCurso) throws Exception { // Valida se codigo já foi usado, para garantir
																		// entradas únicas
		ListaEncadeada<Curso> lista = listarCursos();

		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				Curso curso = lista.get(i);
				if (novoCurso.getCodigo() == curso.getCodigo()) {
					throw new Exception("Código de curso já existe! Verifique o campo código ou crie novo curso");
				}
			}
		}

	}

	private void limpaCampos() { // Limpa campos de texto
		txtCodigoCurso.setText("");
		txtNomeCurso.setText("");
		txtAreaCurso.setText("");
	}

	public String[] retornaCursos() throws Exception {
		ListaEncadeada<Curso> lista = listarCursos();

		String[] cursos = new String[lista.size() + 1];

		if (!lista.isEmpty()) {

			for (int i = 0; i < cursos.length; i++) {
				if (i == 0) {
					cursos[i] = " ";
				} else {
					cursos[i] = lista.get(i - 1).toString();
				}
			}

			return cursos;
		} else {
			return new String[] { "" };
		}
	}

	public String[] retornaAreas() throws Exception {
		ListaEncadeada<Curso> lista = listarCursos();

		ListaEncadeada<String> listaAreas = new ListaEncadeada<String>();

		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				String area = lista.get(i).getArea();
				boolean existe = false;

				for (int j = 0; j < listaAreas.size(); j++) {
					String areaExistente = listaAreas.get(j);

					if (areaExistente != null && areaExistente.equals(area)) {
						existe = true;
						break;
					}
				}

				if (!existe) {
					listaAreas.addLast(area);
				}
			}

			// Agora usa listaAreas, que só tem áreas únicas
			String[] areas = new String[listaAreas.size() + 1];

			for (int i = 0; i < areas.length; i++) {
				if (i == 0) {
					areas[i] = " ";
				} else {
					areas[i] = listaAreas.get(i - 1); // pega da lista de áreas únicas
				}

			}

			return areas;
		} else {
			return new String[] { "" };
		}
	}
}
