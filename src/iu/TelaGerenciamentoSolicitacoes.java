package iu;

import fachada.SCGPRPG;
import servico.entidade.Campanha;
import servico.entidade.Narrador;
import servico.entidade.Personagem;
import servico.entidade.Solicitacao;
import servico.excecao.campanha.CampanhaNaoExisteException;
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
                                        " ID: " + s.getPersonagem().getID() +
                                        " (Jogador: " + s.getJogador().getNome() + ")"));
                    }
                });

                String cId = solicitarCampanha();

                System.out.print("ID do personagem para aprovar/rejeitar: ");
                String pId = sc.nextLine();

                System.out.print("Aprovar? (S/N): ");
                boolean aprovar = sc.nextLine().equalsIgnoreCase("S");

                fachada.processarSolicitacao(cId, pId, aprovar);
                System.out.println("Operação concluída!");

            } catch (CampanhaNaoExisteException | PersonagemNaoExisteException e) {
                System.out.println(e.getMessage());
                System.out.println("O processo ira recomecar...");
                gerenciar();
            } catch (Exception e) {
                System.err.println("Erro no processamento: " + e.getMessage());
            }
        }
    }
    public String solicitarCampanha(){
        String cId = null;
        Campanha campanha_aux = null;
            System.out.print("\nID da campanha para gerenciar: ");
            cId = sc.nextLine();
            try {
                campanha_aux = fachada.buscarCampanha(cId);
            }catch (CampanhaNaoExisteException e) {
                System.out.println(e.getMessage());
                solicitarCampanha();
            }
            if (campanha_aux.getSolicitacoes().isEmpty()) {
                System.out.println("Esta campanha nao tem solicitacoes!! Digite o ID de uma campanha que possui solicitacoes.");
                solicitarCampanha();
            }
        return cId;
    }

    public String solicitarPersonagem(String cId){
        String pId = null;
        Personagem personagem_aux = null;
        System.out.print("ID do personagem para aprovar/rejeitar: ");
        pId = sc.nextLine();
        try {
            personagem_aux = fachada.buscarPersonagem(pId);
        }catch (PersonagemNaoExisteException e) {
            System.out.println(e.getMessage());
            solicitarCampanha();
        }
        try{
            boolean b = fachada.buscarCampanha(cId).getSolicitacoes().contains(personagem_aux);
            if(!b){
                System.out.println("Este personagem nao tem uma solicitacao para esta campanha!" +
                        "Por favor, selecione um personagem que foi exibido para voce.");
                solicitarPersonagem(cId);
            }
        }catch (CampanhaNaoExisteException e) {
            System.out.println(e.getMessage());
            solicitarPersonagem(cId);
        }
        return pId;
    }

    }
