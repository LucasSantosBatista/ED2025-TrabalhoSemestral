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

import br.lucassbatista.ed.FilaDinamica;
import br.lucassbatista.ed.ListaEncadeada;
import model.Professor;
import view.Tela;

public class ProfessorController implements ActionListener {
	private JTextField txtCPFProfessor;
	private JTextField txtNomeProfessor;
	private JComboBox<String> cbxAreaProfessor;
	private JTextField txtPontuacaoProfessor;
	private JTextArea txaTabelaProfessores;
	private String caminho = System.getProperty("user.home") + File.separator + "LSB-TrabalhoSemestral"
			+ File.separator;
	private String nomeArquivo = "professores.csv";
	private boolean precisaConfirmacao = true;

	public ProfessorController() {
		super();
	}

	public ProfessorController(JTextField txtCPFProfessor, JTextArea txaTabelaProfessores) {
		this.txtCPFProfessor = txtCPFProfessor;
		this.txaTabelaProfessores = txaTabelaProfessores;
		this.precisaConfirmacao = false;
	}

	public ProfessorController(JTextField txtCPFProfessor, JTextField txtNomeProfessor,
			JComboBox<String> cbxAreaProfessor, JTextField txtPontuacaoProfessor, JTextArea txaTabelaProfessores) {

		this.txtCPFProfessor = txtCPFProfessor;
		this.txtNomeProfessor = txtNomeProfessor;
		this.cbxAreaProfessor = cbxAreaProfessor;
		this.txtPontuacaoProfessor = txtPontuacaoProfessor;
		this.txaTabelaProfessores = txaTabelaProfessores;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar Novo Professor")) {
			adicionarProfessor();

			try {
				new Tela().atualizarComboBox();
				limpaCampos();
			} catch (Exception e1) {
			}

		} else if (cmd.equals("Atualizar Professor")) {
			atualizarProfessor();

			try {
				new Tela().atualizarComboBox();
				limpaCampos();
			} catch (Exception e1) {
			}

		} else if (cmd.equals("Remover Professor")) {
			removerProfessor();

			try {
				new Tela().atualizarComboBox();
				if (precisaConfirmacao == true)
					limpaCampos();
			} catch (Exception e1) {
			}

		} else if (cmd.equals("Consultar por CPF")) {
			consultarProfessorPorCPF();

			try {
				limpaCampos();
			} catch (Exception e1) {
			}
		}
	}

	private void adicionarProfessor() { // Adiciona novo curso no arquivo de cursos
		try {
			validaCampos();

			Professor novoProfessor = new Professor();
			novoProfessor.setCPF(Long.parseLong(txtCPFProfessor.getText()));
			novoProfessor.setNome(txtNomeProfessor.getText());
			novoProfessor.setArea(cbxAreaProfessor.getSelectedItem().toString());
			novoProfessor.setPontuacao(Integer.parseInt(txtPontuacaoProfessor.getText()));

			validaExistente(novoProfessor);

			File dir = new File(caminho);
			if (!dir.exists()) {
				dir.mkdir();
			}

			StringBuilder conteudo = new StringBuilder();

			File arq = new File(caminho, nomeArquivo);
			if (!arq.exists()) {
				conteudo.append("CPF;Nome Completo;Area de Conhecimento;Pontuacao;\n");
			}

			conteudo.append(novoProfessor.getCPF());
			conteudo.append(";");
			conteudo.append(novoProfessor.getNome());
			conteudo.append(";");
			conteudo.append(novoProfessor.getArea());
			conteudo.append(";");
			conteudo.append(novoProfessor.getPontuacao());
			conteudo.append("\n");

			PrintWriter print = new PrintWriter(new FileWriter(arq, arq.exists() ? true : false));
			print.write(conteudo.toString());
			print.flush();
			print.close();

			txaTabelaProfessores.setText("");
			txaTabelaProfessores.setText("Professor cadastrado com sucesso! \n" + novoProfessor.toString());

		} catch (Exception e) {
			txaTabelaProfessores.setText("");
			txaTabelaProfessores.setText(e.getMessage());
		}

	}

	private void atualizarProfessor() {
		try {
			validaCampos();

			Professor novoProfessor = new Professor();
			novoProfessor.setCPF(Long.parseLong(txtCPFProfessor.getText()));
			novoProfessor.setNome(txtNomeProfessor.getText());
			novoProfessor.setArea(cbxAreaProfessor.getSelectedItem().toString());
			novoProfessor.setPontuacao(Integer.parseInt(txtPontuacaoProfessor.getText()));

			ListaEncadeada<Professor> lista = listarProfessores();
			boolean houveAtualizacao = false;

			for (int i = 0; i < lista.size(); i++) {
				Professor p = lista.get(i);
				if (p.getCPF() == novoProfessor.getCPF()) {
					lista.remove(i);
					lista.add(novoProfessor, i);
					houveAtualizacao = true;
					break;
				}
			}

			if (houveAtualizacao == false) {
				throw new Exception("Professor não encontrado! Por favor verificar CPF ou cadastrar novo professor");
			}

			int confirmacao = JOptionPane.showConfirmDialog(null,
					"Tem certeza que seja atualizar dados este professor?", "Confirmação", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);

			if (confirmacao == JOptionPane.YES_OPTION) {

				File dir = new File(caminho);
				if (!dir.exists()) {
					dir.mkdir();
				}

				File arq = new File(caminho, nomeArquivo);

				StringBuilder conteudo = new StringBuilder();
				conteudo.append("CPF;Nome Completo;Area de Conhecimento;Pontuacao;\n");

				while (!lista.isEmpty()) {
					Professor remove = lista.get(0);
					conteudo.append(remove.getCPF());
					conteudo.append(";");
					conteudo.append(remove.getNome());
					conteudo.append(";");
					conteudo.append(remove.getArea());
					conteudo.append(";");
					conteudo.append(remove.getPontuacao());
					conteudo.append("\n");
					lista.removeFirst();
				}

				PrintWriter print = new PrintWriter(new FileWriter(arq, false));
				print.write(conteudo.toString());
				print.flush();
				print.close();

				txaTabelaProfessores.setText("");
				txaTabelaProfessores
						.setText("Dados do Professor atualizado com sucesso! \n" + novoProfessor.toString());

			} else {
				txaTabelaProfessores.setText("");
				txaTabelaProfessores.setText("Operação de atualização interrompida!");
			}

		} catch (Exception e) {
			txaTabelaProfessores.setText("");
			txaTabelaProfessores.setText(e.getMessage());
		}
	}

	private void removerProfessor() {
		try {
			validaCampos(false);
			int cpf = Integer.parseInt(txtCPFProfessor.getText());

			ListaEncadeada<Professor> lista = listarProfessores();
			boolean houveAtualizacao = false;

			Professor p = new Professor();
			for (int i = 0; i < lista.size(); i++) {
				p = lista.get(i);
				if (p.getCPF() == cpf) {
					lista.remove(i);
					houveAtualizacao = true;
					break;
				}
			}

			if (houveAtualizacao == false) {
				throw new Exception("Professor não encontrado! Por favor verificar CPF ou cadastrar novo professor");
			}

			int confirmacao = 0;

			if (precisaConfirmacao == false) {
				confirmacao = JOptionPane.YES_OPTION;
			} else {
				confirmacao = JOptionPane.showConfirmDialog(null,
						"Tem certeza que seja excluir este professor? A exclusão deletará também qualquer inscrição atrelada a ele!",
						"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			}

			if (confirmacao == JOptionPane.YES_OPTION) {
				File dir = new File(caminho);
				if (!dir.exists()) {
					dir.mkdir();
				}

				File arq = new File(caminho, nomeArquivo);

				StringBuilder conteudo = new StringBuilder();
				conteudo.append("CPF;Nome Completo;Area de Conhecimento;Pontuacao;\n");

				while (!lista.isEmpty()) {
					Professor remove = lista.get(0);
					conteudo.append(remove.getCPF());
					conteudo.append(";");
					conteudo.append(remove.getNome());
					conteudo.append(";");
					conteudo.append(remove.getArea());
					conteudo.append(";");
					conteudo.append(remove.getPontuacao());
					conteudo.append("\n");
					lista.removeFirst();
				}

				PrintWriter print = new PrintWriter(new FileWriter(arq, false));
				print.write(conteudo.toString());
				print.flush();
				print.close();

				txaTabelaProfessores.setText("");
				txaTabelaProfessores.setText("Professor removido do cadastro com sucesso! \n" + p.toString());

				
			} else {

				txaTabelaProfessores.setText("");
				txaTabelaProfessores.setText("Operação de exclusão interrompida!");
			}

		} catch (Exception e) {
			txaTabelaProfessores.setText("");
			txaTabelaProfessores.setText(e.getMessage());
		}

	}

	private void consultarProfessorPorCPF() {
		try {
			validaCampos(false);
			int cpf = Integer.parseInt(txtCPFProfessor.getText());

			ListaEncadeada<Professor> lista = listarProfessores();
			FilaDinamica<Professor> fila = new FilaDinamica<>();

			for (int i = 0; i < lista.size(); i++) {
				Professor p = lista.get(i);
				if (p.getCPF() == cpf) {
					fila.insert(p);
				}
			}

			if (!fila.isEmpty()) {
				txaTabelaProfessores.setText("CPF | Nome Completo | Área de Conhecimento | Pontuação\n");
				while (!fila.isEmpty()) {
					Professor p = fila.remove();
					txaTabelaProfessores.append(p.toString() + "\n");
				}
			} else {
				throw new Exception("Não foi encontrado nenhum Professor com o CPF informado!");
			}

		} catch (Exception e) {
			txaTabelaProfessores.setText("");
			txaTabelaProfessores.setText(e.getMessage());
		}
	}

	private void limpaCampos() {
		txtCPFProfessor.setText("");
		txtNomeProfessor.setText("");
		cbxAreaProfessor.setSelectedIndex(0);
		txtPontuacaoProfessor.setText("");
	}

	public ListaEncadeada<Professor> listarProfessores() throws Exception { // Cria lista com conteúdo do arquivo
		ListaEncadeada<Professor> lista = new ListaEncadeada<>();

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

					Professor professor = new Professor();
					professor.setCPF(Long.parseLong(celulas[0]));
					professor.setNome(celulas[1]);
					professor.setArea(celulas[2]);
					professor.setPontuacao(Integer.parseInt(celulas[3]));

					lista.addLast(professor);
				}
			}

			buffer.close();

		}

		return lista;
	}

	private void validaExistente(Professor professor) throws Exception { // Valida se codigo já foi usado, para
																			// garantir
																			// entradas únicas
		ListaEncadeada<Professor> lista = listarProfessores();

		if (!lista.isEmpty()) {
			for (int i = 0; i < lista.size(); i++) {
				Professor novoProfessor = lista.get(i);
				if (novoProfessor.getCPF() == professor.getCPF()) {
					throw new Exception("CPF já existe! Verifique o campo CPF ou cadastre novo professor");
				}
			}
		}

	}

	private boolean validaCampos() throws Exception {
		// Campo CPF
		if (txtCPFProfessor.getText().isBlank()) {
			throw new Exception("CPF vazio");
		}
		try {
			Long.parseLong(txtCPFProfessor.getText());
		} catch (NumberFormatException e) {
			throw new Exception("CPF inválido");
		}

		// Campo Nome
		if (txtNomeProfessor.getText().isBlank()) {
			throw new Exception("Nome vazio");
		} else if (txtNomeProfessor.getText().matches(".*\\d.*")) {
			throw new Exception("Nome inválido");
		}

		// Campo Area
		if (cbxAreaProfessor.getSelectedItem() == null || cbxAreaProfessor.getSelectedItem().toString().isBlank()) {
			throw new Exception("Não há áreas disponiveis ou nenhuma foi selecionada!");
		}

		// Campo Pontuacao
		if (txtPontuacaoProfessor.getText().isBlank()) {
			throw new Exception("Pontuação vazia");
		}
		try {
			Integer.parseInt(txtCPFProfessor.getText());
		} catch (NumberFormatException e) {
			throw new Exception("Pontuação inválida");
		}

		return true;
	}

	private boolean validaCampos(boolean v) throws Exception {
		// Campo CPF
		if (txtCPFProfessor.getText().isBlank()) {
			throw new Exception("CPF vazio");
		}
		try {
			Long.parseLong(txtCPFProfessor.getText());
		} catch (NumberFormatException e) {
			throw new Exception("CPF inválido");
		}
		return true;
	}

	public String[] retornaProfessores() throws Exception {
		ListaEncadeada<Professor> lista = listarProfessores();

		String[] professores = new String[lista.size() + 1];

		if (!lista.isEmpty()) {

			for (int i = 0; i < professores.length; i++) {
				if (i == 0) {
					professores[i] = " ";
				} else {
					professores[i] = lista.get(i - 1).toString();
				}
			}

			return professores;
		} else {
			return new String[] { "" };
		}

	}

	public void atualizaComboBox() {
		try {
			String[] areas = new CursoController().retornaAreas();
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(areas);
			cbxAreaProfessor.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
