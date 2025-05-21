package service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import model.*;

public class GestorAcademico {
    List<Professor> professores = new ArrayList<>();
    List<Aluno> alunos = new ArrayList<>();
    Map<String, Curso> cursos = new HashMap<>();
    List<Aluno> alunosBuscadosPorCurso = new ArrayList<>();

    public void cadastrarCurso(Curso c){
        cursos.put(c.getCodigo(), c);
    }

    public void cadastrarCurso(String codigo, String nome, int cargaH){
        Curso c = new Curso(nome, cargaH, codigo);
        cursos.put(c.getCodigo(), c);
    }

    public void contratarProfessor(Professor p){
        professores.add(p);
    }

    public void demitirProfessor(Professor p){
        professores.remove(p);
    }

    public void matricularAluno(Aluno a, String curso_key){
        Curso c = this.cursos.get(curso_key);
        a.matricalarEmCurso(c);
        alunos.add(a);
    }

    public void desmatricalarAluno(Aluno a, String curso_key){
        Curso c = this.cursos.get(curso_key);
        if(c == null){
            System.out.println("Curso com código "+curso_key+" não cadastrado|");
            return;
        }
        a.desmatricalarEmCurso(c);

        if(a.getCursosMatriculados().isEmpty()){
            this.alunos.remove(a);
        }        
    }

    public void matricularAluno(Aluno a, Curso curso){
        Curso c = this.cursos.get(curso.getCodigo());
        if(c == null){
            System.out.println("Curso com código "+curso.getCodigo()+" não cadastrado|");
            return;
        }   
/* 
        if (a == null) {
            System.out.println("Aluno não cadastrado|");
            return;
        }
*/
        a.matricalarEmCurso(c);
        alunos.add(a);
    }

    public void desmatricalarAluno(Aluno a, Curso curso){
        Curso c = this.cursos.get(curso.getCodigo());
        if(c == null){
            System.out.println("Curso com código "+curso.getCodigo()+" não cadastrado|");
            return;
        }
        a.desmatricalarEmCurso(c);

        if(a.getCursosMatriculados().isEmpty()){
            this.alunos.remove(a);
        }
        
    }

    public void listarCursos(){
        cursos.values().stream().forEach(
            curso -> System.out.println(curso.getNome())
        );
    }

    public void listarAlunos(){
        this.alunos.stream().forEach(aluno -> aluno.exibirDetalhes());
    }

    public void listarProfessores(){
        professores.stream()
        .forEach(
            p -> p.exibirDetalhes()
        );
    }

    public void listarProfessoresPorNome(String filterName){
        professores.stream()
        .filter(p -> p.getNome().contains(filterName))
        .forEach(
            p -> System.out.println(p.getNome())
        );
    }

    public void listarProfessoresPorEsp(String filterEsp){
        professores.stream()
        .filter(p -> p.getEspecialidade().contains(filterEsp))
        .forEach(
            p -> System.out.println(p.getNome())
        );
    }
    
    public void listarProfessoresPorFiltro(Predicate<Professor> filtro) {
        professores.stream()
        .filter(filtro)
        .forEach(
            p -> System.out.println(p.getNome())
        );
    }

    public Aluno buscarAluno(String scannerRa){
        for (Aluno aluno : alunos) {
            if (aluno.getRa().equals(scannerRa)) {
                return aluno; // retorna objeto aluno
            }
        }
        return null; // Retorna null se nenhum aluno for encontrado

    }

    //busca pra ligar prof a curso
    public Professor buscarProfessor(String scannerMatr){
        return professores.stream()
           .filter(p -> p.getMatricula().equals(scannerMatr))
           .findFirst()
           .orElse(null); // Retorna null se nenhum professor for encontrado
    }

public Curso buscarCurso(String scannerCodCurso){
        return cursos.values().stream()
            .filter(curso -> curso.getCodigo().equals(scannerCodCurso))
            .findFirst()
            .orElse(null);
    }

    public void buscarAlunosPorCurso(String nomeCurso){
                
    /*    for (Aluno aluno : alunos) {
            if (cursos.getNome().equals(nomeCurso)) { // entra por scanner
                alunosBuscadosPorCurso.add(aluno);
                //return aluno; // retorna objeto aluno que tem tal curso
                System.out.println(" primeiro if"); // nao ta encontrando por nome de curso;
                                                    // digito e vai direto para o prox if
                Pausa.pausarExecucao();
            }
        } 
    */
        // Limpa a lista antes de usar
        alunosBuscadosPorCurso.clear(); // ULTIMA COISA FEITA COLADA PRA BUSCAR POR NOME    DE                         CURSO O ALUNO, FUNCIONOU

        for (Aluno aluno : alunos) {
            // Percorre os cursos matriculados de cada aluno
            for (Curso curso : aluno.getCursosMatriculados()) {
                if (curso.getNome().equalsIgnoreCase(nomeCurso)) { // Verifica o nome do curso
                    alunosBuscadosPorCurso.add(aluno); // Adiciona o aluno à lista
                    System.out.println("Aluno encontrado: " + aluno.getNome());
                    Pausa.pausarExecucao();
                    break; // Para evitar múltiplas adições do mesmo aluno
                }
            }
        }        

        if (alunosBuscadosPorCurso.isEmpty()){
            System.out.println("Nenhum aluno matriculado no curso "+nomeCurso+"|");
            Pausa.pausarExecucao();
            return; // retorna saí
        }
        
    }

    public int contarAlunosPorCurso(String nomeCurso){
        int contador = 0;
        for (Aluno aluno : alunos) {
            for (Curso curso : aluno.getCursosMatriculados()) { // itera na lista
                if (curso.getNome().equalsIgnoreCase(nomeCurso)) { // nome de cada um busco
                    contador += 1; // valor retornado
                }
            }          
        }
        return contador;
    }

}
