package dados.personagem;

import servico.entidade.Personagem;

public interface IRepositorioPersonagens {

    void adicionar(Personagem personagem);
    void remover(Personagem personagem);
    void atualizar(Personagem personagem);
    Personagem buscar(int p_Id);
    void listar();
    boolean existe(int p_Id);

}