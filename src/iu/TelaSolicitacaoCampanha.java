package iu;

import fachada.SCGPRPG;
import servico.entidade.Campanha;
import servico.entidade.Jogador;

import java.util.ArrayList;
import java.util.Scanner;

import servico.entidade.Personagem;
import servico.excecao.campanha.CampanhaLotadaException;
import servico.excecao.campanha.JogadorJaEstaNaCampanhaException;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.jogador.SolicitacaoJaExisteException;
import servico.excecao.personagem.PersonagemNaoPertenceAoJogadorException;

public class TelaSolicitacaoCampanha {

    private final SCGPRPG fachada;
    private final Jogador jogador;
    private final Scanner sc;

    public TelaSolicitacaoCampanha(SCGPRPG fachada, Jogador jogador) {
        this.fachada = fachada;
        this.jogador = jogador;
        this.sc = new Scanner(System.in);
    }

    public void solicitarEntrada(){
        if(fachada.listarCampanhasAbertas().isEmpty()) {
            System.out.println("Nenhuma campanha aberta no momento. Tente novamente mais tarde!");
            return;
        }
        try {
            if (fachada.getPersonagensDoJogador(jogador.getID()).isEmpty()) {
                System.out.println("Você não possui personagens cadastrados.");
                return;
            }
        } catch (JogadorNaoExisteException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("\n--- CAMPANHAS DISPONÍVEIS ---");
            fachada.listarCampanhasAbertas().forEach(c ->
                    System.out.printf(
                            "%s (Vagas: %d/%d) | Narrador: %s\n | ID: %s\n",
                            c.getNome(),
                            c.getJogadores().size(),
                            c.getlimiteJogadores(),
                            c.getNarrador().getNome(),
                            c.getID()
                    )
            );

        System.out.print("\nDigite o ID da Campanha que voce gostaria de participar (Digite 0 para cancelar a operacao): ");
        String cId = sc.nextLine();
        if(cId.equals("0")) {
            return;
        }

            System.out.println("\n--- SEUS PERSONAGENS ---");
        try {
            fachada.getPersonagensDoJogador(jogador.getID()).forEach(p ->
                    System.out.printf("%s (Nível %d) | ID: %s\n",
                            p.getNome(),
                            p.getNivel(),
                            p.getID())
            );
        } catch (JogadorNaoExisteException e){
            System.out.println(e.getMessage());
            System.out.println("O processo ira reiniciar...");
            solicitarEntrada();
        }

        System.out.print("\nDigite o ID do Personagem que voce gostaria de utilizar (digite 0 para cancelar a operacao): ");
            String pId = sc.nextLine();
            if(pId.equals("0")){
                return;
            }

        try{
            fachada.solicitarEntradaEmCampanha(jogador.getID(), pId, cId);
            System.out.println("Solicitação enviada com sucesso!");

        } catch (CampanhaLotadaException e) {
            System.out.printf("Campanha lotada! (Limite: %d jogadores)\n Por favor, escolha outra campanha.", e.getLimite());
            System.out.println("O processo ira reiniciar...");
            solicitarEntrada();
        } catch (PersonagemNaoPertenceAoJogadorException e) {
            System.out.println("Este personagem não pertence a você! Por favor, selecione o ID de um de seus personagens!");
            System.out.println("O processo ira reiniciar...");
            solicitarEntrada();
        } catch (SolicitacaoJaExisteException e) {
            System.out.println("Você já tem uma solicitação pendente para esta campanha!");
            System.out.println("O processo ira reiniciar...");
            solicitarEntrada();
        } catch (JogadorJaEstaNaCampanhaException e){
            System.out.println(e.getMessage());
            System.out.println("Por favor, selecione uma campanha que voce nao participa" +
                    "OU peca ao narrador para trocar seu personagem");
            System.out.println("O processo ira reiniciar...");
            solicitarEntrada();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            System.out.println("O processo ira reiniciar...");
            solicitarEntrada();

        }
    }
}