package model;

public class Disciplina {
    private String nome;
    private int caragaHoraria;
    private int periodo;
    private Curso curso;

    public Disciplina(String nome, int caragaHoraria, int periodo){
        this.nome = nome;
        this.caragaHoraria = caragaHoraria;
        this.periodo = periodo;
    }

    public String getNome() {
        return this.nome;
    }

    public int getCaragaHoraria() {
        return this.caragaHoraria;
    }
    
    public int getPeriodo() {
        return this.periodo;
    }
    
    public Curso getCurso() {
        return this.curso;
    }
}
