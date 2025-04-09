package iu;

import fachada.SCGPRPG;
import servico.entidade.Campanha;
import servico.entidade.Jogador;
import servico.entidade.Narrador;
import servico.entidade.Personagem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import servico.excecao.campanha.CampanhaLotadaException;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.jogador.ConviteJaExisteException;
import servico.excecao.personagem.PersonagemNaoPertenceAoJogadorException;


public class TelaConvitesCampanha {

    private SCGPRPG fachada;
    private Narrador narrador;
    private Scanner sc;

    public TelaConvitesCampanha(SCGPRPG fachada, Narrador narrador) {
        this.fachada = fachada;
        this.narrador = narrador;
        this.sc = new Scanner(System.in);
    }

    public void convidarJogador() {
        System.out.println(">>>> Suas campanhas: <<<<");
        ArrayList<Campanha> campanhasNarrador = new ArrayList<>(narrador.getListaCampanhas()); // Converte para ArrayList
        ArrayList<Campanha> campanhasDisponiveis = campanhasNarrador.stream()
                .filter(c -> c.getStatus().equals("ABERTA") && c.getJogadores().size() < c.getlimiteJogadores())
                .collect(Collectors.toCollection(ArrayList::new));
        if (campanhasDisponiveis.isEmpty()) {
            System.out.println("Você não possui campanhas abertas com vagas disponíveis.");
            return;
        }
        campanhasDisponiveis.forEach(c ->
                System.out.printf(
                        "%s (Vagas: %d/%d) | ID: %s\n",
                        c.getNome(),
                        c.getJogadores().size(),
                        c.getlimiteJogadores(),
                        c.getID()
                )
        );

        System.out.print("Digite o ID da Campanha para convidar jogadores (Digite 0 para cancelar): ");
        String cId = sc.nextLine();
        if (cId.equals("0")) {
            return;
        }
        System.out.println(">>>> Jogadores disponíveis ---");
        ArrayList<Jogador> jogadores = fachada.listarJogadores();
        if (jogadores.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado no sistema.");
            return;
        }
        for(Jogador j : jogadores) {
            if(!j.getID().equals(narrador.getID())) {
                System.out.printf("%s | ID: %s\n", j.getNome(), j.getID());
            }
        }
        System.out.print("Digite o ID do Jogador que você deseja convidar (digite 0 para cancelar): ");
        Jogador achado = null;
        String jId = sc.nextLine();
        for(Jogador j : jogadores) {
            if (j.getID().equals(jId)) {
                achado = j;
                break;
            }
        }
        if (jId.equals("0") || achado == null) {
            return;
        }
        try {
            System.out.println(">>>> Personagens Do Jogador <<<<");
            ArrayList<Personagem> personagensJogador = fachada.getPersonagensDoJogador(jId);
            if (personagensJogador.isEmpty()) {
                System.out.println("Este jogador não possui personagens cadastrados.");
                return;
            }
            for (Personagem p : personagensJogador) {
                boolean personagemEmCampanha = false; // Flag para verificar se o personagem está em alguma campanha
                for (Campanha c : fachada.listarCampanhasAbertas()) {
                    if (c.getPersonagens().contains(p)) {
                        personagemEmCampanha = true;
                        break;
                    }
                }
                if (!personagemEmCampanha) {
                    System.out.printf("%s (Nível %d) | ID: %s\n", p.getNome(), p.getNivel(), p.getID());
                }
            }
            System.out.print("Digite o ID do Personagem que você deseja convidar (digite 0 para cancelar): ");
            String pId = sc.nextLine();
            if (pId.equals("0")) {
                return;
            }
            fachada.enviarConviteParaJogador(narrador.getID(), cId, jId, pId);
            System.out.println("Convite enviado com sucesso!");
            achado.adicionarNotificacao("Novo convite...");
        } catch (CampanhaLotadaException e) {
            System.out.printf("Campanha lotada! (Limite: %d jogadores)\n", e.getLimite());
        } catch (JogadorNaoExisteException e) {
            System.out.println("Jogador não encontrado!");
        } catch (PersonagemNaoPertenceAoJogadorException e) {
            System.out.println("Este personagem não pertence ao jogador selecionado!");
        } catch (ConviteJaExisteException e) {
            System.out.println("Este jogador já possui um convite pendente para esta campanha!");
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }

}