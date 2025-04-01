package dados.jogador;

import servico.entidade.Jogador;

public interface IRepositorioJogadores {

    void adicionar(Jogador jogador);
    void remover(Jogador jogador);
    void atualizar(Jogador jogador);
    Jogador buscar(int j_Id);
    void listar();
    boolean existe(int j_Id);


}