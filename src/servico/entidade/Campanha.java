package servico.entidade;

import java.util.ArrayList;

public class Campanha {

    private String nome;
    private String ID;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private String local;
    private String status;
    private ArrayList<Jogador> jogadores;
    private ArrayList<Personagem> personagens;

    public Campanha(String nome, String ID, String descricao, String dataInicio, String local, String status){
        this.nome = nome;
        this.ID = ID;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = null;
        this.local = local;
        this.status = status;
        this.jogadores = new ArrayList<Jogador>();
        this.personagens = new ArrayList<Personagem>();
    }
}
