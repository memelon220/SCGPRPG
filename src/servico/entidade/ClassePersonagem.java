package servico.entidade;

public abstract class ClassePersonagem {
    private String nomeClasse;
    private Personagem dono;

    public ClassePersonagem(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public abstract void Atacar(Personagem alvo);
    public abstract void aplicarClasse(Personagem dono);
    public abstract void habilidadeEspecial(Personagem dono, Personagem alvo);

    //getters e setters
    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }


}