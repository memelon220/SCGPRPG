package iu;

import fachada.SCGPRPG;
import fachada.FachadaJogador;
import servico.entidade.Jogador;
import servico.entidade.Personagem;
import servico.excecao.personagem.PersonagemNaoExisteException;

import java.util.ArrayList;
import java.util.stream.IntStream;

import java.util.Scanner;

public class TelaGerenciamentoPersonagem {

    private Scanner sc;
    private SCGPRPG fachada;
    private FachadaJogador fachadaJogador;
    private TelaCriacaoPersonagem telaCriacaoPersonagem;
    private Jogador usuario;

    public TelaGerenciamentoPersonagem(SCGPRPG fachada, FachadaJogador fachadaJogador, TelaCriacaoPersonagem telaCriacaoPersonagem, Jogador usuario){
        this.fachada = fachada;
        this.fachadaJogador = fachadaJogador;
        this.telaCriacaoPersonagem = telaCriacaoPersonagem;
        this.usuario = usuario;
        this.sc = new Scanner(System.in);
    }

    public void criarPersonagem(){
        telaCriacaoPersonagem.solicitarDados();
    }

    public void listarPersonagens(){
        for(Personagem p : usuario.getPersonagens()){
            System.out.println(p);
        }
    }

    public void atualizarPersonagem() throws PersonagemNaoExisteException {
        System.out.println(">>>> Digite as informações do personagem a ser atualizado!<<<<");
        telaCriacaoPersonagem.solicitarDados();
        System.out.println("Qual personagem deseja atualizar? Digite o ID do personagem");
        System.out.println("Senha:");
        String id = sc.nextLine();
        int index = IntStream.range(0, usuario.getPersonagens().size()).filter(i -> id.equals(usuario.getPersonagens().get(i).getID())).findFirst().orElse(-1);
        usuario.getPersonagens().remove(index);
    }

}