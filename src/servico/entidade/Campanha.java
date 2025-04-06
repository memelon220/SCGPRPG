package servico.entidade;
import java.io.Serializable;
import java.util.ArrayList;

public class Campanha implements Serializable {

    private static final long serialVersionUID = 1L; //Versão Inicial

    private String nome;
    private final String ID;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private String status; //Em andamento, em sessão, finalizada, etc.
    private Narrador narrador;
    private ArrayList<Jogador> jogadores;
    private ArrayList<Personagem> personagens;

    public Campanha(Narrador narrador, String nome, String descricao, String dataInicio, String status){
        this.narrador = narrador;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = null;
        this.status = status;
        this.jogadores = new ArrayList<Jogador>();
        this.personagens = new ArrayList<Personagem>();
        int numeracao = Integer.parseInt(narrador.getID().substring(1)) + narrador.getListaCampanhas().size();
        //faz o ID de campanha com base no ID de narrador adicionando o número de campanhas que este narrador tem.
        this.ID = String.format("C%06d", numeracao);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Narrador getNarrador() {
        return narrador;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(ArrayList<Personagem> personagens) {
        this.personagens = personagens;
    }

    public void adicionarJogador(Jogador jogador){
        this.jogadores.add(jogador);
    }

    public void adicionarPersonagem(Personagem personagem){
        this.personagens.add(personagem);
    }

}
