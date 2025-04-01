package dados.personagem;

import servico.entidade.Jogador;
import servico.entidade.Personagem;
import java.util.ArrayList;

public class RepositorioPersonagensArrayList implements IRepositorioPersonagens {

    private ArrayList<Personagem> arrayPersonagens;

    public RepositorioPersonagensArrayList(){
        arrayPersonagens = new ArrayList<Personagem>();
    }

    @Override
    public void adicionar(Personagem personagem) {
        arrayPersonagens.add(personagem);
    }

    @Override
    public void remover(Personagem personagem) {
        int i = arrayPersonagens.indexOf(personagem);
        if(i != -1){
            arrayPersonagens.remove(i);
        }
    }

    @Override
    public void atualizar(Personagem personagem1, Personagem personagem2) {
        int i = arrayPersonagens.indexOf(personagem1);
        if(i != -1){
            arrayPersonagens.set(i, personagem2);
        }
    }

    @Override
    public Personagem buscar(Jogador j, String nome) {
        Personagem personagemProcurado = null;
        for (Personagem p : arrayPersonagens) {
            if (j.getID().equals(p.getJogador().getID())) {
                personagemProcurado = p;
                break;
            }
        }
        return personagemProcurado;
    }

    @Override
    public void listar(){
        for(Personagem p : arrayPersonagens){
            System.out.println(p);
        }
    }

    @Override
    public boolean existe(Jogador j, String nome) {
        boolean flag = false;
        for(Personagem p : arrayPersonagens){
            if(j.getID().equals(p.getJogador().getID())){
                flag = true;
            }
        }
        return flag;
    }


}