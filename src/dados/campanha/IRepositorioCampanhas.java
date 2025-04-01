package dados.campanha;

import servico.entidade.Campanha;

public interface IRepositorioCampanhas {

    void adicionar(Campanha campanha);
    void remover(Campanha campanha);
    void atualizar(Campanha campanha);
    Campanha buscar(int c_Id);
    void listar();
    boolean existe(int c_Id);

}
