package iu;

import fachada.SCGPRPG;
import servico.entidade.Campanha;
import servico.entidade.Jogador;
import servico.entidade.Personagem;
import servico.entidade.Convite;
import servico.excecao.campanha.CampanhaLotadaException;
import servico.excecao.campanha.CampanhaNaoExisteException;
import servico.excecao.jogador.ConviteNaoExisteException;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.personagem.PersonagemNaoElegivelException;

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
            System.out.println(">>> CONVITES RECEBIDOS <<<");

            // Agrupa convites por status
            System.out.println("--- PENDENTES ---");
            convites.stream()
                    .filter(c -> !c.isAceito() && !c.isRecusado())
                    .forEach(this::imprimirConvite);

            System.out.println("--- ACEITOS ---");
            convites.stream()
                    .filter(Convite::isAceito)
                    .forEach(this::imprimirConvite);

            System.out.println("--- RECUSADOS ---");
            convites.stream()
                    .filter(Convite::isRecusado)
                    .forEach(this::imprimirConvite);

            if (convites.stream().anyMatch(c -> !c.isAceito() && !c.isRecusado())) {
                System.out.println("1 - Responder convite pendente");
                System.out.println("2 - Ver detalhes de um convite");
                System.out.println("0 - Voltar");
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
/*
    public void mostrarConvites(Jogador jogador) {
        ArrayList<Convite> pendentes = jogador.getConvitesPendentes();

        if (pendentes.isEmpty()) {
            System.out.println("Nenhum convite pendente.");
            return;
        }

        pendentes.forEach(convite ->
                System.out.printf(
                        "ID: %s | Campanha: %s | Personagem: %s\n",
                        convite.getId(),
                        convite.getCampanha().getNome(),
                        convite.getPersonagem().getNome()
                )
        );
    }
*/
    public void processarResposta(Jogador jogador, String conviteId, boolean aceitar) {
        try {
            if (aceitar) {
                fachada.aceitarConvite(jogador.getID(), conviteId);
                System.out.println("Convite aceito! Personagem adicionado à campanha.");
            } else {
                fachada.recusarConvite(jogador.getID(), conviteId);
                System.out.println("Convite recusado.");
            }
        } catch (CampanhaNaoExisteException e) {
            System.err.println("Campanha não existe mais!");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private void responderConvite() {
        try {
            System.out.print("\nID do convite que deseja responder: ");
            String conviteId = sc.nextLine();

            System.out.print("Aceitar convite? (S/N): ");
            boolean aceitar = sc.nextLine().equalsIgnoreCase("S");
            if(aceitar) {
                fachada.aceitarConvite(jogador.getID(), conviteId);
                System.out.println("Convite aceito com sucesso! Você foi adicionado a campanha.");
            }else{
                fachada.recusarConvite(jogador.getID(), conviteId);
                System.out.println("Convite recusado com sucesso!");
            }

        } catch (ConviteNaoExisteException | PersonagemNaoElegivelException | CampanhaNaoExisteException |
                 JogadorNaoExisteException e) {
            System.out.println(e.getMessage());
            return;
        } catch(CampanhaLotadaException e){
           System.out.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao responder convite: " + e.getMessage());
            return;
        }
    }

    private void verDetalhesConvite() {
        try {
            System.out.print("\nID do convite: ");
            String conviteId = sc.nextLine();

            Convite convite = fachada.buscarConvitePorId(conviteId, jogador);

            if (!convite.getJogador().getID().equals(jogador.getID())) {
                System.out.println("Este convite não é seu!");
                return;
            }

            System.out.println(">>>> DETALHES DO CONVITE <<<<");
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