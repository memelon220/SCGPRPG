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
            Jogador usuario = fachada.buscarJogador(id);
            if (usuario.getID().charAt(0) == 'J' || usuario.getID().charAt(0) == 'N') {
                if (usuario.getSenha().equals(senha)) {
                    return usuario;
                } else {
                    System.out.println("Senha incorreta!");
                    return null;
                }
            }
        } catch (JogadorNaoExisteException e) {
            System.out.println("Usuario com ID informado não existe.");
        }
        return null;
    }

}