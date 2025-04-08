package iu;

import fachada.SCGPRPG;
import servico.entidade.*;
import servico.excecao.personagem.PersonagemNaoExisteException;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TelaGerenciamentoPersonagem {

    private Scanner sc;
    private SCGPRPG fachada;
    private Jogador usuario;

    public TelaGerenciamentoPersonagem(SCGPRPG fachada, Jogador usuario){
        this.fachada = fachada;
        this.usuario = usuario;
        this.sc = new Scanner(System.in);
    }

    public boolean existemPersonagens(ArrayList<Personagem> arrayPersonagem){
        return arrayPersonagem != null && !arrayPersonagem.isEmpty();
    }

    public void listarPersonagens(){
        if(existemPersonagens(usuario.getPersonagens())){
            for(Personagem p : usuario.getPersonagens()){
                System.out.println("ID: " + p.getID());
                System.out.println("Nome:" + p.getNome());
                System.out.println("Especie: " + p.getEspecie().getNome());
                System.out.println("Classe: " + p.getClasse().getNomeClasse());
                System.out.println("XP: " + p.getXP() + ", Nível: " + p.getNivel() + ", Vida: " + p.getVidaAtual() + "/" + p.getVidaMax() + ", Mana: " + p.getManaAtual() + "/" + p.getManaMax());
                System.out.println("Força: " + p.getForca() + ", Destreza: " + p.getDestreza() + ", Constituição: " + p.getConstituicao() + ", Inteligência: " + p.getInteligencia() + ", Sabedoria: " + p.getSabedoria() + ", Carisma: " + p.getCarisma());
                System.out.println("---------------------");
            }
        } else {
            System.out.println("Crie um personagem antes...");
        }
    }

    public void atualizarPersonagem() {
        if (existemPersonagens(usuario.getPersonagens())) {
            System.out.println(">>>> Qual personagem deseja atualizar? Digite o ID do personagem <<<<");
            String id = sc.nextLine();
            Personagem escolhido = null;
            try {
                for (Personagem p : usuario.getPersonagens()) {
                    if (p.getID().equals(id)) {
                        escolhido = p;
                        break;
                    }
                }
                if (escolhido == null) {
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
            System.out.println("Personagem escolhido: "+ escolhido.getNome());
            System.out.println(">>>> O que deseja atualizar? <<<<");
            System.out.println("1 - Atualizar nome");
            System.out.println("2 - Atualizar classe");
            System.out.println("3 - Atualizar espécie");
            System.out.println("0 - Voltar");
            String op = sc.nextLine();
            switch(op){
                case "1":
                    System.out.println("Qual o novo nome...?");
                    String nNome = sc.nextLine();
                    escolhido.setNome(nNome);
                    break;
                case "2":
                    System.out.println("Qual a nova classe...?");
                    System.out.println("1 - Mago");
                    System.out.println("2 - Ladino");
                    System.out.println("3 - Guerreiro");
                    System.out.println("4 - Clérigo");
                    System.out.println("0 - Voltar");
                    op = sc.nextLine();
                    switch(op) {
                        case "1":
                            escolhido.getClasse().retirarClasse();
                            escolhido.setClasse(new Mago(escolhido));
                            escolhido.getClasse().aplicarClasse();
                            break;
                        case "2":
                            escolhido.getClasse().retirarClasse();
                            escolhido.setClasse(new Ladino(escolhido));
                            escolhido.getClasse().aplicarClasse();
                            break;
                        case "3":
                            escolhido.getClasse().retirarClasse();
                            escolhido.setClasse(new Guerreiro(escolhido));
                            escolhido.getClasse().aplicarClasse();
                            break;
                        case "4":
                            escolhido.getClasse().retirarClasse();
                            escolhido.setClasse(new Clerigo(escolhido));
                            escolhido.getClasse().aplicarClasse();
                            break;
                        case "0":
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;
                case "3":
                    System.out.println("Qual a nova espécie...?");
                    System.out.println("1 - Humano");
                    System.out.println("2 - Halfling");
                    System.out.println("3 - Elfo");
                    System.out.println("4 - Anão");
                    System.out.println("5 - Draconato");
                    System.out.println("0 - Voltar");
                    op = sc.nextLine();
                    switch(op) {
                        case "1":
                            Humano h1 = new Humano();
                            h1.aplicarEspecie(escolhido);
                            break;
                        case "2":
                            Halfling h2 = new Halfling();
                            h2.aplicarEspecie(escolhido);
                            break;
                        case "3":
                            Elfo e = new Elfo();
                            e.aplicarEspecie(escolhido);
                            break;
                        case "4":
                            Draconato d = new Draconato();
                            d.aplicarEspecie(escolhido);
                            break;
                        case "0":
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            try {
                fachada.atualizarPersonagem(escolhido, id);
            } catch(PersonagemNaoExisteException e) {
                e.getMessage();
            }
        } else {
            System.out.println("Crie um personagem antes...");
        }
    }

    public void removerPersonagem() {
        ArrayList<Personagem> personagens = usuario.getPersonagens();
        if (personagens == null || personagens.isEmpty()) {
            System.out.println("Nenhum personagem cadastrado para remover.");
            return;
        }
        listarPersonagens();
        System.out.println(">>>> Digite o ID do personagem a ser removido! <<<<");
        String id = sc.nextLine();
        try {
            boolean encontrado = false;
            Personagem personagemParaRemover = null;
            for (Personagem p : personagens) {
                if (p.getID().equals(id)) {
                    personagemParaRemover = p;
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Personagem com ID " + id + " não encontrado.");
                return;
            }
            System.out.println("Tem certeza que deseja remover o personagem " +
                    personagemParaRemover.getNome() + "? (S/N)");
            String confirmacao = sc.nextLine().toUpperCase();
            if (!confirmacao.equals("S")) {
                System.out.println("Operação cancelada.");
                return;
            }
            fachada.removerPersonagem(id);
            personagens.remove(personagemParaRemover);
            System.out.println("Personagem removido com sucesso!");

        } catch (PersonagemNaoExisteException e) {
            System.out.println("Erro ao remover personagem: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

}