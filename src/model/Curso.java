package model;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    protected String nome;
    protected int cargaH;
    protected String codigo;
    protected List<Disciplina> disciplinas = new ArrayList<>();

    public Curso(String nome, int cargaH, String codigo) {
        this.nome = nome;
        this.cargaH = cargaH;
        this.codigo = codigo;
    }

    public String getNome(){
        return nome;
    }

    public int getCargaH(){
        return cargaH;
    }

    public String getCodigo(){
        return codigo;
    }

    public void adicionarDisciplina(Disciplina disciplina){
        if (disciplina != null){ 
            this.disciplinas.add(disciplina);
            System.out.println("Disciplina "+ disciplina.getNome()+ " adicionada com sucesso!");
        }else{
            System.out.println("Disciplina inválida.");
        }
    }

    // não passo parâmeto algum, pois apenas exibo a lista
    public void exibirDisciplinas() {
        if (disciplinas != null) {
            for (Disciplina disciplina : disciplinas) {
                System.out.println("Disciplina "+disciplina.getNome()+ " matriculada");
            }
        }else{
            System.out.println("Nenhuma disciplina cadastrada.");
        }
    }
}
