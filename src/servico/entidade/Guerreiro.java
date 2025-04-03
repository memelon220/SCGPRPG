package servico.entidade;

public class Guerreiro extends ClassePersonagem {
    private Personagem dono;

    public Guerreiro(Personagem dono) {
        super("Guerreiro");
        this.dono = dono;
    }

    @Override
    public void aplicarClasse(Personagem dono) {
        dono.setClasse(new Guerreiro(dono));
        dono.setVidaMax(dono.getVidaMax() + 10);
        dono.setForca(dono.getForca() + 2);
        dono.setConstituicao(dono.getConstituicao() + 2);
    }

    @Override
    public void Atacar(Personagem alvo) {
        Dado dado = new Dado(8);
        int dano = dado.rolarDado(1);// Rola um dado de 8 lados (1 a 8)
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

    public void habilidadeEspecial(Personagem dono, Personagem alvo) {
        Dado dado = new Dado(12);
        int dano = dado.rolarDado(1);
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
        dono.setVidaAtual(dono.getVidaAtual() + dano/2);
        if (dono.getVidaAtual() > dono.getVidaMax()){
            dono.setVidaAtual(dono.getVidaMax());
        }
    }
}
