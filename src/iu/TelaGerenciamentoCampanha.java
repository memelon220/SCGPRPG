package iu;

import fachada.SCGPRPG;
import servico.entidade.*;
import servico.excecao.campanha.CampanhaNaoExisteException;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.personagem.PersonagemNaoExisteException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TelaGerenciamentoCampanha {

    private Scanner sc;
    private SCGPRPG fachada;
    private Narrador usuario;

    public TelaGerenciamentoCampanha(SCGPRPG fachada, Narrador usuario){
        this.fachada = fachada;
        this.usuario = usuario;
        this.sc = new Scanner(System.in);
    }

    public boolean existemJogadores(ArrayList<Jogador> arrayJogador){
        if(arrayJogador == null || arrayJogador.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean existemPersonagens(ArrayList<Personagem> arrayPersonagem){
        if(arrayPersonagem == null || arrayPersonagem.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean existemCampanhas(ArrayList<Campanha> arrayCampanha){
        if(arrayCampanha == null || arrayCampanha.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean campanhaEmAndamento(Campanha c){
        if(c.getStatus().equals("ABERTO")){
            return true;
        }
        return false;
    }

    public void listarPersonagens(Campanha campanha){
        if(existemPersonagens(campanha.getPersonagens())){
            System.out.println("\nPersonagens");
            for(Personagem p : campanha.getPersonagens()){
                System.out.println("---------------------");
                System.out.println("Nome:" + p.getNome());
                System.out.println("Especie: " + p.getEspecie().getNome());
                System.out.println("Classe: " + p.getClasse().getNomeClasse());
                System.out.println("XP: " + p.getXP() + ", Nível: " + p.getNivel() + ", Vida: " + p.getVidaAtual() + "/" + p.getVidaMax() + ", Mana: " + p.getManaAtual() + "/" + p.getManaMax());
                System.out.println("Força: " + p.getForca() + ", Destreza: " + p.getDestreza() + ", Constituição: " + p.getConstituicao() + ", Inteligência: " + p.getInteligencia() + ", Sabedoria: " + p.getSabedoria() + ", Carisma: " + p.getCarisma());
                System.out.println("---------------------\n");
            }
        } else {
            System.out.println("Nenhum personagem foi adicionado...");
            return;
        }
    }

    public void listarCampanhas(){
        if(existemCampanhas(usuario.getListaCampanhas())){
            System.out.println("\n>>>> Campanhas <<<<");
            for(Campanha c : usuario.getListaCampanhas()){
                System.out.println("ID: " + c.getID());
                System.out.println("Nome: " + c.getNome() + ", Status: " + c.getStatus());
                System.out.println("Descrição: " + c.getDescricao());
                if(c.getDataFim() == null){
                    System.out.println("Data de início: " + c.getDataInicio() + ", Data de fim: Campanha em andamento");
                } else{
                System.out.println("Data de início: " + c.getDataInicio() + ", Data de fim: " + c.getDataFim());
                }
                listarPersonagens(c);
            }
        } else {
            System.out.println("Crie uma campanha antes...");
            return;
        }
    }

    public void atualizarCampanha(){
        if(existemCampanhas(usuario.getListaCampanhas())) {
            System.out.println(">>>> Qual campanha deseja atualizar? Digite o ID da campanha <<<<");
            String id = sc.nextLine();
            Campanha escolhida = null;
            try {
                for (Campanha c : usuario.getListaCampanhas()) {
                    if (c.getID().equals(id)) {
                        escolhida = c;
                        break;
                    }
                }
                if (escolhida == null) {
                    throw new CampanhaNaoExisteException();
                }
            } catch (CampanhaNaoExisteException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Campanha com ID '" + id + "' não encontrado.");
                return;
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                return;
            }
            if (!campanhaEmAndamento(escolhida)) {
                System.out.println("Campanha escolhida: " + escolhida.getNome());
                System.out.println(">>>> O que deseja atualizar? <<<<");
                System.out.println("1 - Nome");
                System.out.println("2 - Descrição");
                System.out.println("3 - Status");
                System.out.println("4 - Remover jogador");
                System.out.println("5 - Remover personagem");
                System.out.println("6 - Encerrar campanha");
                System.out.println("0 - Sair");
                String op = sc.nextLine();
                switch(op) {
                    case "1":
                        System.out.println("Qual o novo nome...?");
                        String nNome = sc.nextLine();
                        escolhida.setNome(nNome);
                        break;
                    case "2":
                        System.out.println("Qual a nova descrição...?");
                        String nDesc = sc.nextLine();
                        escolhida.setDescricao(nDesc);
                        break;
                    case "3":
                        System.out.println("Qual o novo status...?");
                        String nStatus = sc.nextLine();
                        escolhida.setStatus(nStatus);
                        break;
                    case "4":
                        System.out.println("Qual o ID do jogador a ser removido...?");
                        String removej_id = sc.nextLine();
                        Jogador j_removido = null;
                        try {
                            for (Jogador j : escolhida.getJogadores()) {
                                if (j.getID().equals(removej_id)) {
                                    j_removido = j;
                                    break;
                                }
                            }
                            if (j_removido == null) {
                                throw new JogadorNaoExisteException();
                            }
                        } catch (JogadorNaoExisteException e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Jogador com ID '" + id + "' não encontrado.");
                            return;
                        } catch (Exception e) {
                            System.out.println("Erro inesperado: " + e.getMessage());
                            return;
                        }
                        escolhida.getJogadores().remove(j_removido);
                        for (Personagem p : escolhida.getPersonagens()) {
                            if (p.getJogador().equals(j_removido)) {
                                escolhida.getPersonagens().remove(p);
                            }
                        }
                        break;
                    case "5":
                        System.out.println("Qual o ID do personagem a ser removido...?");
                        String removep_id = sc.nextLine();
                        Personagem p_removido = null;
                        try {
                            for (Personagem p : escolhida.getPersonagens()) {
                                if (p.getID().equals(removep_id)) {
                                    p_removido = p;
                                    break;
                                }
                            }
                            if (p_removido == null) {
                                throw new PersonagemNaoExisteException();
                            }
                        } catch (PersonagemNaoExisteException e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Personagem com ID '" + id + "' não encontrado.");
                            return;
                        } catch (Exception e) {
                            System.out.println("Erro inesperado: " + e.getMessage());
                            return;
                        }
                        escolhida.getPersonagens().remove(p_removido);
                        break;
                    case "6":
                        escolhida.setStatus("FECHADO");
                        escolhida.setDataFim(LocalDate.now());
                        System.out.println("Alteracao feita com sucesso.");
                        break;
                    case "0":
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("Campanha finalizada!");
            }
        } else {
            System.out.println("Crie uma campanha antes...");
        }
    }

    public void removerCampanha() {
        ArrayList<Campanha> campanhas = usuario.getListaCampanhas();
        if (campanhas == null || campanhas.isEmpty()) {
            System.out.println("Nenhuma campanha cadastrada para remover.");
            return;
        }
        listarCampanhas();
        System.out.println(">>>> Digite o ID da campanha a ser removido! <<<<");
        String id = sc.nextLine();
        try {
            boolean encontrado = false;
            Campanha campanhaParaRemover = null;
            for (Campanha c : campanhas) {
                if (c.getID().equals(id)) {
                    campanhaParaRemover = c;
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Campanha com ID " + id + " não encontrado.");
                return;
            }
            System.out.println("Tem certeza que deseja remover a campanha " +
                    campanhaParaRemover.getNome() + "? (S/N)");
            String confirmacao = sc.nextLine().toUpperCase();
            if (!confirmacao.equals("S")) {
                System.out.println("Operação cancelada.");
                return;
            }
            fachada.removerCampanha(id);
            usuario.getListaCampanhas().remove(campanhaParaRemover);
            System.out.println("Campnha removida com sucesso!");

        } catch (CampanhaNaoExisteException e) {
            System.out.println("Erro ao remover campanha: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    /*
    public void aprovarSolicitacao(Campanha campanha, Solicitacao solicitacao) {
        if (campanha.getNarrador().equals(this.nome)) {
            campanha.aprovarSolicitacao(solicitacao);
        } else {
            System.out.println("Você não é o narrador desta campanha!");
        }
    }

    public void enviarConvite(Campanha campanha, Jogador jogador, Personagem personagem) {
        if (campanha.getNarrador().equals(this.nome)) {
            campanha.enviarConvite(jogador, personagem);
        } else {
            System.out.println("Você não é o narrador desta campanha!");
        }
    }
*/
    public void solicitacoesEntradaCampanha(Narrador usuario){

    }

    public void convidarJogador(Campanha campanha, Jogador jogador){

    }

}