package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import br.lucassbatista.ed.ListaEncadeada;
import model.Curso;
import model.Disciplina;
import model.Inscricao;
import model.Professor;

public class ProcessosAtivosController implements ActionListener {

    private JTextArea txaTabelaProcessosAtivos;

    public ProcessosAtivosController() {
        super();
    }

    public ProcessosAtivosController(JTextArea txaTabelaProcessosAtivos) {
        this.txaTabelaProcessosAtivos = txaTabelaProcessosAtivos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("Atualizar".equals(cmd)) {
            gerarProcessosAtivos();
        }
    }

    public void gerarProcessosAtivos() {
        try {
            ListaEncadeada<Curso> cursos = new CursoController().listarCursos();
            ListaEncadeada<Disciplina> disciplinas = new DisciplinaController().listarDisciplinas();
            ListaEncadeada<Professor> professores = new ProfessorController().listarProfessores();
            ListaEncadeada<Inscricao> inscricoes = new InscricoesController().listarInscricoes();

            @SuppressWarnings("unchecked")
            ListaEncadeada<Disciplina>[] vetorDisciplinasPorCurso = new ListaEncadeada[cursos.size()];

            // Mapeia disciplinas para cada curso
            for (int i = 0; i < cursos.size(); i++) {
                Curso curso = cursos.get(i);
                vetorDisciplinasPorCurso[i] = new ListaEncadeada<>();
                for (int j = 0; j < disciplinas.size(); j++) {
                    Disciplina d = disciplinas.get(j);
                    if (d.getCodCursoDisciplina() == curso.getCodigo()) {
                        vetorDisciplinasPorCurso[i].addLast(d);
                    }
                }
            }

            @SuppressWarnings("unchecked")
            ListaEncadeada<Professor>[] vetorProfessoresPorDisciplina = new ListaEncadeada[disciplinas.size()];

            // Inicializa listas de professores para cada disciplina
            for (int i = 0; i < disciplinas.size(); i++) {
                vetorProfessoresPorDisciplina[i] = new ListaEncadeada<>();
            }

            // Mapeia professores inscritos em cada disciplina
            for (int i = 0; i < disciplinas.size(); i++) {
                Disciplina disciplina = disciplinas.get(i);

                for (int j = 0; j < inscricoes.size(); j++) {
                    Inscricao inscr = inscricoes.get(j);

                    if (inscr.getCodigoDisciplina() == disciplina.getCodigo()) {
                        long cpfProfessorInscricao = inscr.getCpfProfessor();

                        boolean professorJaAdicionado = false;

                        ListaEncadeada<Professor> profsDaDisciplina = vetorProfessoresPorDisciplina[i];
                        for (int x = 0; x < profsDaDisciplina.size(); x++) {
                            Professor pExistente = profsDaDisciplina.get(x);
                            if (pExistente.getCPF() != 0 && pExistente.getCPF() == cpfProfessorInscricao) {
                                professorJaAdicionado = true;
                                break;
                            }
                        }

                        if (!professorJaAdicionado) {
                            for (int k = 0; k < professores.size(); k++) {
                                Professor prof = professores.get(k);
                                if (prof.getCPF() != 0 && prof.getCPF() == cpfProfessorInscricao) {
                                    profsDaDisciplina.addLast(prof);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < cursos.size(); i++) {
                Curso curso = cursos.get(i);
                sb.append("Curso: ").append(curso.getNome()).append(" (Código: ").append(curso.getCodigo())
                        .append(")\n");

                ListaEncadeada<Disciplina> disciplinasDoCurso = vetorDisciplinasPorCurso[i];

                for (int j = 0; j < disciplinasDoCurso.size(); j++) {
                    Disciplina disciplina = disciplinasDoCurso.get(j);
                    sb.append("  └── Disciplina: ").append(disciplina.getNome()).append(" (Código: ")
                            .append(disciplina.getCodigo()).append(")\n");

                    // Encontra o índice da disciplina para buscar seus professores
                    int idxDisciplina = -1;
                    for (int x = 0; x < disciplinas.size(); x++) {
                        if (disciplinas.get(x).getCodigo() == disciplina.getCodigo()) {
                            idxDisciplina = x;
                            break;
                        }
                    }

                    if (idxDisciplina != -1) {
                        ListaEncadeada<Professor> profsDaDisciplina = vetorProfessoresPorDisciplina[idxDisciplina];
                        if (profsDaDisciplina.isEmpty()) {
                            sb.append("      └── Nenhum professor inscrito na disciplina\n");
                        } else {
                            for (int k = 0; k < profsDaDisciplina.size(); k++) {
                                Professor p = profsDaDisciplina.get(k);
                                sb.append("      └── Professor: ").append(p.getNome()).append(" (CPF: ")
                                        .append(p.getCPF()).append(")\n");
                            }
                        }
                    } else {
                        sb.append("      └── Disciplina não encontrada no índice\n");
                    }
                }
                sb.append("\n");
            }

            txaTabelaProcessosAtivos.setText(sb.toString());
            txaTabelaProcessosAtivos.setCaretPosition(0);

        } catch (Exception e) {
            txaTabelaProcessosAtivos.setText("Erro ao gerar processos ativos: " + e.getMessage());
        }
    }
}
