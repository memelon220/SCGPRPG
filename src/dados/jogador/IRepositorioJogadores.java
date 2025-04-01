package dados.jogador;

import servico.entidade.Jogador;

public interface IRepositorioJogadores {

    void adicionar(Jogador jogador);
    void remover(Jogador jogador);
    void atualizar(Jogador jogador1, Jogador jogador2);
    Jogador buscar(String j_Id);
    void listar();
    boolean existe(String j_Id);


}