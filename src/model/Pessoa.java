package model;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected int idade;
    protected String genero;

    public Pessoa(String nome, String cpf, int idade){
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public Pessoa(String nome, String cpf, int idade, String genero){
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }

    public abstract void exibirDetalhes();

}
