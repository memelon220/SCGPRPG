package servico.entidade;

public class Solicitacao {
    private Jogador jogador;
    private Personagem personagem;
    private Campanha campanha;

    public Solicitacao(Jogador jogador, Personagem personagem, Campanha campanha) {
        this.jogador = jogador;
        this.personagem = personagem;
        this.campanha = campanha;
    }

    // Getters e Setters
    public Jogador getJogador() { return jogador; }
    public Personagem getPersonagem() { return personagem; }
    public Campanha getCampanha() { return campanha; }
    public void setJogador(Jogador jogador) { this.jogador = jogador; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }
    public void setCampanha(Campanha campanha) { this.campanha = campanha; }

}