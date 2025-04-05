package servico;

import dados.campanha.IRepositorioCampanhas;
import servico.entidade.Campanha;
import servico.excecao.campanha.CampanhaJaExisteException;
import servico.excecao.campanha.CampanhaNaoExisteException;

public class ServicoCampanha {

    private IRepositorioCampanhas repositorioCampanhas;

    public ServicoCampanha(IRepositorioCampanhas repositorioCampanhas) {
        this.repositorioCampanhas = repositorioCampanhas;
    }

    public void adicionar(Campanha campanha) throws CampanhaJaExisteException{
        boolean existe = repositorioCampanhas.existe(campanha.getID());
        if(existe){
            throw new CampanhaJaExisteException();
        }else{
            repositorioCampanhas.adicionar(campanha);
        }

    }

    public void remover(Campanha campanha) throws CampanhaNaoExisteException{
        boolean existe = repositorioCampanhas.existe(campanha.getID());
        if(!existe){
            throw new CampanhaNaoExisteException();
        }else{
            repositorioCampanhas.remover(campanha);
        }
    }

    public Campanha buscar(String c_Id) throws CampanhaNaoExisteException{
        Campanha campanha = repositorioCampanhas.buscar(c_Id);
        if(campanha == null){
            throw new CampanhaNaoExisteException();
        }else {
            return campanha;
        }
    }

    public void atualizar(Campanha campanha1, Campanha campanha2) throws CampanhaNaoExisteException{
        Campanha campanha = repositorioCampanhas.buscar(campanha1.getID());
        if(campanha == null){
            throw new CampanhaNaoExisteException();
        }else{
            repositorioCampanhas.atualizar(campanha1, campanha2);
        }

    }


}
