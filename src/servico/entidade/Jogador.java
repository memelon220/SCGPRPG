package servico.entidade;

import java.util.List;

public class Jogador extends Usuario{
    protected List<Personagem> personagens;

    public Jogador(String nome, int ID){
        super(nome, ID);
    }

    public void adicionarPersonagem(String nome, boolean randomizar){
        Personagem personagem = new Personagem(nome, randomizar);
        personagens.add(personagem);
        System.out.println("dados.Personagem "+ nome +" adicionado com sucesso!");
    }
}
