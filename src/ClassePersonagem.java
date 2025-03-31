public abstract class ClassePersonagem {
    private String nomeClasse;

    public ClassePersonagem(String nomeClasse){
        this.nomeClasse = nomeClasse;
    }

    public void aplicarClasse(Personagem personagem){
    }

    //getters e setters
    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public void Atacar(){
    }


}
