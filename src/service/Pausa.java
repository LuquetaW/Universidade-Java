package service;

import java.util.Scanner;

public class Pausa {
    public static void pausarExecucao(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pressione Enter para continuar.");
        scanner.nextLine(); // aguarda oo usuário pressionar enter
    }
}
