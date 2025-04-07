package iu;
import fachada.SCGPRPG;
import servico.entidade.*;

import servico.excecao.personagem.PersonagemJaExisteException;
import java.util.Scanner;

public class TelaCriacaoPersonagem {
    private Scanner sc;
    private SCGPRPG fachada;
    private Jogador usuario;

    public TelaCriacaoPersonagem(SCGPRPG fachada, Jogador usuario){
        this.sc = new Scanner(System.in);
        this.fachada = fachada;
        this.usuario = usuario;
    }

    public void solicitarDados(){
        Personagem personagem_criado = null;
        System.out.println(">>>>Digite o nome de seu personagem<<<<");
        String nome = sc.nextLine();
        System.out.println("Em que nivel voce gostaria de comecar com este personagem?");
        int nivel = sc.nextInt();
        sc.nextLine(); // Consome o newline
        String especie = solicitarEspecie();
        String classe = solicitarClasse();
        System.out.println("Gostaria de digitar os valores dos atributos do personagem? (S/N)");
        String resposta = sc.nextLine().toUpperCase(); // Normaliza a entrada para evitar problemas de case sensitivity


        switch (resposta){
            case "S":
            int forca = lerAtributo("Força");
            int destreza = lerAtributo("Destreza");
            int constituicao = lerAtributo("Constituição");
            int inteligencia = lerAtributo("Inteligência");
            int sabedoria = lerAtributo("Sabedoria");
            int carisma = lerAtributo("Carisma");

            try {
                fachada.criarPersonagem(this.usuario, nome, nivel, forca, destreza, constituicao, inteligencia, sabedoria, carisma, classe, especie);
                System.out.println("Personagem criado com sucesso!");
            } catch (PersonagemJaExisteException e) {
                System.out.println(e.getMessage());
            }

            break;
            case "N":
            try {
                fachada.criarPersonagem(this.usuario, nome, nivel, classe, especie);
                System.out.println("Personagem criado com sucesso!");
            } catch (PersonagemJaExisteException e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            System.out.println("Entrada inválida! Por favor, tente novamente.");
            solicitarDados();
        }

    }

    private String solicitarClasse(){
        System.out.println(">>>>Que classe voce gostaria de comecar com este personagem?<<<<");
        System.out.println("1 - Mago");
        System.out.println("2 - Ladino");
        System.out.println("3 - Guerreiro");
        System.out.println("4 - Clerigo");
        String resposta = sc.nextLine();
        String classe = "";
        switch(resposta) {
            case "1":
                classe = "mago";
                break;
            case "2":
                classe = "ladino";
                break;
            case "3":
                classe = "guerreiro";
                break;
            case "4":
                classe = "clerigo";
                break;
            default:
                System.out.println("Opcao invalida. Por favor, selecione numeros de 1 a 4 para fazer sua escolha");
                solicitarClasse();
                break;
        }
    return classe;
    }

    private String solicitarEspecie() {

        System.out.println(">>>>Que especie voce gostaria de comecar com este personagem?<<<<");
        System.out.println("1 - Humano");
        System.out.println("2 - Halfling");
        System.out.println("3 - Elfo");
        System.out.println("4 - Anao");
        System.out.println("5 - Draconato");
        System.out.println("6 - Ler Descricao");
        EspeciePersonagem descricao = null;
        String resposta = sc.nextLine();
        String especie = "";
        switch (resposta) {
            case "1":
                especie = "humano";
                break;
            case "2":
                especie = "halfling";
                break;
            case "3":
                especie = "elfo";
                break;
            case "4":
                especie = "anao";
                break;
            case "5":
                especie = "draconato";
                break;
            case "6":
                exibirDescricao();
                solicitarEspecie();
                break;
            default:
                System.out.println("Opcao invalida. Por favor, escolha um numero de 1 a 6 para fazer sua escolha");
                solicitarEspecie();
                break;
        }
        return especie;
    }

private void exibirDescricao(){
    System.out.println(">>>>Qual Especie voce quer ler a descricao?<<<<");
    System.out.println("1 - Humano");
    System.out.println("2 - Halfling");
    System.out.println("3 - Elfo");
    System.out.println("4 - Anao");
    System.out.println("5 - Draconato");
    EspeciePersonagem descricao;
    String resposta = sc.nextLine();
    switch (resposta){
        case "1":
            descricao = new Humano();
            System.out.println(descricao.getDescricao());
            break;
        case "2":
            descricao = new Halfling();
            System.out.println(descricao.getDescricao());
            break;
        case "3":
            descricao = new Elfo();
            System.out.println(descricao.getDescricao());
            break;
        case "4":
            descricao = new Anão();
            System.out.println(descricao.getDescricao());
            break;
        case "5":
            descricao = new Draconato();
            System.out.println(descricao.getDescricao());
            break;
        default:
            System.out.println("Opcao invalida. Escolha um numero de 1 a 5 para escolher a descricao que voce deseja ler.");
            exibirDescricao();
            break;
    }
    System.out.println("Gostaria de ler mais uma descricao? S/N");
    resposta = sc.nextLine().toUpperCase();
    switch (resposta){
        case "S":
            exibirDescricao();
            break;
            case "N":
                System.out.println("Voltando a tela de criacao...");
                break;
    }
}
    private int lerAtributo(String atributo) {
        int valor;
        do {
            System.out.printf("%s (8-18): ", atributo);
            valor = sc.nextInt();
            sc.nextLine(); // Consome o newline
            if (valor < 8 || valor > 18) {
                System.out.println("Erro: O valor deve estar entre 8 e 18. Digite o valor novamente");
            }
        } while (valor < 8 || valor > 18);
        return valor;
    }

        }
