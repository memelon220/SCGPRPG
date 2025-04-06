package iu;

import fachada.SCGPRPG;
import servico.entidade.Jogador;
import servico.excecao.jogador.JogadorNaoExisteException;

import java.util.Scanner;

public class TelaLogin {

    private Scanner sc;
    private SCGPRPG fachada;

    public TelaLogin(SCGPRPG fachada) {
        this.sc = new Scanner(System.in);
        this.fachada = fachada;
    }
    public Jogador logar() {
        System.out.println(">>>>Para logar, por favor digite as informações necessárias!<<<<");
        System.out.println("Digite seu ID:");
        String id = sc.nextLine();
        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();
        try {
            Jogador jogador = fachada.buscarJogador(id);

            if (jogador.getID().charAt(0) == 'J') {
                if (jogador.getSenha().equals(senha)) {
                    return jogador;
                } else {
                    System.out.println("Senha incorreta!");
                    return null;
                }
            } else {
                System.out.println("ID inválido.");
            }
        } catch (JogadorNaoExisteException e) {
            System.out.println("Jogador com ID informado não existe.");
        }
        return null;
    }

}