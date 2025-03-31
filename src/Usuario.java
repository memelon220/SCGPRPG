import java.util.Random;

abstract class Usuario {
    protected String nome;
    protected int ID;
    Random rand = new Random();
    public Usuario(String nome, int ID){
        this.nome = nome;
        this.ID = rand.nextInt(100000,999999);
    }
}
