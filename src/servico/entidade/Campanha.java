package servico.entidade;
import java.io.Serializable;
import java.util.ArrayList;
import servico.excecao.campanha.CampanhaLotadaException;
import servico.excecao.campanha.JogadorJaEstaNaCampanhaException;
import servico.excecao.jogador.JogadorNaoExisteException;
import servico.excecao.personagem.PersonagemNaoPertenceAoJogadorException;

import java.time.LocalDate;

public class Campanha implements Serializable {

    private static final long serialVersionUID = 1L; //Versão Inicial

    private String nome;
    private final String ID;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status; //Em andamento, em sessão, finalizada, etc.
    private Narrador narrador;
    private ArrayList<Jogador> jogadores;
    private ArrayList<Personagem> personagens;
    private ArrayList<Solicitacao> solicitacoes;
    private ArrayList<Convite> convites;
    private int limite_jogadores;
    private static int contadorID = 1;

    public Campanha(Narrador narrador, String nome, String descricao, String status,int limite_jogadores){
        this.narrador = narrador;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = LocalDate.now();
        this.dataFim = null;
        this.status = status;
        this.jogadores = new ArrayList<Jogador>();
        this.personagens = new ArrayList<Personagem>();
        this.solicitacoes = new ArrayList<Solicitacao>();
        this.convites = new ArrayList<Convite>();
        this.limite_jogadores = limite_jogadores;
        this.ID = "C" + System.currentTimeMillis() + "-" + contadorID++;
        }


    public void adicionarSolicitacao(Solicitacao solicitacao) throws JogadorJaEstaNaCampanhaException {
        solicitacoes.add(solicitacao);
    }

    public void enviarConvite(Jogador jogador, Personagem personagem, Campanha campanha)
            throws PersonagemNaoPertenceAoJogadorException, JogadorNaoExisteException {
        Convite convite = new Convite(jogador.getID(), campanha, jogador, personagem);
        jogador.receberConvite(convite);
    }

    public void adicionarJogador(Jogador jogador) throws CampanhaLotadaException {
        if (jogadores.size() >= limite_jogadores) {
            throw new CampanhaLotadaException(limite_jogadores);
        }
        this.jogadores.add(jogador);
    }

    public void adicionarPersonagem(Personagem personagem){
        this.personagens.add(personagem);
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
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

    public ArrayList<Solicitacao> getSolicitacoes() { return solicitacoes; }

    public int getVagasRestantes() {
        return limite_jogadores - jogadores.size();
    }

    public int getlimiteJogadores(){
        return limite_jogadores;
    }

    public boolean temVagas() {
        return jogadores.size() < limite_jogadores;
    }

    public ArrayList<Convite> getConvites() {
        return convites;
    }

    public void adicionarConvite(Convite convite) {
        this.convites.add(convite);
    }

    public void removerConvite(Convite convite) {
        this.convites.remove(convite);
    }

}
