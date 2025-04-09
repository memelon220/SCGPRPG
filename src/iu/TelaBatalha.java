package iu;

import servico.entidade.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TelaBatalha {
    protected final Dado d20 = new Dado(20);

    public void iniciarBatalha(Personagem p1, Personagem p2) {
        Scanner sc = new Scanner(System.in);
        int rodadas = 1, iniciativa1, iniciativa2, opc;
        boolean entradaValida;

        iniciativa1 = calcularIniciativa(p1);
        iniciativa2 = calcularIniciativa(p2);

        Personagem primeiro = (iniciativa1 >= iniciativa2) ? p1 : p2;
        Personagem segundo = (primeiro == p1) ? p2 : p1;

        System.out.println("Batalha iniciada! " + primeiro.getNome() + " VS " + segundo.getNome());

        while (primeiro.getVidaAtual() > 0 && segundo.getVidaAtual() > 0) {
            System.out.println(rodadas + "º Rodada");
            System.out.println("Turno de " + primeiro.getNome());

            exibirStatus(primeiro);

            do {
                int vidaAntes = 0;
                entradaValida = true;

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
                                System.out.println(primeiro.getNome() + " recupera " + cura + " de vida!");
                            } else {
                                if (acertoHabilidade(primeiro, segundo)) {
                                    vidaAntes = segundo.getVidaAtual();
                                    primeiro.getClasse().habilidadeEspecial(segundo);
                                    System.out.println("Um golpe poderoso?! " +
                                            segundo.getNome() + " recebe " + (vidaAntes - segundo.getVidaAtual()) +
                                            " de dano!");
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
                primeiro.adicionarXP(250);
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
        int acerto = d20.rolarDado(1) + atacante.getClasse().getBonus();
        System.out.println("...");
        if (acerto >= alvo.getClasseResistencia()) {
            System.out.println(acerto + " > " + alvo.getClasseResistencia());
            System.out.println(acerto + "! O golpe acerta!");
            return true;
        } else {
            System.out.println(acerto + ". É uma falha!");
            return false;
        }
    }

    public void exibirStatus(Personagem p) {
        System.out.println(p.getNome());
        System.out.println("---------------------");
        System.out.print("Vida: ");
        int percent = (int)((p.getVidaAtual() * 10.0) / p.getVidaMax());
        for (int i = 0; i < 10; i++) {
            System.out.print(i < percent ? "█" : "░");
        }
        System.out.println(" " + p.getVidaAtual() + "/" + p.getVidaMax());
        System.out.println("Mana: " + p.getManaAtual() + "/" + p.getManaMax());
    }
}
