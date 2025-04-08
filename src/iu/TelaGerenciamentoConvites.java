package iu;

import fachada.SCGPRPG;
import servico.entidade.Campanha;
import servico.entidade.Jogador;
import servico.entidade.Personagem;
import servico.entidade.Convite;
import servico.excecao.jogador.ConviteNaoExisteException;

import java.util.Scanner;
import java.util.ArrayList;

public class TelaGerenciamentoConvites {
    private final SCGPRPG fachada;
    private final Jogador jogador;
    private final Scanner sc;

    public TelaGerenciamentoConvites(SCGPRPG fachada, Jogador jogador) {
        this.fachada = fachada;
        this.jogador = jogador;
        this.sc = new Scanner(System.in);
    }

    public void gerenciar() {
        ArrayList<Convite> convites = jogador.getConvitesRecebidos();

        if (convites.isEmpty()) {
            System.out.println("Você não possui convites pendentes.");
            return;
        }

        try {
            System.out.println("\n>>> CONVITES RECEBIDOS <<<");

            // Agrupa convites por status
            System.out.println("\n--- PENDENTES ---");
            convites.stream()
                    .filter(c -> !c.isAceito() && !c.isRecusado())
                    .forEach(this::imprimirConvite);

            System.out.println("\n--- ACEITOS ---");
            convites.stream()
                    .filter(Convite::isAceito)
                    .forEach(this::imprimirConvite);

            System.out.println("\n--- RECUSADOS ---");
            convites.stream()
                    .filter(Convite::isRecusado)
                    .forEach(this::imprimirConvite);

            // Opções apenas para convites pendentes
            if (convites.stream().anyMatch(c -> !c.isAceito() && !c.isRecusado())) {
                System.out.println("\n1 - Responder convite pendente");
                System.out.println("2 - Ver detalhes de um convite");
                System.out.println("0 - Voltar");
                System.out.print("Opção: ");
                String opcao = sc.nextLine();

                switch(opcao) {
                    case "1":
                        responderConvite();
                        break;
                    case "2":
                        verDetalhesConvite();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no processamento: " + e.getMessage());
        }
    }

    private void imprimirConvite(Convite convite) {
        String status = convite.isAceito() ? "ACEITO" :
                convite.isRecusado() ? "RECUSADO" : "PENDENTE";

        System.out.printf("- %s (Campanha: %s | Narrador: %s | Status: %s | ID: %s)\n",
                convite.getPersonagem().getNome(),
                convite.getCampanha().getNome(),
                convite.getCampanha().getNarrador().getNome(),
                status,
                convite.getId());
    }

    private void responderConvite() {
        try {
            System.out.print("\nID do convite que deseja responder: ");
            String conviteId = sc.nextLine();

            System.out.print("Aceitar convite? (S/N): ");
            boolean aceitar = sc.nextLine().equalsIgnoreCase("S");

            fachada.responderConvite(jogador.getID(), conviteId, aceitar);
            System.out.println("Convite " + (aceitar ? "aceito" : "recusado") + " com sucesso!");

        } catch (ConviteNaoExisteException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao responder convite: " + e.getMessage());
        }
    }

    private void verDetalhesConvite() {
        try {
            System.out.print("\nID do convite: ");
            String conviteId = sc.nextLine();

            Convite convite = fachada.buscarConvitePorId(conviteId);

            if (!convite.getJogador().getID().equals(jogador.getID())) {
                System.out.println("Este convite não é seu!");
                return;
            }

            System.out.println("\n=== DETALHES DO CONVITE ===");
            System.out.println("Campanha: " + convite.getCampanha().getNome());
            System.out.println("Narrador: " + convite.getCampanha().getNarrador().getNome());
            System.out.println("Personagem: " + convite.getPersonagem().getNome());
            System.out.println("Nível: " + convite.getPersonagem().getNivel());
            System.out.println("Status: " +
                    (convite.isAceito() ? "ACEITO" :
                            convite.isRecusado() ? "RECUSADO" : "PENDENTE"));
            System.out.println("Data do convite: " + convite.getDataEnvio());
            System.out.println("Descrição da Campanha: " + convite.getCampanha().getDescricao());

        } catch (ConviteNaoExisteException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao buscar convite: " + e.getMessage());
        }
    }
}