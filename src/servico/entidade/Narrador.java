package servico.entidade;
import java.util.ArrayList;

public class Narrador extends Jogador{
    private ArrayList<Campanha> listaCampanhas;
    private Campanha campanhaAtual;

    public Narrador(String nome, int idade, String senha) {
        super(nome, idade, senha);
        this.ID = "N" + this.ID.substring(1); //troca J por N
        this.listaCampanhas = new ArrayList<Campanha>();
        this.campanhaAtual = null;
    }

    public ArrayList<Campanha> getListaCampanhas() {
        return listaCampanhas;
    }

    public void setListaCampanhas(ArrayList<Campanha> listaCampanhas) {
        this.listaCampanhas = listaCampanhas;
    }

    public Campanha getCampanhaAtual() {
        return campanhaAtual;
    }

    public void setCampanhaAtual(Campanha campanhaAtual) {
        this.campanhaAtual = campanhaAtual;
    }
}
