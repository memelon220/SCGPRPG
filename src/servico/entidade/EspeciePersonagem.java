package servico.entidade;

public abstract class EspeciePersonagem {

    private String nome;
    private String descricao;

    public EspeciePersonagem(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public abstract void aplicarEspecie(Personagem personagem);

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }
}
