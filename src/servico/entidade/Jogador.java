package servico.entidade;
import java.util.Random;
import java.util.ArrayList;
import java.io.Serializable;

public class Jogador implements Serializable{

    private static final long serialVersionUID = 1L; //Versão Inicial
    private static int contadorID = 1;

    protected ArrayList<Personagem> personagens;
    protected String nome;
    protected int idade;
    protected String ID;
    protected String senha;

    public Jogador(String nome, int idade, String senha){
        this.personagens = new ArrayList<Personagem>() ;
        this.nome = nome;
        this.idade = idade;
        Random random = new Random();
        int numero = random.nextInt(1000000);
        this.ID = String.format("J%06d", numero) + "-" + contadorID++;
        this.senha = senha;
    }

    public void adicionarPersonagem(Personagem personagem){
        this.personagens.add(personagem);
        personagem.setJogador(this);
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    public ArrayList<Personagem> setPersonagens(ArrayList personagens) {
        return this.personagens = personagens;
    }
}
