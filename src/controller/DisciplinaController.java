package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.lucassbatista.AlgoritmosOrdenacao;
import br.lucassbatista.ed.FilaDinamica;
import br.lucassbatista.ed.ListaEncadeada;
import model.Disciplina;

public class DisciplinaController implements ActionListener {
	private JTextField txtCodigoDisciplina;
	private JTextField txtNomeDisciplina;
	private JComboBox<String> cbxDiaSemanaDisciplina;
	private JComboBox<String> cbxHorarioInicio;
	private JComboBox<String> cbxCargaHoraria;
	private JComboBox<String> cbxCodigoCursoDisciplina;
	private JTextArea txaTabelaDisciplinas;
	private String caminho = System.getProperty("user.home") + File.separator + "LSB-TrabalhoSemestral"
			+ File.separator;
	private String nomeArquivo = "disciplinas.csv";

	public DisciplinaController() {
		super();
	}

	public DisciplinaController(JTextField txtCodigoDisciplina, JTextArea txaTabelaDisciplinas) {
		this.txtCodigoDisciplina = txtCodigoDisciplina;
		this.txaTabelaDisciplinas = txaTabelaDisciplinas;
	}
	
	public DisciplinaController(JTextField txtCodigoDisciplina, JTextField txtNomeDisciplina,
			JComboBox<String> cbxDiaSemanaDisciplina, JComboBox<String> cbxHorarioInicio,
			JComboBox<String> cbxCargaHoraria, JComboBox<String> cbxCodigoCursoDisciplina,
			JTextArea txaTabelaDisciplinas) {
		super();
		this.txtCodigoDisciplina = txtCodigoDisciplina;
		this.txtNomeDisciplina = txtNomeDisciplina;
		this.cbxDiaSemanaDisciplina = cbxDiaSemanaDisciplina;
		this.cbxHorarioInicio = cbxHorarioInicio;
		this.cbxCargaHoraria = cbxCargaHoraria;
		this.cbxCodigoCursoDisciplina = cbxCodigoCursoDisciplina;
		this.txaTabelaDisciplinas = txaTabelaDisciplinas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar Nova Disciplina")) {
			adicionarDisciplina();
		} else if (cmd.equals("Atualizar Disciplina")) {
			atualizarDisciplina();
		} else if (cmd.equals("Remover Disciplina")) {
			removerDisciplina();
		} else if (cmd.equals("Consultar por Código")) {
			consultarDisciplinaPorCodigo();
		}
	}

	private void adicionarDisciplina() { // Adiciona novo curso no arquivo de cursos
		try {
			validaCampos();

			Disciplina novaDisciplina = new Disciplina();
			novaDisciplina.setCodigo(Integer.parseInt(txtCodigoDisciplina.getText()));
			novaDisciplina.setNome(txtNomeDisciplina.getText());
			novaDisciplina.setDiaSemana(cbxDiaSemanaDisciplina.getSelectedItem().toString());
			novaDisciplina.setHorarioInicio((cbxHorarioInicio.getSelectedItem().toString()));
			novaDisciplina.setCargaHoraria(Double.parseDouble(cbxCargaHoraria.getSelectedItem().toString()));
			String[] pegaCodigo = cbxCodigoCursoDisciplina.getSelectedItem().toString().split(" ");
			novaDisciplina.setCodCursoDisciplina(Integer.parseInt(pegaCodigo[0]));

			validaExistente(novaDisciplina);

			File dir = new File(caminho);
			if (!dir.exists()) {
				dir.mkdir();
			}

			StringBuilder conteudo = new StringBuilder();

			File arq = new File(caminho, nomeArquivo);
			if (!arq.exists()) {
				conteudo.append(
						"Codigo;Nome da Disciplina;Dia da Semana;Horario de Inicio;Carga Horaria (hrs/aula);Codigo do Curso\n");
			}

			conteudo.append(novaDisciplina.getCodigo());
			conteudo.append(";");
			conteudo.append(novaDisciplina.getNome());
			conteudo.append(";");
			conteudo.append(novaDisciplina.getDiaSemana());
			conteudo.append(";");
			conteudo.append(novaDisciplina.getHorarioInicio());
			conteudo.append(";");
			conteudo.append(novaDisciplina.getCargaHoraria());
			conteudo.append(";");
			conteudo.append(novaDisciplina.getCodCursoDisciplina());
			conteudo.append("\n");

			PrintWriter print = new PrintWriter(new FileWriter(arq, arq.exists() ? true : false));
			print.write(conteudo.toString());
			print.flush();
			print.close();

			txaTabelaDisciplinas.setText("");
			txaTabelaDisciplinas.setText("Disciplina criada com sucesso! \n" + novaDisciplina.toString());

			limpaCampos();

		} catch (Exception e) {
			txaTabelaDisciplinas.setText("");
			txaTabelaDisciplinas.setText(e.getMessage());
		}

	}

	private void atualizarDisciplina() {
		try {
			validaCampos();

			Disciplina novaDisciplina = new Disciplina();
			novaDisciplina.setCodigo(Integer.parseInt(txtCodigoDisciplina.getText()));
			novaDisciplina.setNome(txtNomeDisciplina.getText());
			novaDisciplina.setDiaSemana(cbxDiaSemanaDisciplina.getSelectedItem().toString());
			novaDisciplina.setHorarioInicio((cbxHorarioInicio.getSelectedItem().toString()));
			novaDisciplina.setCargaHoraria(Double.parseDouble(cbxCargaHoraria.getSelectedItem().toString()));
			String[] pegaCodigo = cbxCodigoCursoDisciplina.getSelectedItem().toString().split(" ");
			novaDisciplina.setCodCursoDisciplina(Integer.parseInt(pegaCodigo[0]));

			ListaEncadeada<Disciplina> lista = listarDisciplinas();
			boolean houveAtualizacao = false;

			for (int i = 0; i < lista.size(); i++) {
				Disciplina d = lista.get(i);
				if (d.getCodigo() == novaDisciplina.getCodigo()) {
					lista.remove(i);
					lista.add(novaDisciplina, i);
					houveAtualizacao = true;
					break;
				}
			}

			if (houveAtualizacao == false) {
				throw new Exception("Disciplina não encontrada! Por favor verificar código ou criar nova disciplina");
			}

			int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que seja atualizar esta disciplina?",
					"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

			if (confirmacao == JOptionPane.YES_OPTION) {

				File dir = new File(caminho);
				if (!dir.exists()) {
					dir.mkdir();
				}

				File arq = new File(caminho, nomeArquivo);

				StringBuilder conteudo = new StringBuilder();
				conteudo.append(
						"Codigo;Nome da Disciplina;Dia da Semana;Horario de Inicio;Carga Horaria (hrs/aula);Codigo do Curso\n");

				while (!lista.isEmpty()) {
					Disciplina remove = lista.get(0);
					conteudo.append(remove.getCodigo());
					conteudo.append(";");
					conteudo.append(remove.getNome());
					conteudo.append(";");
					conteudo.append(remove.getDiaSemana());
					conteudo.append(";");
					conteudo.append(remove.getHorarioInicio());
					conteudo.append(";");
					conteudo.append(remove.getCargaHoraria());
					conteudo.append(";");
					conteudo.append(remove.getCodCursoDisciplina());
					conteudo.append("\n");
					lista.removeFirst();
				}

				PrintWriter print = new PrintWriter(new FileWriter(arq, false));
				print.write(conteudo.toString());
				print.flush();
				print.close();

				txaTabelaDisciplinas.setText("");
				txaTabelaDisciplinas.setText("Disciplina atualizada com sucesso! \n" + novaDisciplina.toString());

				limpaCampos();
			} else {
				txaTabelaDisciplinas.setText("");
				txaTabelaDisciplinas.setText("Operação de atualização interrompida!");
			}

		} catch (Exception e) {
			txaTabelaDisciplinas.setText("");
			txaTabelaDisciplinas.setText(e.getMessage());
		}
	}

	private void removerDisciplina() {
		try {
			validaCampos(false);
			int cod = Integer.parseInt(txtCodigoDisciplina.getText());

			ListaEncadeada<Disciplina> lista = listarDisciplinas();
			boolean houveAtualizacao = false;

			Disciplina d = new Disciplina();
			for (int i = 0; i < lista.size(); i++) {
				d = lista.get(i);
				if (d.getCodigo() == cod) {
					lista.remove(i);
					houveAtualizacao = true;
					break;
				}
			}

			if (houveAtualizacao == false) {
				throw new Exception("Disciplina não encontrada! Por favor verificar código ou criar nova disciplina");
			}

			int confirmacao = JOptionPane.showConfirmDialog(null,
					"Tem certeza que seja excluir esta disciplina? A exclusão deletará também qualquer professor e inscrição atrelada a ele!",
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
					Disciplina remove = lista.get(0);
					conteudo.append(remove.getCodigo());
					conteudo.append(";");
					conteudo.append(remove.getNome());
					conteudo.append(";");
					conteudo.append(remove.getDiaSemana());
					conteudo.append(";");
					conteudo.append(remove.getHorarioInicio());
					conteudo.append(";");
					conteudo.append(remove.getCargaHoraria());
					conteudo.append(";");
					conteudo.append(remove.getCodCursoDisciplina());
					conteudo.append("\n");
					lista.removeFirst();
				}

				PrintWriter print = new PrintWriter(new FileWriter(arq, false));
				print.write(conteudo.toString());
				print.flush();
				print.close();

				txaTabelaDisciplinas.setText("");
				txaTabelaDisciplinas.setText("Curso removido com sucesso! \n" + d.toString());

				limpaCampos();
			} else {
				limpaCampos();
				txaTabelaDisciplinas.setText("");
				txaTabelaDisciplinas.setText("Operação de exclusão interrompida!");
			}

		} catch (Exception e) {
			txaTabelaDisciplinas.setText("");
			txaTabelaDisciplinas.setText(e.getMessage());
		}

	}

	private void consultarDisciplinaPorCodigo() {
		try {
			validaCampos(false); // valida o campo antes de tentar converter
			int cod = Integer.parseInt(txtCodigoDisciplina.getText());

			ListaEncadeada<Disciplina> lista = listarDisciplinas();
			FilaDinamica<Disciplina> fila = new FilaDinamica<>();

			for (int i = 0; i < lista.size(); i++) {
				Disciplina d = lista.get(i);
				if (d.getCodigo() == cod) {
					fila.insert(d);
				}
			}

			if (!fila.isEmpty()) {
				txaTabelaDisciplinas.setText(
						"Codigo | Nome da Disciplina | Dia da Semana | Horario de Inicio | Carga Horaria (hrs/aula) | Codigo do Curso\n");
				while (!fila.isEmpty()) {
					Disciplina d = fila.remove();
					txaTabelaDisciplinas.append(d.toString() + "\n");
				}
			} else {
				throw new Exception("Não foi encontrado nenhuma disciplina com o código informado!");
			}

			limpaCampos();
		} catch (Exception e) {
			txaTabelaDisciplinas.setText("");
			txaTabelaDisciplinas.setText(e.getMessage());
		}
	}

	public ListaEncadeada<Disciplina> listarDisciplinas() throws Exception { // Cria lista com conteúdo do arquivo
		ListaEncadeada<Disciplina> lista = new ListaEncadeada<>();

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

					Disciplina disciplina = new Disciplina();
					disciplina.setCodigo(Integer.parseInt(celulas[0]));
					disciplina.setNome(celulas[1]);
					disciplina.setDiaSemana(celulas[2]);
					disciplina.setHorarioInicio(celulas[3]);
					disciplina.setCargaHoraria(Double.parseDouble(celulas[4]));
					disciplina.setCodCursoDisciplina(Integer.parseInt(celulas[5]));

					lista.addLast(disciplina);
				}
			}

			buffer.close();

		}

		return lista;
	}

	private boolean validaCampos() throws Exception { // Valida se campos estão preenchidos corretamente
		// Campo Código
		if (txtCodigoDisciplina.getText().isBlank()) {
			throw new Exception("Código da disciplina vazia");
		}
		try {
			Integer.parseInt(txtCodigoDisciplina.getText());
		} catch (NumberFormatException e) {
			throw new Exception("Código da disciplina inválida");
		}

		// Campo Nome
		if (txtNomeDisciplina.getText().isBlank()) {
			throw new Exception("Nome da disciplina vazia");
		} else if (txtNomeDisciplina.getText().matches(".*\\d.*")) {
			throw new Exception("Nome da disciplina inválida");
		}

		// Campo DiaSemana
		if (cbxDiaSemanaDisciplina.getSelectedItem().toString().isBlank()) {
			throw new Exception("Dia da semana não selecionado");
		}

		// Campo HorarioInicio
		if (cbxHorarioInicio.getSelectedItem().toString().isBlank()) {
			throw new Exception("Horário de inicio não selecionado");
		}

		// Campo CargaHoraria
		if (cbxCargaHoraria.getSelectedItem().toString().isBlank()) {
			throw new Exception("Horário de inicio não selecionado");
		}

		// Campo CodigoCursoDisciplina
		if (cbxCodigoCursoDisciplina.getSelectedItem().toString().isBlank()) {
			throw new Exception("Não há cursos disponiveis! Criar na aba curso e recarregar aplicação!");
		}

		return true;
	}

	private boolean validaCampos(boolean v) throws Exception {
		// Campo Código
		if (txtCodigoDisciplina.getText().isBlank()) {
			throw new Exception("Código da disciplina vazia");
		}
		try {
			Integer.parseInt(txtCodigoDisciplina.getText());
		} catch (NumberFormatException e) {
			throw new Exception("Código da disciplina inválida");
		}

		return true;
	}

	private void validaExistente(Disciplina disciplina) throws Exception { // Valida se codigo já foi usado, para
																			// garantir
																			// entradas únicas
		ListaEncadeada<Disciplina> lista = listarDisciplinas();

		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				Disciplina novaDisciplina = lista.get(i);
				if (novaDisciplina.getCodigo() == disciplina.getCodigo()) {
					throw new Exception(
							"Código da disciplina já existe! Verifique o campo código ou crie nova disciplina");
				}
			}
		}

	}

	private void limpaCampos() { // Limpa campos de texto
		txtCodigoDisciplina.setText("");
		txtNomeDisciplina.setText("");
		cbxDiaSemanaDisciplina.setSelectedIndex(0);
		cbxHorarioInicio.setSelectedIndex(0);
		cbxCargaHoraria.setSelectedIndex(0);
		cbxCodigoCursoDisciplina.setSelectedIndex(0);
	}

	public String[] retornaCodDisciplinas() throws Exception {
		ListaEncadeada<Disciplina> lista = listarDisciplinas();

		if (!lista.isEmpty()) {
			AlgoritmosOrdenacao algoritmosOrdenacao = new AlgoritmosOrdenacao();

			int[] ordenar = new int[lista.size()];

			for (int i = 0; i < ordenar.length; i++) {
				Disciplina d = lista.get(i);
				ordenar[i] = d.getCodigo();
			}

			ordenar = algoritmosOrdenacao.mergeSort(ordenar, 0, ordenar.length - 1);

			String[] codigos = new String[ordenar.length];
			for (int i = 0; i < ordenar.length; i++) {
				Disciplina d = lista.get(i);
				codigos[i] = String.valueOf(ordenar[i]) + " - " + d.getNome();
			}

			return codigos;
		} else {
			return new String[] { "" };
		}
	}

}
