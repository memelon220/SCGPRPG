package dados.personagem;

import servico.entidade.Personagem;

@SuppressWarnings("unused")
public interface IRepositorioPersonagens {

    void adicionar(Personagem personagem);
    void remover(Personagem personagem);
    void atualizar(Personagem personagem1, Personagem personagem2);
    Personagem buscar(String p_Id);
    void listar();
    boolean existe(String p_Id);

}