package iu;

import fachada.SCGPRPG;
import servico.excecao.jogador.JogadorJaExisteException;
import servico.excecao.jogador.TipoJogadorNaoExisteException;

import java.util.Scanner;

public class TelaCadastroUsuario {

    private Scanner sc;
    private SCGPRPG fachada;

    public TelaCadastroUsuario(SCGPRPG fachada) {
        this.sc = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void solicitarDados() {
        System.out.println(">>>> Que tipo de conta deseja criar?<<<<");
        System.out.println("1 - Jogador");
        System.out.println("2 - Narrador");
        System.out.println("0 - Cancelar operação");
        int narradorJogador = sc.nextInt();
        sc.nextLine();
        switch(narradorJogador) {
            case 0:
                System.out.println("A operacao foi cancelada");
                break;
            default:
                System.out.println(">>>> Dados Pessoais <<<<");
                System.out.println("Nome: ");
                String nome = sc.nextLine(); // Não será ignorado pois consumimos o newline antes
                System.out.println("Idade: ");
                int idade = sc.nextInt();
                sc.nextLine(); // Consome o newline novamente
                System.out.println("Senha:");
                String senha = sc.nextLine();
                boolean flag = false;

                while (!flag) {
                    try {
                        String id = fachada.criarJogador(nome, idade, senha, narradorJogador);
                        flag = true;
                        System.out.println("Conta criada com sucesso!");
                        System.out.println("Seu ID: "+id+", lembre-se dele, será necessário para logar!");
                    } catch (JogadorJaExisteException e) {
                        System.out.println("Erro: Jogador já existe! A operacao de cadastro ira reiniciar.");
                        solicitarDados();
                    } catch (TipoJogadorNaoExisteException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Por favor, escolha um tipo de conta válida (1 ou 2). A operacao de cadastro ira reiniciar.");
                        solicitarDados();
                    }
                }
                break;
        }

    }
}
