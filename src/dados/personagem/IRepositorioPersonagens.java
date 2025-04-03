package dados.personagem;

import servico.entidade.Jogador;
import servico.entidade.Personagem;

@SuppressWarnings("unused")
public interface IRepositorioPersonagens {

    void adicionar(Personagem personagem);
    void remover(Personagem personagem);
    void atualizar(Personagem personagem1, Personagem personagem2);
    Personagem buscar(Jogador j, String nome);
    void listar();
    boolean existe(Jogador j, String nome);

}