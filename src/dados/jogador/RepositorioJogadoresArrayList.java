package dados.jogador;

import servico.entidade.Jogador;
import java.util.ArrayList;

public class RepositorioJogadoresArrayList implements IRepositorioJogadores {

    private ArrayList<Jogador> arrayJogadores;

    public RepositorioJogadoresArrayList(){
        arrayJogadores = new ArrayList<Jogador>();
    }

    @Override
    public void adicionar(Jogador jogador) {
        arrayJogadores.add(jogador);
    }

    @Override
    public void remover(Jogador jogador) {
        int i = arrayJogadores.indexOf(jogador);
        if(i != -1){
            arrayJogadores.remove(i);
        }
    }

    @Override
    public void atualizar(Jogador jogador1, Jogador jogador2) {
        int i = arrayJogadores.indexOf(jogador1);
        if(i != -1){
            arrayJogadores.set(i, jogador2);
        }
    }

    @Override
    public Jogador buscar(String j_Id) {
        Jogador jogadorProcurado = null;
        for (Jogador j : arrayJogadores) {
            if (j.getID().equals(j_Id)) {
                jogadorProcurado = j;
                break;
            }
        }
        return jogadorProcurado;
    }

    @Override
    public void listar(){
        for(Jogador j : arrayJogadores){
            System.out.println(j);
        }
    }

    @Override
    public boolean existe(String j_Id) {
        boolean flag = false;
        for(Jogador j : arrayJogadores){
            if(j.getID().equals(j_Id)){
                flag = true;
                break;
            }
        }
        return flag;
    }
}