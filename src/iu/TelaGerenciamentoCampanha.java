package iu;

import fachada.SCGPRPG;
import servico.entidade.*;
import servico.excecao.campanha.CampanhaNaoExisteException;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TelaGerenciamentoCampanha {

    private Scanner sc;
    private SCGPRPG fachada;
    private Narrador usuario;

    public TelaGerenciamentoCampanha(SCGPRPG fachada, Narrador usuario){
        this.fachada = fachada;
        this.usuario = usuario;
        this.sc = new Scanner(System.in);
    }

    public boolean existemJogadores(ArrayList<Jogador> arrayJogador){
        if(arrayJogador == null || arrayJogador.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean existemPersonagens(ArrayList<Personagem> arrayPersonagem){
        if(arrayPersonagem == null || arrayPersonagem.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean existemCampanhas(ArrayList<Campanha> arrayCampanha){
        if(arrayCampanha == null || arrayCampanha.isEmpty()){
            return false;
        }
        return true;
    }

    public void listarPersonagens(Campanha campanha){
        if(existemPersonagens(campanha.getPersonagens())){
            System.out.println(">>>> Personagens <<<<");
            for(Personagem p : campanha.getPersonagens()){
                System.out.println("Nome:" + p.getNome());
                System.out.println("Especie: " + p.getEspecie().getNome());
                System.out.println("Classe: " + p.getClasse().getNomeClasse());
                System.out.println("XP: " + p.getXP() + ", Nível: " + p.getNivel() + ", Vida: " + p.getVidaAtual() + "/" + p.getVidaMax() + ", Mana: " + p.getManaAtual() + "/" + p.getManaMax());
                System.out.println("Força: " + p.getForca() + ", Destreza: " + p.getDestreza() + ", Constituição: " + p.getConstituicao() + ", Inteligência: " + p.getInteligencia() + ", Sabedoria: " + p.getSabedoria() + ", Carisma: " + p.getCarisma());
                System.out.println("---------------------");
            }
        } else {
            System.out.println("Nenhum personagem foi adicionado...");
        }
    }

    public void listarJogadores(Campanha campanha){
        if(existemJogadores(campanha.getJogadores())){
            System.out.println(">>>> Jogadores <<<<");
            for(Jogador j : campanha.getJogadores()){
                System.out.println("Nome: " + j.getNome() + ", Idade: " + j.getIdade());
                System.out.print("Personagens: [");
                for(Personagem p : campanha.getPersonagens()){
                    if(p.getJogador().equals(j)){
                        System.out.print(" " + p.getNome() + ",");
                    }
                    System.out.println("]");
                }
            }
        } else {
            System.out.println("Nenhum jogador foi adicionado...");
        }
    }

    public void listarCampanhas(){
        if(existemCampanhas(usuario.getListaCampanhas())){
            for(Campanha c : usuario.getListaCampanhas()){
                System.out.println("ID: " + c.getID());
                System.out.println("Nome: " + c.getNome() + ", Status: " + c.getStatus());
                System.out.println("Descrição: " + c.getDescricao());
                System.out.println("Data de início: " + c.getDataInicio() + ", Data de fim: " + c.getDataFim());
                listarPersonagens(c);
                listarJogadores(c);
            }
        }
    }

    /*
    public void aprovarSolicitacao(Campanha campanha, Solicitacao solicitacao) {
        if (campanha.getNarrador().equals(this.nome)) {
            campanha.aprovarSolicitacao(solicitacao);
        } else {
            System.out.println("Você não é o narrador desta campanha!");
        }
    }

    public void enviarConvite(Campanha campanha, Jogador jogador, Personagem personagem) {
        if (campanha.getNarrador().equals(this.nome)) {
            campanha.enviarConvite(jogador, personagem);
        } else {
            System.out.println("Você não é o narrador desta campanha!");
        }
    }
*/
    public void solicitacoesEntradaCampanha(Narrador usuario){

    }

    public void convidarJogador(Campanha campanha, Jogador jogador){

    }

}