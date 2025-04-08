package servico.entidade;
import java.time.LocalDateTime;

public class Convite {

    private String id;
    private Campanha campanha;
    private Jogador jogador;
    private Personagem personagem;
    private boolean aceito = false;
    private boolean recusado = false;
    private LocalDateTime dataEnvio;

    public Convite(String id, Campanha campanha, Jogador jogador, Personagem personagem) {
        this.id = id;
        this.campanha = campanha;
        this.jogador = jogador;
        this.personagem = personagem;
        this.dataEnvio = LocalDateTime.now();
    }

    public boolean isAceito() { return aceito; }
    public boolean isRecusado() { return recusado; }
    public Jogador getJogador() { return jogador; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public void setAceito(boolean aceito) { this.aceito = aceito; }
    public void setRecusado(boolean recusado) { this.recusado = recusado; }
    public Campanha getCampanha() { return campanha; }
    public Personagem getPersonagem() { return personagem; }
    public void setCampanha(Campanha campanha) { this.campanha = campanha; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }
    public void setId(String id) { this.id = id; }
    public String getId() { return id; }

}
