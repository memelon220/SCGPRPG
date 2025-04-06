package iu;

import java.util.Scanner;
import iu.TelaCriacaoPersonagem;
import iu.TelaCadastroUsuario;
import fachada.SCGPRPG;
import fachada.FachadaJogador;
import fachada.FachadaNarrador;
import servico.entidade.Jogador;

public class TelaPrincipal {

    private Scanner sc;
    private SCGPRPG fachada;
    private FachadaJogador fachadaJogador;
    private FachadaNarrador fachadaNarrador;
    private TelaCadastroUsuario telaCadastroUsuario;
    private TelaCriacaoPersonagem telaCriacaoPersonagem;

    public TelaPrincipal(SCGPRPG fachada, Jogador usuario) {
        this.sc = new Scanner(System.in);
        this.fachada = fachada;
        this.fachadaJogador = new FachadaJogador(fachada);
        this.fachadaNarrador = new FachadaNarrador(fachada);
        this.telaCadastroUsuario = new TelaCadastroUsuario(fachada);
        this.telaCriacaoPersonagem = new TelaCriacaoPersonagem(fachada, usuario);
    }


    public void iniciar() {
        boolean flag = true;
        System.out.println("Bem vindo ao SCGPRPG!");
        while(flag){
            System.out.println("Digite 1 para criar uma conta ou 2 para criar personagem. Digite 0 para sair.");
            int opcao = sc.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("Obrigado por jogar o SCGPRPG!");
                    flag = false;
                    break;
                case 1:
                    telaCadastroUsuario.solicitarDados();
                    break;
                case 2:
                    telaCriacaoPersonagem.solicitarDados();
                    break;
                default:
                    System.out.println("Opcao invalida. Por favor, tente novamente");
                    iniciar();
                    break;
            }

            
        }
        }

    }
