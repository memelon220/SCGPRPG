package servico.entidade;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.stream.Collectors;

public class Jogador implements Serializable{

    private static final long serialVersionUID = 1L; //Versão Inicial
    private static int contadorID = 1;

    protected ArrayList<Personagem> personagens;
    protected ArrayList<Convite> convites;
    protected String nome;
    protected int idade;
    protected String ID;
    protected String senha;
    protected ArrayList<String> notificacoes;
    private ArrayList<Convite> convitesRecebidos;

    public Jogador(String nome, int idade, String senha){
        this.personagens = new ArrayList<Personagem>() ;
        this.nome = nome;
        this.idade = idade;
        Random random = new Random();
        int numero = random.nextInt(1000000);
        this.ID = String.format("J%06d", numero) + "-" + contadorID++;
        this.senha = senha;
        this.notificacoes = new ArrayList<String>();
        this.convitesRecebidos = new ArrayList<Convite>();
    }

    public void adicionarPersonagem(Personagem personagem){
        this.personagens.add(personagem);
        personagem.setJogador(this);
    }

    public void adicionarNotificacao(String mensagem) {
        notificacoes.add(LocalDateTime.now() + " | " + mensagem);
    }

    public void aceitarConvite(Convite convite, boolean confirmacao) {
            if (convites.contains(convite) && confirmacao) {
                convite.getCampanha().adicionarPersonagem(convite.getPersonagem());
                convites.remove(convite);
            }
        else if (convites.contains(convite) && !confirmacao) {
            convites.remove(convite);
            }
    }

    public void aceitarConvite(Convite convite) {
        convite.marcarComoAceito();
    }

    public void recusarConvite(Convite convite) {
        convite.marcarComoRecusado();
    }

    public ArrayList<Convite> getConvitesPendentes() {
        return (ArrayList<Convite>) convites.stream()
                .filter(Convite::isPendente)
                .collect(Collectors.toList());
    }

    public void receberConvite(Convite convite) {
        this.convitesRecebidos.add(convite);
    }

    public void removerConvite(Convite convite) {
        this.convitesRecebidos.remove(convite);
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

    public ArrayList<Convite> getConvitesRecebidos() {
        return new ArrayList<Convite>(convitesRecebidos);
    }

    public void setConvites(ArrayList<Convite> convites){
        this.convites = convites;
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    public ArrayList<String> getNotificacoes() {
        return new ArrayList<>(this.notificacoes);
    }

    public ArrayList<Personagem> setPersonagens(ArrayList personagens) {
        return this.personagens = personagens;
    }
}
