package fachada;

import servico.entidade.Campanha;
import servico.entidade.Narrador;
import servico.excecao.campanha.CampanhaJaExisteException;
import servico.excecao.campanha.CampanhaNaoExisteException;

public class FachadaNarrador extends FachadaJogador {

    private SCGPRPG fachadaPrincipal;

    public FachadaNarrador(SCGPRPG fachadaPrincipal) {
        super(fachadaPrincipal);
    }

    public void criarCampanha(Narrador narrador, String nome, String descricao,
                              String dataInicio, String status) throws CampanhaJaExisteException {
        fachadaPrincipal.criarCampanha(narrador, nome, descricao, dataInicio, status);
    }

    public void removerCampanha(String c_Id) throws CampanhaNaoExisteException {
        fachadaPrincipal.removerCampanha(c_Id);
    }

    public void atualizarCampanha(Campanha campanha1, Campanha campanha2) throws CampanhaNaoExisteException{
        fachadaPrincipal.atualizarCampanha(campanha1, campanha2);
    }


}