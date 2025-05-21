package model;

import java.util.ArrayList;
import java.util.List;
import service.Pausa;

public class Aluno extends Pessoa {
    protected String ra;
    protected List<Curso> cursos = new ArrayList<>();
    protected List<Disciplina>  disciplinaMatriculadas = new ArrayList<>();

    public Aluno(String nome, String cpf, int idade, String ra) {
        super(nome, cpf, idade);
        this.ra = ra;
    }

    public Aluno(String nome, String cpf, int idade, String genero, String ra) {
        super(nome, cpf, idade, genero);
        this.ra = ra;
    }

    public Aluno(String nome, String cpf, int idade, String ra, Curso c) {
        super(nome, cpf, idade);
        this.ra = ra;
        this.cursos.add(c);
    }

    public Aluno(String nome, String cpf, int idade, String genero, String ra, Curso c) {
        super(nome, cpf, idade, genero);
        this.ra = ra;
        this.cursos.add(c);
    }
    
    @Override
    public void exibirDetalhes(){
        System.out.println("--------------------");
        System.out.println("Aluno "+ this.nome+" :");
        System.out.println("RA: "+ this.ra);
        // exibe as disciplinas associadas aos cursos matriculados
        System.out.println("Disciplinas dos cursos:");
        if (this.cursos != null && !this.cursos.isEmpty()) {
            this.cursos.forEach(curso -> {
                System.out.println("Curso: " + curso.getNome() + " (" + curso.getCodigo() + ")");
                curso.exibirDisciplinas(); // método que exibe as disciplinas do curso
            });
        } else {
            System.out.println("Nenhum curso encontrado para buscar disciplinas.");
        }

        System.out.println("Idade: "+ this.idade);
        //System.out.println("Curso: "+ ;);
        if (this.cursos != null && !this.cursos.isEmpty()) {
            this.cursos.forEach(curso->System.out.println("Cód Curso: " + curso.getCodigo() + " - Nome Curso: " + curso.getNome()));
            System.out.println("--------------------");
        }else{
            System.out.println("Nenhum curso encontrado!");
        }
    }
    
    public void matricalarEmCurso(Curso c){
        this.cursos.add(c);
    }

    public void desmatricalarEmCurso(Curso c){
        if(c == null){
            System.out.println("Curso inválido.");
            Pausa.pausarExecucao();
            return;
        }
        this.cursos.remove(c);
        System.out.println("Desmatriculado com sucesso!");
        Pausa.pausarExecucao();
    }

    public List<Curso> getCursosMatriculados(){
        return this.cursos;
    }
    
    public String getRa(){
        return this.ra;
    }

    public String getNome(){
        return this.nome;
    }

    //foi criado uma ArrayList onde cada aluno tera suas diciplinas matriculadas
    public void matricularDisciplina(Disciplina disciplina) {
        if (disciplina != null) {   
            disciplinaMatriculadas.add(disciplina);
            System.out.println("Disciplina "+ disciplina.getNome() + " matriculada com sucesso!");
            Pausa.pausarExecucao();
        }else{
            System.out.println("Disciplina inválida.");
            Pausa.pausarExecucao();
        }
    
    }

    public void exibirDisciplinasMatriculadas(){
        if (!disciplinaMatriculadas.isEmpty()) {
            for (Disciplina disciplina : disciplinaMatriculadas) {
                System.out.println(disciplina.getNome());
            }
        }else{
            System.out.println("Não há registros de disciplinas matriculadas.");
        }
    }

}
