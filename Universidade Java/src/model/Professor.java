package model;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Pessoa{
    protected String matricula;
    protected String especialidade;
    private double salario;
    List<Curso> cursoProfessor;

    public Professor(String nome, String cpf, int idade, String matricula, String especialidade, double salario, List<Curso> curso) {
        super(nome, cpf, idade);
        this.matricula = matricula;
        this.especialidade = especialidade;
        // recebe la no curso direto
        this.salario = salario;
        // cria new lista de cursos lecionados pelos professores, precisa declarar na classe
        this.cursoProfessor = (curso != null) ? curso : new ArrayList<>();
    }

    public Professor(String nome, String cpf, int idade, String genero, String matricula, String especialidade, double salario) {
        super(nome, cpf, idade, genero);
        this.matricula = matricula;
        this.especialidade = especialidade;
        this.salario = salario;
    }

    // para associar prof a curso
    // precisa ja vir com a busca de professor ja feita ex: professorEncontrado.atribuiCurso()
    public void atribuirCurso(Curso curso){
        cursoProfessor.add(curso);
    }

    // cursos que o professor ministra
    public void exibirCursos(){
        for (Curso curso : cursoProfessor) {
            System.out.println("Ministra aula: " + curso.getNome());            
        }
    }

    @Override
    public void exibirDetalhes(){
        System.out.println("\n\nProfessor "+ this.nome+" :");
        System.out.println("MAtricula: "+ this.matricula);
        System.out.println("Idade: "+ this.idade);
        System.out.println("Especialidade: "+ this.especialidade);
        System.out.println("Salário: " + this.salario);
        if (!cursoProfessor.isEmpty()) {
            System.out.println("Cursos que ministra:");
            this.exibirCursos();
        }
    }
    
    public String getNome(){
        return nome;
    }

    public String getEspecialidade(){
        return especialidade;
    }

    public String getMatricula() {
        return matricula;
    }


}
