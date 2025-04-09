package dados.jogador;

import servico.entidade.Campanha;
import servico.entidade.Jogador;

import java.util.ArrayList;

@SuppressWarnings("unused")
public interface IRepositorioJogadores {

    void adicionar(Jogador jogador);
    void remover(Jogador jogador);
    void atualizar(Jogador jogador);
    Jogador buscar(String j_Id);
    void listar();
    boolean existe(String j_Id);
    ArrayList<Jogador> getArrayJogadores();
}