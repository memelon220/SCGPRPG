package servico.entidade;

public class Guerreiro extends ClassePersonagem {
    private Personagem dono;
    private int bonus;

    public Guerreiro(Personagem dono) {
        super("Guerreiro");
        this.dono = dono;
        bonus = dono.calcularModificador(dono.getForca());
    }

    @Override
    public void aplicarClasse() {
        dono.setClasse(new Guerreiro(dono));
        dono.setForca(dono.getForca() + 2);
        dono.setConstituicao(dono.getConstituicao() + 2);

        dono.setVidaAtual(dono.getVidaAtual() + 11);
        dono.setVidaMax(dono.getVidaMax() + 11);
    }

    @Override
    public void retirarClasse() {
        dono.setForca(dono.getForca() - 2);
        dono.setConstituicao(dono.getConstituicao() - 2);

        dono.setVidaAtual(dono.getVidaAtual() - 11);
        dono.setVidaMax(dono.getVidaMax() - 11);
        dono.setClasse(null);
        this.dono = null;
    }

    @Override
    public void Atacar(Personagem alvo) {
        int dano = d8.rolarDado(1) + bonus;
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

    public void habilidadeEspecial(Personagem alvo) {
        int dano = d12.rolarDado(1) + bonus;
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
        dono.setVidaAtual(Math.min(
                dono.getVidaAtual() + (dano / 2),
                dono.getVidaMax()
        ));
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
