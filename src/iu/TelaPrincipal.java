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
    private Jogador usuario;
    private SCGPRPG fachada;
    private FachadaJogador fachadaJogador;
    private FachadaNarrador fachadaNarrador;
    private TelaCadastroUsuario telaCadastroUsuario;
    private TelaCriacaoPersonagem telaCriacaoPersonagem;
    private TelaLogin telaLogin;

    public TelaPrincipal(SCGPRPG fachada) {
        this.sc = new Scanner(System.in);
        this.fachada = fachada;
        this.usuario = null;
        this.fachadaJogador = new FachadaJogador(fachada);
        this.fachadaNarrador = new FachadaNarrador(fachada);
        this.telaCadastroUsuario = new TelaCadastroUsuario(fachada);
        this.telaCriacaoPersonagem = new TelaCriacaoPersonagem(fachada, usuario);
        this.telaLogin = new TelaLogin(fachada);
    }


    public void iniciar() {
        boolean flag = true;
        System.out.println("Bem vindo ao SCGPRPG!");
        while(flag){
            System.out.println(">>>> Menu de operações <<<<");
            System.out.println("1 - Cadastrar Usuario");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");
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
                    usuario = telaLogin.logar();
                    if(usuario != null) {
                        System.out.println("Login realizado com sucesso!");
                    }else {
                        System.out.println("ID ou senha incorretos. Por favor, verifique as informacoes e  tente novamente");
                    }
                        break;
                default:
                    System.out.println("Opcao invalida. Por favor, tente novamente");
                    iniciar();
                    break;
            }

            
        }
    }

    public Jogador getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Jogador jogador){
        this.usuario = jogador;
    }

}