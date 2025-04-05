package main;

import servico.entidade.Dado;
import servico.entidade.Personagem;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
     Personagem personagem = new Personagem("Goku");
     personagem.adicionarXP(610);
     Dado dado = new Dado(20);
     System.out.println("---------------------------------------");

     dado.rolarDado(2);

    }
}