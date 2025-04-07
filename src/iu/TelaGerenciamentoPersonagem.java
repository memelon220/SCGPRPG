package iu;

import fachada.SCGPRPG;
import servico.entidade.*;
import servico.excecao.personagem.PersonagemNaoExisteException;

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
        if(arrayPersonagem == null || arrayPersonagem.isEmpty()){
            return false;
        }
        return true;
    }

    public void listarPersonagens(){
        if(existemPersonagens(usuario.getPersonagens())){
            for(Personagem p : usuario.getPersonagens()){
                System.out.println("ID: " + p.getID());
                System.out.println("Nome:" + p.getNome());
                //System.out.println(p.getEspecie().getDescricao());
                System.out.println("XP: " + p.getXP() + ", Nível: " + p.getNivel() + ", Vida: " + p.getVidaAtual() + "/" + p.getVidaMax() + ", Mana: " + p.getManaAtual() + "/" + p.getManaMax());
                System.out.println("Força: " + p.getForca() + ", Destreza: " + p.getDestreza() + ", Constituição: " + p.getConstituicao() + ", Inteligência: " + p.getInteligencia() + ", Sabedoria: " + p.getSabedoria() + ", Carisma: " + p.getCarisma());
                System.out.println("---------------------");
            }
        } else {
            System.out.println("Crie um personagem antes...");
        }
    }

    public void atualizarPersonagem() {
        if(existemPersonagens(usuario.getPersonagens())){
            System.out.println("Qual personagem deseja atualizar? Digite o ID do personagem");
            String id = sc.nextLine();
            Personagem escolhido = null;
            try {
                int index = IntStream.range(0, usuario.getPersonagens().size())
                        .filter(i -> id.equals(usuario.getPersonagens().get(i).getID()))
                        .findFirst()
                        .orElse(-1);
                if (index == -1) {
                    throw new PersonagemNaoExisteException();
                }
                escolhido = usuario.getPersonagens().get(index);
            } catch (PersonagemNaoExisteException e) {
                System.out.println(e.getMessage());
                return;
            }
            System.out.println(escolhido.getNome());
            System.out.println(">>>> O que deseja atualizar? <<<<");
            System.out.println("1 - Atualizar nome");
            System.out.println("2 - Atualizar classe");
            System.out.println("3 - Atualizar espécie");
            System.out.println("0 - Voltar");
            int op = sc.nextInt();
            sc.nextLine();
            switch(op){
                case 1:
                    System.out.println("Insira o novo nome:");
                    String nNome = sc.nextLine();
                    escolhido.setNome(nNome);
                    break;
                case 2:
                    System.out.println("Qual a nova classe:");
                    System.out.println("1 - Mago");
                    System.out.println("2 - Ladino");
                    System.out.println("3 - Guerreiro");
                    System.out.println("4 - Clérigo");
                    System.out.println("0 - Voltar");
                    op = sc.nextInt();
                    sc.nextLine();
                    switch(op) {
                        case 1:
                            escolhido.setClasse(new Mago(escolhido));
                            escolhido.getClasse().aplicarClasse();
                            break;
                        case 2:
                            escolhido.setClasse(new Ladino(escolhido));
                            escolhido.getClasse().aplicarClasse();
                            break;
                        case 3:
                            escolhido.setClasse(new Guerreiro(escolhido));
                            escolhido.getClasse().aplicarClasse();
                            break;
                        case 4:
                            escolhido.setClasse(new Clerigo(escolhido));
                            escolhido.getClasse().aplicarClasse();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                case 3:
                    System.out.println("Qual a nova espécie:");
                    System.out.println("1 - Humano");
                    System.out.println("2 - Halfling");
                    System.out.println("3 - Elfo");
                    System.out.println("4 - Anão");
                    System.out.println("5 - Draconato");
                    System.out.println("0 - Voltar");
                    op = sc.nextInt();
                    sc.nextLine();
                    switch(op) {
                        case 1:
                            Humano h1 = new Humano();
                            h1.aplicarEspecie(escolhido);
                            break;
                        case 2:
                            Halfling h2 = new Halfling();
                            h2.aplicarEspecie(escolhido);
                            break;
                        case 3:
                            Elfo e = new Elfo();
                            e.aplicarEspecie(escolhido);
                            break;
                        case 4:
                            Draconato d = new Draconato();
                            d.aplicarEspecie(escolhido);
                        case 0:
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } else {
            System.out.println("Crie um personagem antes...");
        }
    }

}