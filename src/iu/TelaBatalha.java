package iu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

import dados.campanha.RepositorioCampanhasArrayList;
import servico.entidade.*;
import fachada.SCGPRPG;

public class TelaBatalha {
    private final Dado d20 = new Dado(20);
    private SCGPRPG fachada;
    private Scanner sc;
    private Narrador usuario;


    public TelaBatalha(SCGPRPG fachada, Narrador usuario) {
        this.fachada = fachada;
        this.sc = new Scanner(System.in);
        this.usuario = usuario;
    }

    public void selecionarCombatentes() {
        listarCampanhas();

        ArrayList<Campanha> campanhas = usuario.getListaCampanhas();

        if (campanhas.isEmpty()) {
            System.out.println("Não há campanhas disponíveis.");
            return;
        }

        System.out.println("Escolha uma campanha:");
        for (int i = 0; i < campanhas.size(); i++) {
            System.out.println((i + 1) + " - " + campanhas.get(i).getNome());
        }

        int escolhaCampanha = sc.nextInt();
        sc.nextLine();

        if (escolhaCampanha < 1 || escolhaCampanha > campanhas.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Campanha campanhaSelecionada = campanhas.get(escolhaCampanha - 1);
        ArrayList<Personagem> personagens = campanhaSelecionada.getPersonagens();

        if (personagens.size() < 2) {
            System.out.println("A campanha precisa de pelo menos dois personagens para iniciar uma batalha.");
            return;
        }

        System.out.println("Personagens disponíveis:");
        for (int i = 0; i < personagens.size(); i++) {
            System.out.println((i + 1) + " - " + personagens.get(i).getNome());
        }

        System.out.print("Escolha o número do primeiro personagem: ");
        int escolha1 = sc.nextInt();
        System.out.print("Escolha o número do segundo personagem: ");
        int escolha2 = sc.nextInt();

        if (escolha1 < 1 || escolha1 > personagens.size() || escolha2 < 1 || escolha2 > personagens.size() || escolha1 == escolha2) {
            System.out.println("Escolhas inválidas.");
            return;
        }

        Personagem p1 = personagens.get(escolha1 - 1);
        Personagem p2 = personagens.get(escolha2 - 1);

        iniciarBatalha(p1, p2);

    }

    public void iniciarBatalha(Personagem p1, Personagem p2) {
        Scanner sc = new Scanner(System.in);

        int rodadas = 1, iniciativa1, iniciativa2, opc;
        boolean entradaValida;

        iniciativa1 = calcularIniciativa(p1);
        iniciativa2 = calcularIniciativa(p2);

        Personagem primeiro = (iniciativa1 >= iniciativa2) ? p1 : p2;
        Personagem segundo = (primeiro == p1) ? p2 : p1;

        System.out.println("---Batalha iniciada! " + primeiro.getNome() + " VS " + segundo.getNome() + "---");

        while (primeiro.getVidaAtual() > 0 && segundo.getVidaAtual() > 0) {
            System.out.println("-------------------------------------------------");
            System.out.println("\n" + rodadas + "º Rodada \n");
            System.out.println("-------------------------------------------------");
            System.out.println("Turno de " + primeiro.getNome());

            exibirStatus(primeiro);

            do {
                int vidaAntes = 0;
                entradaValida = true;

                System.out.println("-------------------------------------------------");
                System.out.println("Digite a sua ação: ");
                System.out.println("    1 - Ataque");
                System.out.println("    2 - Habilidade Especial");

                try {
                    opc = sc.nextInt();

                    switch (opc) {
                        case 1:
                            if (acertoHabilidade(primeiro, segundo)) {
                                vidaAntes = segundo.getVidaAtual();
                                primeiro.getClasse().Atacar(segundo);
                                System.out.println(segundo.getNome() + " recebe " + (vidaAntes - segundo.getVidaAtual()) + " de dano");
                            }
                            break;
                        case 2:
                            if (primeiro.getClasse() instanceof Clerigo) {
                                vidaAntes = primeiro.getVidaAtual();
                                primeiro.getClasse().habilidadeEspecial(primeiro);
                                int cura = primeiro.getVidaAtual() - vidaAntes;
                                System.out.println("˚. ✦.˳·˖✶ " + primeiro.getNome() + " recupera " + cura + " de vida! ˚. ✦.˳·˖✶");
                            } else {
                                if (acertoHabilidade(primeiro, segundo)) {
                                    vidaAntes = segundo.getVidaAtual();
                                    primeiro.getClasse().habilidadeEspecial(segundo);
                                    System.out.println("•❅───✧❅✦❅✧───❅• Um golpe poderoso?! " +
                                            segundo.getNome() + " recebe " + (vidaAntes - segundo.getVidaAtual()) +
                                            " de dano! •❅───✧❅✦❅✧───❅•");
                                }
                            }
                            break;
                        default:
                            System.out.println("Operação inválida! Tente novamente.");
                            entradaValida = false;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Digite um número válido (1 ou 2)!");
                    sc.next();
                    entradaValida = false;
                }
            } while (!entradaValida);

            if (segundo.getVidaAtual() <= 0) {
                System.out.println(segundo.getNome() + " foi nocauteado! " + primeiro.getNome() + " é o vencedor!!");
                System.out.println(segundo.getNome() + " Recebe 250 de XP!");
                primeiro.adicionarXP(250);

                //resetando os negocios
                primeiro.setVidaAtual(primeiro.getVidaMax());
                segundo.setVidaAtual(segundo.getVidaMax());

                primeiro.setManaAtual(primeiro.getManaMax());
                segundo.setManaAtual(segundo.getManaMax());
                break;
            }

            Personagem temp = primeiro;
            primeiro = segundo;
            segundo = temp;
            rodadas++;
        }
    }

    public int calcularIniciativa(Personagem p) {
        return d20.rolarDado(1) + p.calcularModificador(p.getDestreza());
    }

    public boolean acertoHabilidade(Personagem atacante, Personagem alvo) {
        System.out.println("Rolando ataque...");
        System.out.println("\n");
        int acerto = d20.rolarDado(1) + atacante.getClasse().getBonus();
        System.out.println("...");
        if (acerto >= alvo.getClasseResistencia()) {
            System.out.println(acerto + "! O golpe acerta!\n");
            return true;
        } else {
            System.out.println(acerto + ". É uma falha!\n");
            return false;
        }
    }

    public void exibirStatus(Personagem p) {
        System.out.println("\n");
        System.out.println(p.getNome());
        System.out.println("---------------------");
        System.out.print("Vida: ");
        int percent = (int) ((p.getVidaAtual() * 10.0) / p.getVidaMax());
        for (int i = 0; i < 10; i++) {
            System.out.print(i < percent ? "█" : "░");
        }
        System.out.println(" " + p.getVidaAtual() + "/" + p.getVidaMax());
        System.out.println("Mana: " + p.getManaAtual() + "/" + p.getManaMax());
    }

    public boolean existemCampanhas(ArrayList<Campanha> arrayCampanha) {
        if (arrayCampanha == null || arrayCampanha.isEmpty()) {
            return false;
        }
        return true;
    }

    public void listarCampanhas() {
        if (existemCampanhas(usuario.getListaCampanhas())) {
            for (Campanha c : usuario.getListaCampanhas()) {
                System.out.println("ID: " + c.getID());
                System.out.println("Nome: " + c.getNome() + ", Status: " + c.getStatus());
                System.out.println("Descrição: " + c.getDescricao());
                if (c.getDataFim() == null) {
                    System.out.println("Data de início: " + c.getDataInicio() + ", Data de fim: Campanha em andamento");
                } else {
                    System.out.println("Data de início: " + c.getDataInicio() + ", Data de fim: " + c.getDataFim());
                }
            }
        } else {
            System.out.println("Crie uma campanha antes...");
        }
    }
}