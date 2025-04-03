package servico.entidade;
import java.util.Random;
import java.util.ArrayList;

public class Jogador{

    protected ArrayList<Personagem> personagens;
    protected String nome;
    protected int idade;
    protected String ID;

    public Jogador(String nome, int idade){
        this.personagens = new ArrayList<Personagem>() ;
        this.nome = nome;
        this.idade = idade;
        Random random = new Random();
        int numero = random.nextInt(1000000);
        this.ID = String.format("J%06d", numero);
    }

    public void adicionarPersonagem(String nome, boolean randomizar){
        Personagem personagem = new Personagem(nome, randomizar);
        this.personagens.add(personagem);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public ArrayList getPersonagens() {
        return personagens;
    }

    public ArrayList setPersonagens(ArrayList personagens) {
        return this.personagens = personagens;
    }
}
