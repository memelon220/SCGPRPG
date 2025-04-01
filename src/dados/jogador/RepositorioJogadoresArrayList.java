package dados.jogador;

import servico.entidade.Jogador;
import java.util.ArrayList;

public class RepositorioJogadoresArrayList implements IRepositorioJogadores {

    private ArrayList<Jogador> jogadores;

    public RepositorioJogadoresArrayList(){
        jogadores = new ArrayList<Jogador>();
    }

    @Override
    public void adicionar(Jogador jogador) {
        jogadores.add(jogador);
    }



}