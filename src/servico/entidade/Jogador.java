package servico.entidade;
import java.util.Random;
import java.util.ArrayList;
import java.io.Serializable;

public class Jogador implements Serializable{

    private static final long serialVersionUID = 1L; //Versão Inicial

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

    public void adicionarPersonagem(String nome){
        Personagem personagem = new Personagem(nome);
        this.personagens.add(personagem);
    }

    public void adicionarPersonagem(String nome, int nivel){
        Personagem personagem = new Personagem(nome, nivel);
        this.personagens.add(personagem);
    }

    public void adicionarPersonagem(String nome, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma){
        Personagem personagem = new Personagem(nome, forca, destreza, constituicao, inteligencia, sabedoria, carisma);
        this.personagens.add(personagem);
    }

    public void adicionarPersonagem(String nome, int nivel, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma ){
        Personagem personagem = new Personagem(nome, nivel, forca, destreza, constituicao, inteligencia, sabedoria, carisma);
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
