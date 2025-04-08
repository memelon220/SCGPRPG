package iu;
import fachada.SCGPRPG;
import servico.entidade.Jogador;
import servico.entidade.Personagem;
import servico.excecao.campanha.CampanhaLotadaException;

import java.util.ArrayList;
import java.util.Scanner;

    public class TelaSolicitacaoCampanha {
        private final SCGPRPG fachada;
        private final Jogador jogador;
        private final Scanner sc;

        public TelaSolicitacaoCampanha(SCGPRPG fachada, Jogador jogador) {
            this.fachada = fachada;
            this.jogador = jogador;
            this.sc = new Scanner(System.in);
        }

        public void solicitarEntrada() {
            System.out.println("\n>>> SOLICITAR ENTRADA EM CAMPANHA <<<");
            System.out.println("1 - Digitar informacoes");
            System.out.println("0 - Voltar");
            String opcao = sc.nextLine();
            switch(opcao) {
                case "0":
                    break;

                case "1":
                    if (existemPersonagens(jogador.getPersonagens())) {
                        try {
                            System.out.println("\n>>> SOLICITAR ENTRADA EM CAMPANHA <<<");

                            System.out.println("\nSeus personagens:");
                            fachada.getPersonagensDoJogador(jogador.getID()).forEach(p ->
                                    System.out.println("- " + p.getNome() + " (ID: " + p.getID() + ")"));

                            System.out.print("\nID do personagem: ");
                            String pId = sc.nextLine();

                            System.out.print("ID da campanha: ");
                            String cId = sc.nextLine();

                            fachada.solicitarEntradaEmCampanha(jogador.getID(), pId, cId);
                            System.out.println("Solicitação enviada com sucesso!");

                        } catch (CampanhaLotadaException e) {
                            System.out.println("Campanha cheia! Limite: " + e.getLimite() + " jogadores.");
                            System.out.println("Por favor, escolha outra campanha ou cancele a operacao");
                            solicitarEntrada();
                        } catch (Exception e) {
                            System.err.println("Erro na solicitação: " + e.getMessage());
                            System.out.println("Por favor, verifique as informacoes e tente novamente.");
                            solicitarEntrada();
                        }
                    } else {
                        System.out.println("Voce precisa criar um personagem antes...");
                    }
                    break;
            }
        }
        public boolean existemPersonagens(ArrayList<Personagem> arrayPersonagem){
            return arrayPersonagem != null && !arrayPersonagem.isEmpty();
        }
    }

