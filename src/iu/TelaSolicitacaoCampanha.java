package iu;

import fachada.SCGPRPG;
import servico.entidade.Jogador;
import java.util.Scanner;
import servico.excecao.campanha.CampanhaLotadaException;
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
            System.out.println("\n--- CAMPANHAS DISPONÍVEIS ---");
            fachada.listarCampanhasAbertas().forEach(c ->
                    System.out.printf(
                            "%s (Vagas: %d/%d) | Narrador: %s\n | ID: %s",
                            c.getNome(),
                            c.getJogadores().size(),
                            c.getlimiteJogadores(),
                            c.getNarrador().getNome(),
                            c.getID()
                    )
            );

        System.out.print("\nDigite o ID da Campanha que voce gostaria de participar: ");
        String cId = sc.nextLine();

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

        System.out.print("\nDigite o ID do Personagem que voce gostaria de utilizar: ");
            String pId = sc.nextLine();


        try{
            fachada.solicitarEntradaEmCampanha(jogador.getID(), pId, cId);
            System.out.println("Solicitação enviada com sucesso!");

        } catch (CampanhaLotadaException e) {
            System.out.printf("Campanha lotada! (Limite: %d jogadores)\n", e.getLimite());
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
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            System.out.println("O processo ira reiniciar...");
            solicitarEntrada();

        }
    }
}
/*
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

 */


