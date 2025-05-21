import java.util.List;
import java.util.Scanner;
import model.Aluno;
import model.Curso;
import model.Disciplina;
import model.Professor;
import service.GestorAcademico;
import service.LimpaTela;
import service.Pausa;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        GestorAcademico gestor = new GestorAcademico();

        System.out.println("\n\n\nArquivo LEIAME.txt contém informações importantes da rotina da aplicação.");
        Pausa.pausarExecucao();
        LimpaTela.limparTela();
        
        while (true) {
            LimpaTela.limparTela();
            System.out.println("=== Menu ===");
            System.out.println("1. Listar Alunos");
            System.out.println("2. Listar Cursos - Disciplinas");
            System.out.println("3. Listar Professores - Vincular Curso");
            System.out.println("\n4. Cadastrar Aluno");
            System.out.println("5. Cadastrar Curso");

            System.out.println("6. Cadastrar Professor");
            System.out.println("\n7. Buscar aluno por Curso");
            System.out.println("8. Listar Qnt de Alunos por Curso");
            System.out.println("9. Listar Disciplinas por aluno");
            System.out.println("\n\n0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    //lista os alunos
                    LimpaTela.limparTela();
                    gestor.listarAlunos();
                    Pausa.pausarExecucao();

                    System.out.println("1 - Excluir curso de um aluno \n2 - Adicionar curso à um aluno \n\n0 - Voltar\n");

                    int op = scanner.nextInt(); // reutiliza scanner
                    scanner.nextLine(); // consome quebra de linha
                    
                    if (op == 1) { 
                        System.out.println("Digite o RA do aluno:");
                        Aluno alunoEncontrado = gestor.buscarAluno(scanner.nextLine());//re sc

                        // esse comando que tava printando localização do objeto erradamente
                        //System.out.println(alunoEncontrado.getCursosMatriculados());//relist
                        System.out.println("Aluno encontrado!");

                        System.out.println("Digite o código do curso do aluno:");
                        Curso cursoEncontrado = gestor.buscarCurso(scanner.nextLine());//re

                        try {// tenta
                            LimpaTela.limparTela();
                            alunoEncontrado.desmatricalarEmCurso(cursoEncontrado);
                            gestor.listarAlunos();
                            Pausa.pausarExecucao();
                        } catch (Exception e) {
                            System.out.println("Ocorreu um erro, tente novamente mais tarde!");
                            Pausa.pausarExecucao();
                        }

                    }

                    if (op == 2) { // cadastrar mais um curso em um aluno
                        System.out.println("Digite o RA do aluno: ");
                        String ra_aluno = scanner.nextLine();
                        Aluno alunoEncontrado = gestor.buscarAluno(ra_aluno);

                        System.out.println("Digite o código do curso: ");
                        String cod_curso = scanner.nextLine(); // cod_curso foi usado string
                        Curso cursoEncontrado = gestor.buscarCurso(cod_curso);

                        try {
                            alunoEncontrado.matricalarEmCurso(cursoEncontrado);
                            System.out.println("Aluno matriculado com sucesso!");
                            Pausa.pausarExecucao();
                        } catch (Exception e) {
                            // Captura qualquer exceção que ocorrer
                            System.out.println("Erro ao matricular aluno: " + e.getMessage());
                            Pausa.pausarExecucao();
                        }
                        
                        if (op == 0) {
                            return;
                        }
                    }
                        break;
                case 2:
                    //lista cursos
                    LimpaTela.limparTela();
                    gestor.listarCursos();
                    Pausa.pausarExecucao();

                    System.out.println("1 - Listar Disciplinas do curso \n\n0 - Voltar\n");
                    int op2 = scanner.nextInt();
                    scanner.nextLine(); // limpa
                    if (op2 == 1) {
                        System.out.println("Digite o código do curso: ");
                        Curso cursoEncontrado = gestor.buscarCurso(scanner.nextLine());

                        try {
                            LimpaTela.limparTela();
                            cursoEncontrado.exibirDisciplinas();
                            Pausa.pausarExecucao();
                        } catch (Exception e) {
                            System.out.println("Ocorreu um erro, tente novamente mais tarde!");
                            Pausa.pausarExecucao();
                        }
                    }
                    break;

                case 3:
                    //lista professores
                    LimpaTela.limparTela();
                    gestor.listarProfessores();
                    Pausa.pausarExecucao();
                    System.out.println("1 - Adicionar curso ao professor \n\n0 - Voltar\n");

                    int ope = scanner.nextInt(); // reutiliza scanner
                    scanner.nextLine(); // consome quebra de linha
                    
                    if (ope == 1) { 
                        System.out.println("Digite a matricula do professor: ");
                    
                        Professor profEncontrado = gestor.buscarProfessor(scanner.nextLine());
                        System.out.println("Digite o código do curso: ");
                        Curso cursoEncontrado = gestor.buscarCurso(scanner.nextLine());
                        
                        try {
                            profEncontrado.atribuirCurso(cursoEncontrado);
                            System.out.println("Curso atribuído com sucesso!");
                            Pausa.pausarExecucao();
                        } catch (Exception e) {
                            // Captura qualquer exceção que ocorrer
                            System.out.println("Erro ao atribuir curso: " + e.getMessage());
                            Pausa.pausarExecucao();
                        }
                    }

                    if (ope == 0) {
                        break; 
                    }
                    
                    break;
                    
                case 4:
                    LimpaTela.limparTela();
                    System.out.println("Cadastro de Aluno:");
                    System.out.println("Nome:");
                    String nome = scanner.nextLine();
                    System.out.println("CPF:");
                    String cpf = scanner.nextLine();
                    System.out.println("Idade:");
                    int idade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("RA:");
                    String ra = scanner.nextLine();
                    System.out.println("Código do curso: ");
                    
                    String cod_curso = ""; /* Inicializa a variável com um valor padrão; apenas pra que não fique vermelho o código */
                    
                    boolean codigoValido = false; // flag do while
                    while(codigoValido == false) {

                        cod_curso = scanner.nextLine(); // entrada do valor
                        Curso compara = gestor.buscarCurso(cod_curso); // Tipo Curso recebe o valor da busca

                        if (compara != null && compara.getCodigo().equals(cod_curso)) {
                            codigoValido = true;
                        } else {
                            System.out.println("Código do curso inválido, tente novamente");
                        }
                    }
                    

                Aluno a = new Aluno(nome, cpf, idade, ra);
                gestor.matricularAluno(a, cod_curso);
                Pausa.pausarExecucao();
                break;

                case 5:
                    LimpaTela.limparTela();
                    System.out.println("Cadastro de Curso:");
                    System.out.println("Nome:");
                    String nomeCurso = scanner.nextLine();
                    System.out.println("Carga Horária:");
                    int cargaH = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer após o nextInt()
                    System.out.println("Código:");
                    String cod = scanner.nextLine();
                
                    Curso c = new Curso(nomeCurso, cargaH, cod);
                    gestor.cadastrarCurso(c);
                    Pausa.pausarExecucao();
                
                    // submenu q trata das disciplinas
                    System.out.println("1 - Adicionar Disciplina em um Curso \n\n0 - Voltar\n");
                    int opSubmenu = scanner.nextInt();
                    scanner.nextLine(); // limpa o buffer
                
                    if (opSubmenu == 1) {
                        System.out.println("Digite o código do curso que deseja alterar: ");
                        String codCurso = scanner.nextLine();
                        Curso cursoEncontrado = gestor.buscarCurso(codCurso);
                
                        if (cursoEncontrado != null) { // valida se o curso foi encontrado mesm
                            System.out.println("Curso encontrado: " + cursoEncontrado.getNome() + ".");
                            Pausa.pausarExecucao();
                
                            // looop para adicionar várias disciplinas
                            boolean continuar = true;
                            while (continuar) {
                                System.out.println("Cadastro de Disciplina:");
                                System.out.println("Nome:");
                                String nomeDisciplina = scanner.nextLine();
                
                                System.out.println("Carga Horária:");
                                int cargaHorariaDisciplina = scanner.nextInt();
                                scanner.nextLine(); // Limpa o buffer
                
                                System.out.println("Período:");
                                int periodoDisciplina = scanner.nextInt();
                                scanner.nextLine(); // Limpa o buffer
                
                                Disciplina d = new Disciplina(nomeDisciplina, cargaHorariaDisciplina, periodoDisciplina);
                                cursoEncontrado.adicionarDisciplina(d);
                
                                System.out.println("Quer adicionarr outra disciplina? (1 - Sim, 0 - Não)");
                                int opcaoo = scanner.nextInt();
                                scanner.nextLine(); // Limpa o buffer
                                continuar = (opcaoo == 1);
                            }
                            Pausa.pausarExecucao();
                            break;
                        } else {
                            System.out.println("Curso não encontrado.");
                            Pausa.pausarExecucao();
                            break;
                        }
                    } else {
                        break;
                    }

                case 6:
                    LimpaTela.limparTela();
                    System.out.println("Cadastro de Professor:");
                    System.out.println("Nome:");
                    String nomeProf = scanner.nextLine();
                    System.out.println("CPF:");
                    String cpfProf = scanner.nextLine();
                    System.out.println("Idade:");
                    int idadeProf = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Matricula:");
                    String matricula = scanner.nextLine();
                    System.out.println("Especialidade: ");
                    String esp = scanner.nextLine();
                    System.out.println("Salario: ");
                    
                    double salario = scanner.nextDouble();
                    scanner.nextLine();

                    // passa null os cursos que ele ministra, add depois
                    Professor p = new Professor(nomeProf, cpfProf, idadeProf, matricula, esp, salario, null);
                    gestor.contratarProfessor(p);
                    Pausa.pausarExecucao();
                    break;

                case 7:
                    LimpaTela.limparTela();
                    System.out.println("Digite o nome do curso para encontrar alunos: ");
                    String buscaNomeCurso = scanner.nextLine();
                    gestor.buscarAlunosPorCurso(buscaNomeCurso);
                    break;

                case 8:
                    LimpaTela.limparTela();
                    System.out.println("Digite o nome do curso para saber quantos alunos estão matriculados: ");
                    String nomeCursoConta = scanner.nextLine();
                        System.out.println(gestor.contarAlunosPorCurso(nomeCursoConta));
                        Pausa.pausarExecucao();
                        break;     

                    case 0:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;
                    default:
                        throw new AssertionError();
                }

            }
        
        // Curso c_esoft = new Curso("Engenharia de Soft", 100, "0001");
        // Curso c_adsis = new Curso("Analise de Sis", 60, "0002");
        // Curso c_Eng_civil = new Curso("Engenharia Civil", 160, "0003");
  

        // Professor p = new Professor("Jose pedro", "11111111",35,"987654", "Java", 1000.0);
        // Professor p1 = new Professor("Jose Da silva", "11111112",35,"987653", "C++", 1000.0);
        // Professor p2 = new Professor("Jonas Emanuel", "11111112",35,"987652", "Java", 1000.0);
        // Aluno a = new Aluno("João", "0000000", 20, "1234");



        // GestorAcademico gestor = new GestorAcademico();
        // gestor.contratarProfessor(p);
        // gestor.contratarProfessor(p1);
        // gestor.contratarProfessor(p2);
        // gestor.cadastrarCurso(c_esoft);
        // gestor.cadastrarCurso(c_adsis);
        // gestor.cadastrarCurso(c_Eng_civil);

        // gestor.matricularAluno(a, c_esoft);
        // Aluno a2 = new Aluno("Aluno 2", "0123456", 22,"123456");
        // gestor.matricularAluno(a2, c_adsis);

        // a.getCursosMatriculados().stream().forEach(curso -> System.out.println(curso.getNome()));

    }
}
