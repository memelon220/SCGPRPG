package servico.entidade;
import java.util.ArrayList;

public class Narrador extends Jogador{
    private ArrayList<Campanha> listaCampanhas;
    private Campanha campanhaAtual;

    public Narrador(String nome, int idade, String ID){
        super(nome, idade, ID);
        this.listaCampanhas = new ArrayList<Campanha>();
        this.campanhaAtual = null;
    }

}
