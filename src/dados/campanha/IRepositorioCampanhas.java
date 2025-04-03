package dados.campanha;

import servico.entidade.Campanha;

@SuppressWarnings("unused")
public interface IRepositorioCampanhas {

    void adicionar(Campanha campanha);
    void remover(Campanha campanha);
    void atualizar(Campanha campanha1, Campanha campanha2);
    Campanha buscar(String c_Id);
    void listar();
    boolean existe(String c_Id);

}
