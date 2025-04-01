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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
