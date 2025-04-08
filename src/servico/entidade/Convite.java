package servico.entidade;

public class Convite {
    private Campanha campanha;
    private Personagem personagem;

    public Convite(Campanha campanha, Personagem personagem) {
        this.campanha = campanha;
        this.personagem = personagem;
    }

    // Getters e Setters
    public Campanha getCampanha() { return campanha; }
    public Personagem getPersonagem() { return personagem; }
    public void setCampanha(Campanha campanha) { this.campanha = campanha; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }

}
