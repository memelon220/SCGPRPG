package servico.entidade;
import java.util.ArrayList;

public class Jogador{

    protected ArrayList<Personagem> personagens;
    protected String nome;
    protected int idade;
    protected String ID;

    public Jogador(String nome, int idade, String ID){
        this.personagens = new ArrayList<Personagem>() ;
        this.nome = nome;
        this.idade = idade;
        this.ID = ID;
    }

    public void adicionarPersonagem(String nome, boolean randomizar){
        Personagem personagem = new Personagem(nome, randomizar);
        this.personagens.add(personagem);
        System.out.println("dados.Personagem "+ nome +" adicionado com sucesso!");
    }
}
