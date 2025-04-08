package iu;

import java.util.Scanner;
import iu.TelaCriacaoPersonagem;
import iu.TelaCadastroUsuario;
import fachada.SCGPRPG;
import fachada.FachadaJogador;
import fachada.FachadaNarrador;
import servico.entidade.Jogador;
import servico.entidade.Narrador;

public class TelaPrincipal {

    private Scanner sc;
    private Jogador usuario;
    private SCGPRPG fachada;
    private FachadaJogador fachadaJogador;
    private FachadaNarrador fachadaNarrador;
    private TelaCadastroUsuario telaCadastroUsuario;
    private TelaCriacaoPersonagem telaCriacaoPersonagem;
    private TelaLogin telaLogin;
    private TelaGerenciamentoPersonagem telaGerenciamentoPersonagem;
    private TelaGerenciamentoCampanha telaGerenciamentoCampanha;

    public TelaPrincipal(SCGPRPG fachada) {
        this.sc = new Scanner(System.in);
        this.fachada = fachada;
        this.usuario = null;
        this.fachadaJogador = new FachadaJogador(fachada);
        this.fachadaNarrador = new FachadaNarrador(fachada);
        this.telaCadastroUsuario = new TelaCadastroUsuario(fachada);
        this.telaLogin = new TelaLogin(fachada);
    }


    public void iniciar() {
        boolean flag = true;
        boolean login = false;
        System.out.println("Bem vindo ao SCGPRPG!");
        while(flag){
            if (!login) {
                System.out.println(">>>> Menu de operações <<<<");
                System.out.println("1 - Cadastrar Usuario");
                System.out.println("2 - Login");
                System.out.println("0 - Sair");
                String opcao = sc.nextLine();

                switch (opcao) {
                    case "0":
                        System.out.println("Obrigado por jogar o SCGPRPG!");
                        flag = false;
                        break;
                    case "1":
                        telaCadastroUsuario.solicitarDados();
                        break;
                    case "2":
                        usuario = telaLogin.logar();
                        if (usuario != null) {
                            System.out.println("Login realizado com sucesso!");
                            login = true;
                        } else {
                            System.out.println("ID ou senha incorretos. Por favor, verifique as informacoes e  tente novamente");
                        }
                        break;
                    default:
                        System.out.println("Opcao invalida. Por favor, tente novamente");
                        iniciar();
                        break;
                }
            } else {
                while (login) {
                    if (this.usuario.getID().charAt(0) == 'J') {
                        System.out.println(">>>> Menu de operações <<<<");
                        System.out.println("1 - Criar Personagem");
                        System.out.println("2 - Gerenciar Personagens Existentes");
                        System.out.println("3 - Solicitar Entrada em Campanha");
                        System.out.println("4 - Campanhas Que Estou Participando");
                        System.out.println("0 - Sair");
                        String opcao = sc.nextLine();
                        switch (opcao) {
                            case "1":
                                telaCriacaoPersonagem = new TelaCriacaoPersonagem(fachada, usuario);
                                telaCriacaoPersonagem.solicitarDados();
                                break;
                            case "2":
                                telaGerenciamentoPersonagem = new TelaGerenciamentoPersonagem(fachada, usuario);
                                System.out.println(">>>> Personagens Atuais <<<<");
                                telaGerenciamentoPersonagem.listarPersonagens();
                                System.out.println(">>>> Menu de gerenciamento de personagens <<<<");
                                System.out.println("1 - Atualizar Personagem");
                                System.out.println("2 - Remover Personagem");
                                System.out.println("0 - Voltar");
                                opcao = sc.nextLine();
                                sc.nextLine();
                                switch (opcao) {
                                    case "1":
                                    telaGerenciamentoPersonagem.atualizarPersonagem();
                                        break;
                                    case "2":
                                        telaGerenciamentoPersonagem = new TelaGerenciamentoPersonagem(fachada, usuario);
                                        telaGerenciamentoPersonagem.removerPersonagem();
                                        break;
                                    case "0":
                                        break;
                                }
                                break;
                            case "0":
                                login = false;
                                System.out.println("Saindo da conta...");
                                break;
                            default:
                                break;
                        }
                    } else {
                        System.out.println(">>>> Menu de operações <<<<");
                        System.out.println("1 - Criar Campanha");
                        System.out.println("2 - Gerenciar Campanhas Existentes");
                        System.out.println("3 - Criar Personagem");
                        System.out.println("4 - Gerenciar Personagens Existentes");
                        System.out.println("0 - Sair");
                        String opcao = sc.nextLine();
                        switch (opcao) {
                            case "1":
                                TelaCriacaoCampanha criacaoCampanha = new TelaCriacaoCampanha(fachada, (Narrador) usuario);
                                criacaoCampanha.solicitarDados();
                                break;
                            case "2":
                                TelaGerenciamentoCampanha gerenciamentoCampanha = new TelaGerenciamentoCampanha(fachada, (Narrador) usuario);
                                gerenciamentoCampanha.listarCampanhas();
                                System.out.println(">>>> Menu de operações <<<<");
                                System.out.println("1 - Atualizar Campanha");
                                System.out.println("2 - Remover Campanha");
                                System.out.println("3 - Checar Solicitacoes");
                                System.out.println("4 - Convidar Jogadores");
                                System.out.println("0 - Sair");
                                opcao = sc.nextLine();
                                switch (opcao) {
                                    case "1":
                                        break;
                                    case "2":
                                        break;
                                    case "3":
                                        break;
                                    case "4":
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case "3":
                                telaCriacaoPersonagem = new TelaCriacaoPersonagem(fachada, usuario);
                                telaCriacaoPersonagem.solicitarDados();
                                break;
                            case "4":
                                telaGerenciamentoPersonagem = new TelaGerenciamentoPersonagem(fachada, usuario);
                                System.out.println(">>>> Personagens Atuais <<<<");
                                telaGerenciamentoPersonagem.listarPersonagens();
                                System.out.println(">>>> Menu de gerenciamento de personagens <<<<");
                                System.out.println("1 - Atualizar Personagem");
                                System.out.println("2 - Remover Personagem");
                                System.out.println("0 - Voltar");
                                opcao = sc.nextLine();
                                sc.nextLine();
                                switch (opcao) {
                                    case "1":
                                        telaGerenciamentoPersonagem.atualizarPersonagem();
                                        break;
                                    case "2":
                                        telaGerenciamentoPersonagem = new TelaGerenciamentoPersonagem(fachada, usuario);
                                        telaGerenciamentoPersonagem.removerPersonagem();
                                        break;
                                    case "0":
                                        break;
                                }
                                break;
                            case "0":
                                login = false;
                                System.out.println("Saindo da conta...");
                                break;
                            default:
                                break;
                        }
                    }
                }

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