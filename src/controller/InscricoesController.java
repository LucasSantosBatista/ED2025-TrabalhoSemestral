package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.lucassbatista.ed.ListaEncadeada;
import model.Disciplina;
import model.Inscricao;
import model.Professor;
import view.Tela;

public class InscricoesController implements ActionListener {
	private JTextField txtCodigoInscricao;
	private JComboBox<String> cbxCPFProfessor;
	private JComboBox<String> cbxCodigoDisciplina;
	private JTextArea txaTabelaInscricoes;
	private String caminho = System.getProperty("user.home") + File.separator + "LSB-TrabalhoSemestral"
			+ File.separator;
	private String nomeArquivo = "inscricoes.csv";
	private boolean precisaConfirmacao = true;

	public InscricoesController() {
		super();
	}

	public InscricoesController(JTextField txtCodigoInscricao, JTextArea txaTabelaInscricoes) {
		this.txtCodigoInscricao = txtCodigoInscricao;
		this.txaTabelaInscricoes = txaTabelaInscricoes;
		precisaConfirmacao = false;
	}

	public InscricoesController(JTextField txtCodigoInscricao, JComboBox<String> cbxCodigoDisciplina,
			JComboBox<String> cbxCPFProfessor, JTextArea txaTabelaInscricoes) {
		this.txtCodigoInscricao = txtCodigoInscricao;
		this.cbxCodigoDisciplina = cbxCodigoDisciplina;
		this.cbxCPFProfessor = cbxCPFProfessor;
		this.txaTabelaInscricoes = txaTabelaInscricoes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar Inscricao")) {
			adicionarInscricao();

			try {
				new Tela().atualizarComboBox();
				limpaCampos();
			} catch (Exception e1) {
			}

		} else if (cmd.equals("Atualizar Inscrição")) {
			atualizarInscricao();

			try {
				new Tela().atualizarComboBox();
				limpaCampos();
			} catch (Exception e1) {
			}

		} else if (cmd.equals("Remover Inscrição")) {
			removerInscricao();

			try {
				new Tela().atualizarComboBox();
				if (precisaConfirmacao == true)
					limpaCampos();
			} catch (Exception e1) {
			}

		} else if (cmd.equals("Consultar por Codigo")) {
			consultarInscricoes();

			try {
				limpaCampos();
			} catch (Exception e1) {
			}
		}
	}

	private void adicionarInscricao() { // Adiciona novo curso no arquivo de cursos
		try {
			validaCampos();

			Inscricao novaInscricao = new Inscricao();
			novaInscricao.setCodigoInscricao(Integer.parseInt(txtCodigoInscricao.getText()));
			String[] pegaCodDisciplina = cbxCodigoDisciplina.getSelectedItem().toString().split(" ");
			novaInscricao.setCodigoDisciplina(Integer.parseInt(pegaCodDisciplina[0]));
			String[] pegaCPFProfessor = cbxCPFProfessor.getSelectedItem().toString().split(" ");
			novaInscricao.setCpfProfessor(Long.parseLong(pegaCPFProfessor[0]));

			validaExistente(novaInscricao);

			File dir = new File(caminho);
			if (!dir.exists()) {
				dir.mkdir();
			}

			StringBuilder conteudo = new StringBuilder();

			File arq = new File(caminho, nomeArquivo);
			if (!arq.exists()) {
				conteudo.append("Codigo;CPF Professor;Codigo Disciplina;\n");
			}

			conteudo.append(novaInscricao.getCodigoInscricao());
			conteudo.append(";");
			conteudo.append(novaInscricao.getCpfProfessor());
			conteudo.append(";");
			conteudo.append(novaInscricao.getCodigoDisciplina());
			conteudo.append("\n");

			PrintWriter print = new PrintWriter(new FileWriter(arq, arq.exists() ? true : false));
			print.write(conteudo.toString());
			print.flush();
			print.close();

			txaTabelaInscricoes.setText("");
			txaTabelaInscricoes.setText("Inscrição feita com sucesso! \n" + novaInscricao.toString());

		} catch (Exception e) {
			txaTabelaInscricoes.setText("");
			txaTabelaInscricoes.setText(e.getMessage());
		}

	}

	private void atualizarInscricao() {
		try {
			validaCampos();

			Inscricao novaInscricao = new Inscricao();
			novaInscricao.setCodigoInscricao(Integer.parseInt(txtCodigoInscricao.getText()));
			String[] pegaCodDisciplina = cbxCodigoDisciplina.getSelectedItem().toString().split(" ");
			novaInscricao.setCodigoDisciplina(Integer.parseInt(pegaCodDisciplina[0]));
			String[] pegaCPFProfessor = cbxCPFProfessor.getSelectedItem().toString().split(" ");
			novaInscricao.setCpfProfessor(Long.parseLong(pegaCPFProfessor[0]));

			ListaEncadeada<Inscricao> lista = listarInscricoes();
			boolean houveAtualizacao = false;

			for (int i = 0; i < lista.size(); i++) {
				Inscricao insc = lista.get(i);
				if (insc.getCodigoInscricao() == novaInscricao.getCodigoInscricao()) {
					lista.remove(i);
					lista.add(novaInscricao, i);
					houveAtualizacao = true;
					break;
				}
			}

			if (houveAtualizacao == false) {
				throw new Exception("Inscrição não encontrada! Por favor verificar código");
			}

			int confirmacao = JOptionPane.showConfirmDialog(null,
					"Tem certeza que deseja atualizar dados esta inscrição?", "Confirmação", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);

			if (confirmacao == JOptionPane.YES_OPTION) {

				File dir = new File(caminho);
				if (!dir.exists()) {
					dir.mkdir();
				}

				File arq = new File(caminho, nomeArquivo);

				StringBuilder conteudo = new StringBuilder();
				conteudo.append("Codigo;CPF Professor;Codigo Disciplina;\n");

				while (!lista.isEmpty()) {
					Inscricao remove = lista.get(0);
					conteudo.append(remove.getCodigoInscricao());
					conteudo.append(";");
					conteudo.append(remove.getCpfProfessor());
					conteudo.append(";");
					conteudo.append(remove.getCodigoDisciplina());
					conteudo.append("\n");
					lista.removeFirst();
				}

				PrintWriter print = new PrintWriter(new FileWriter(arq, false));
				print.write(conteudo.toString());
				print.flush();
				print.close();

				txaTabelaInscricoes.setText("");
				txaTabelaInscricoes
						.setText("Dados da inscrição atualizadas com sucesso! \n" + novaInscricao.toString());

			} else {
				txaTabelaInscricoes.setText("");
				txaTabelaInscricoes.setText("Operação de atualização interrompida!");
			}

		} catch (Exception e) {
			txaTabelaInscricoes.setText("");
			txaTabelaInscricoes.setText(e.getMessage());
		}
	}

	private void removerInscricao() {
		try {
			validaCampos(false);
			int cod = Integer.parseInt(txtCodigoInscricao.getText());

			ListaEncadeada<Inscricao> lista = listarInscricoes();
			boolean houveAtualizacao = false;

			Inscricao insc = new Inscricao();
			for (int i = 0; i < lista.size(); i++) {
				insc = lista.get(i);
				if (insc.getCodigoInscricao() == cod) {
					lista.remove(i);
					houveAtualizacao = true;
					break;
				}
			}

			if (houveAtualizacao == false) {
				throw new Exception("Inscrição não encontrado! Por favor verificar código");
			}

			int confirmacao = 0;

			if (precisaConfirmacao == false) {
				confirmacao = JOptionPane.YES_OPTION;
			} else {
				confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta inscrição?",
						"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			}

			if (confirmacao == JOptionPane.YES_OPTION) {
				File dir = new File(caminho);
				if (!dir.exists()) {
					dir.mkdir();
				}

				File arq = new File(caminho, nomeArquivo);

				StringBuilder conteudo = new StringBuilder();
				conteudo.append("Codigo;CPF Professor;Codigo Disciplina;\n");

				while (!lista.isEmpty()) {
					Inscricao remove = lista.get(0);
					conteudo.append(remove.getCodigoInscricao());
					conteudo.append(";");
					conteudo.append(remove.getCpfProfessor());
					conteudo.append(";");
					conteudo.append(remove.getCodigoDisciplina());
					conteudo.append("\n");
					lista.removeFirst();
				}

				PrintWriter print = new PrintWriter(new FileWriter(arq, false));
				print.write(conteudo.toString());
				print.flush();
				print.close();

				txaTabelaInscricoes.setText("");
				txaTabelaInscricoes.setText("Inscrição removida com sucesso! \n" + insc.toString());

				if (precisaConfirmacao == false) {
					ListaEncadeada<Professor> listaProfessoresApagar = new ProfessorController().listarProfessores();
					for (int i = listaProfessoresApagar.size() - 1; i >= 0; i--) {
						Professor p = listaProfessoresApagar.get(i);
						if (insc.getCpfProfessor()== p.getCPF()) {
							JTextField txtCPFProfessor = new JTextField(String.valueOf(p.getCPF()));
							ProfessorController professorController = new ProfessorController(txtCPFProfessor,
									new JTextArea());

							professorController.actionPerformed(
									new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Remover Professor"));
						}
					}
				}
			} else {
				txaTabelaInscricoes.setText("");
				txaTabelaInscricoes.setText("Operação de exclusão interrompida!");
			}
		} catch (Exception e) {
			txaTabelaInscricoes.setText("");
			txaTabelaInscricoes.setText(e.getMessage());
		}

	}

	private void consultarInscricoes() {
		try {
			validaCampos(false); // valida o campo antes de tentar converter
			int cod = Integer.parseInt(txtCodigoInscricao.getText());

			ListaEncadeada<Inscricao> listaInscricoes = listarInscricoes();
			ListaEncadeada<Disciplina> listaDisciplinas = new DisciplinaController().listarDisciplinas();
			ListaEncadeada<Professor> listaProfessores = new ProfessorController().listarProfessores();

			Inscricao inscricaoEncontrada = null;
			Disciplina disciplinaEncontrada = null;
			Professor professorEncontrado = null;

			for (int i = 0; i < listaInscricoes.size(); i++) {
				Inscricao insc = listaInscricoes.get(i);

				if (insc.getCodigoInscricao() == cod) {
					inscricaoEncontrada = insc;

					// Encontrar disciplina correspondente
					for (int j = 0; j < listaDisciplinas.size(); j++) {
						Disciplina d = listaDisciplinas.get(j);
						if (insc.getCodigoDisciplina() == d.getCodigo()) {
							disciplinaEncontrada = d;
							break;
						}
					}

					// Encontrar professor correspondente
					for (int y = 0; y < listaProfessores.size(); y++) {
						Professor p = listaProfessores.get(y);
						if (insc.getCpfProfessor() == p.getCPF()) {
							professorEncontrado = p;
							break;
						}
					}

					break;
				}
			}

			if (inscricaoEncontrada != null && disciplinaEncontrada != null && professorEncontrado != null) {
				txaTabelaInscricoes.setText("Inscrição #" + inscricaoEncontrada.getCodigoInscricao() + "\nDisciplina:\t"
						+ disciplinaEncontrada.toString() + "\nProfessor:\t" + professorEncontrado.toString());
			} else {
				throw new Exception("Não foi encontrada uma inscrição com o código informado!");
			}

		} catch (Exception e) {
			txaTabelaInscricoes.setText("");
			txaTabelaInscricoes.setText(e.getMessage());
		}
	}

	public ListaEncadeada<Inscricao> listarInscricoes() throws Exception { // Cria lista com conteúdo do arquivo
		ListaEncadeada<Inscricao> lista = new ListaEncadeada<>();

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

					Inscricao inscricao = new Inscricao();
					inscricao.setCodigoInscricao(Integer.parseInt(celulas[0]));
					inscricao.setCodigoDisciplina(Integer.parseInt(celulas[1]));
					inscricao.setCpfProfessor(Long.parseLong(celulas[2]));

					lista.addLast(inscricao);
				}
			}

			buffer.close();

		}

		return lista;
	}

	private void validaExistente(Inscricao inscricao) throws Exception { // Valida se codigo já foi usado, para garantir
																			// entradas únicas
		ListaEncadeada<Inscricao> lista = listarInscricoes();

		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				Inscricao novaInscricao = lista.get(i);
				if (novaInscricao.getCodigoInscricao() == inscricao.getCodigoInscricao()) {
					throw new Exception("Código da inscrição já existe! Verifique o campo código!");
				}
			}
		}

	}

	private boolean validaCampos() throws Exception {
		// Campo Código
		String codigoStr = txtCodigoInscricao.getText().trim();
		if (codigoStr.isEmpty()) {
			throw new Exception("Código da inscrição vazia");
		}
		try {
			Integer.parseInt(codigoStr);
		} catch (NumberFormatException e) {
			throw new Exception("Código da inscrição inválida");
		}

		// Campo Codigo Disciplina
		if (cbxCodigoDisciplina.getItemCount() == 0 || cbxCodigoDisciplina.getSelectedItem() == null
				|| cbxCodigoDisciplina.getSelectedItem().toString().trim().isEmpty()) {
			throw new Exception("Não há disciplinas disponiveis ou nenhum foi selecionado!");
		}

		// Campo CPF Professor
		if (cbxCPFProfessor.getItemCount() == 0 || cbxCPFProfessor.getSelectedItem() == null
				|| cbxCPFProfessor.getSelectedItem().toString().trim().isEmpty()) {
			throw new Exception("Não há professores disponiveis ou nenhum foi selecionado!");
		}

		return true;
	}

	private boolean validaCampos(boolean v) throws Exception {
		// Campo Código
		if (txtCodigoInscricao.getText().isBlank()) {
			throw new Exception("Código da inscrição vazia");
		}
		try {
			Integer.parseInt(txtCodigoInscricao.getText());
		} catch (NumberFormatException e) {
			throw new Exception("Código da inscrição inválida");
		}
		return true;
	}

	private void limpaCampos() {
		txtCodigoInscricao.setText("");
		cbxCPFProfessor.setSelectedIndex(0);
		cbxCodigoDisciplina.setSelectedIndex(0);
	}

	public void atualizaComboBox() {
		try {
			String[] professores = new ProfessorController().retornaProfessores();
			DefaultComboBoxModel<String> modelProfessores = new DefaultComboBoxModel<>(professores);
			cbxCPFProfessor.setModel(modelProfessores);

			String[] disciplinas = new DisciplinaController().retornaDisciplinas();
			DefaultComboBoxModel<String> modelDisciplinas = new DefaultComboBoxModel<>(disciplinas);
			cbxCodigoDisciplina.setModel(modelDisciplinas);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
