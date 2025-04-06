package iu;

import fachada.SCGPRPG;
import servico.excecao.jogador.JogadorJaExisteException;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.jogador.TipoJogadorNaoExisteException;

import java.util.Scanner;

public class TelaCadastroUsuario {

    private Scanner sc;
    private SCGPRPG fachada;

    public TelaCadastroUsuario(SCGPRPG fachada) {
        this.sc = new Scanner(System.in);
        this.fachada = fachada;
    }

    public void solicitarDados(){
        System.out.println(">>>> Que tipo de conta deseja criar? Digite 1 para Jogador e 2 para Narrador<<<<");
        int narradorJogador = sc.nextInt();
        System.out.println(">>>> Dados Pessoais <<<<");
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        System.out.println("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();
        boolean flag = false;
        while(!flag){
            try{
                fachada.criarJogador(nome, idade, narradorJogador);
                flag = true;
                System.out.println("Conta criada com sucesso!");
            } catch(JogadorJaExisteException e){
                System.out.print("...");
            } catch(TipoJogadorNaoExisteException e){
                System.out.println(e.getMessage());
                System.out.println("Por favor, escolha um tipo de conta válida (1 ou 2).");
                solicitarDados();
                return;
            }
        }
    }

}

