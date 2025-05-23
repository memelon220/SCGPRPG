package dados.campanha;

import servico.entidade.Campanha;
import java.util.ArrayList;

public class RepositorioCampanhasArrayList implements IRepositorioCampanhas {

    private ArrayList<Campanha> arrayCampanhas;

    public RepositorioCampanhasArrayList(){
        arrayCampanhas = new ArrayList<Campanha>();
    }

    @Override
    public void adicionar(Campanha campanha) {
        arrayCampanhas.add(campanha);
    }

    @Override
    public void remover(Campanha campanha) {
        int i = arrayCampanhas.indexOf(campanha);
        if(i != -1){
            arrayCampanhas.remove(i);
        }
    }

    @Override
    public void atualizar(Campanha campanha) {
        int index = arrayCampanhas.indexOf(buscar(campanha.getID()));
        if (index != -1) {
            arrayCampanhas.set(index, campanha);
        }
    }

    @Override
    public Campanha buscar(String c_Id) {
        Campanha campanhaProcurada = null;
        for (Campanha c : arrayCampanhas) {
            if (c.getID().equals(c_Id)) {
                campanhaProcurada = c;
                break;
            }
        }
        return campanhaProcurada;
    }

    @Override
    public void listar(){
        for(Campanha c : arrayCampanhas){
            System.out.println(c);
        }
    }

    @Override
    public ArrayList<Campanha> listarTodas() {
        return arrayCampanhas;
    }


    @Override
    public boolean existe(String c_Id) {
        boolean flag = false;
        for(Campanha j : arrayCampanhas){
            if(j.getID().equals(c_Id)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public ArrayList<Campanha> getArrayCampanhas() {
        return arrayCampanhas;
    }

}