package servico.entidade;

public class Guerreiro extends ClassePersonagem {
    public Guerreiro() {
        super("Guerreiro");
    }

    @Override
    public void Atacar(Personagem alvo) {
        Dado dado = new Dado(8);
        int dano = dado.rolarDado(1);// Rola um dado de 8 lados (1 a 8)
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

    @Override
    public void aplicarClasse(Personagem personagem) {
        personagem.setClasse(new Guerreiro());
        personagem.setVidaMax(personagem.getVidaMax() + 10);
        personagem.setForca(personagem.getForca() + 2);
        personagem.setConstituicao(personagem.getConstituicao() + 2);
    }

}
