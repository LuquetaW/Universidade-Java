package service;

public class LimpaTela {
    // Método para limpar a tela
    public static void limparTela() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Correção aplicada
        } catch (Exception e) {
            System.err.println("Erro ao limpar a tela: " + e.getMessage());
        }
    }
}