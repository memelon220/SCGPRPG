package iu;
import fachada.SCGPRPG;
import servico.entidade.Jogador;

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
        System.out.println(">>>>Digite o nome de seu personagem<<<<");
        String nome = sc.nextLine();
        System.out.println("Em que nivel voce gostaria de comecar com este personagem?");
        int nivel = sc.nextInt();
        sc.nextLine(); // Consome o newline
        System.out.println("Gostaria de digitar os valores dos atributos do personagem? (S/N)");
        String resposta = sc.nextLine().toUpperCase(); // Normaliza a entrada para evitar problemas de case sensitivity

        if (resposta.equals("S")) {
            int forca = lerAtributo("Força");
            int destreza = lerAtributo("Destreza");
            int constituicao = lerAtributo("Constituição");
            int inteligencia = lerAtributo("Inteligência");
            int sabedoria = lerAtributo("Sabedoria");
            int carisma = lerAtributo("Carisma");

            try {
                fachada.criarPersonagem(this.usuario, nome, nivel, forca, destreza, constituicao, inteligencia, sabedoria, carisma);
                System.out.println("Personagem criado com sucesso!");
            } catch (PersonagemJaExisteException e) {
                System.out.println(e.getMessage());
            }

        } else if (resposta.equals("N")) {
            try {
                fachada.criarPersonagem(this.usuario, nome, nivel);
                System.out.println("Personagem criado com sucesso!");
            } catch (PersonagemJaExisteException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Entrada inválida! Por favor, tente novamente.");
            solicitarDados();
        }
    }

    private int lerAtributo(String atributo) {
        int valor;
        do {
            System.out.printf("%s (8-18): ", atributo);
            valor = sc.nextInt();
            sc.nextLine(); // Consome o newline
            if (valor < 8 || valor > 18) {
                System.out.println("Erro: O valor deve estar entre 8 e 18.");
            }
        } while (valor < 8 || valor > 18);
        return valor;
    }

        }
