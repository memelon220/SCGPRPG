package servico.entidade;

public abstract class ClassePersonagem {
    private String nomeClasse;

    public ClassePersonagem(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public abstract void Atacar(Personagem alvo);
    public abstract void aplicarClasse(Personagem personagem);

    //getters e setters
    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

}
