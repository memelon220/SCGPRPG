package iu;

import fachada.SCGPRPG;
import servico.entidade.Narrador;
import servico.entidade.Solicitacao;
import servico.excecao.campanha.CampanhaNaoExisteException;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.personagem.PersonagemNaoExisteException;

import java.util.Scanner;

public class TelaGerenciamentoSolicitacoes {
    private final SCGPRPG fachada;
    private final Narrador narrador;
    private final Scanner sc;

    public TelaGerenciamentoSolicitacoes(SCGPRPG fachada, Narrador narrador) {
        this.fachada = fachada;
        this.narrador = narrador;
        this.sc = new Scanner(System.in);
    }

    public void gerenciar() {
        if (this.narrador.getListaCampanhas().isEmpty()) {
            System.out.println("Voce nao tem campanhas.");
        }else {
            try {
                System.out.println("\n>>> SOLICITAÇÕES PENDENTES <<<");

                narrador.getListaCampanhas().forEach(campanha -> {
                    System.out.println("\nCampanha: " + campanha.getNome());
                    if (campanha.getSolicitacoes() == null) {
                        System.out.println("Nao ha solicitacoes para esta campanha");
                    } else {
                        campanha.getSolicitacoes().forEach(s ->
                                System.out.println("- " + s.getPersonagem().getNome() +
                                        " (Jogador: " + s.getJogador().getNome() + ")"));
                    }
                });

                System.out.print("\nID da campanha para gerenciar: ");
                String cId = sc.nextLine();

                System.out.print("ID do personagem para aprovar/rejeitar: ");
                String pId = sc.nextLine();

                System.out.print("Aprovar? (S/N): ");
                boolean aprovar = sc.nextLine().equalsIgnoreCase("S");

                fachada.processarSolicitacao(cId, pId, aprovar);
                System.out.println("Operação concluída!");

            } catch (CampanhaNaoExisteException | PersonagemNaoExisteException e) {
                System.out.println(e.getMessage());
                System.out.println("O processo ira recomeçar...");
                gerenciar();
            } catch (Exception e) {
                System.err.println("Erro no processamento: " + e.getMessage());
            }
        }
    }
}