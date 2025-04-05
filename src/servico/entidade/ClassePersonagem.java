package servico.entidade;

public abstract class ClassePersonagem {
    private String nomeClasse;
    private Personagem dono;

    protected final Dado d20 = new Dado(20);
    protected final Dado d12 = new Dado(12);
    protected final Dado d10 = new Dado(10);
    protected final Dado d8 = new Dado(8);
    protected final Dado d6 = new Dado(6);

    public ClassePersonagem(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public abstract void Atacar(Personagem alvo);
    public abstract void aplicarClasse();
    public abstract void habilidadeEspecial(Personagem alvo);

    //getters e setters
    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }


}