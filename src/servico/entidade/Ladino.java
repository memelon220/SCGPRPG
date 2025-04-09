package servico.entidade;

public class Ladino extends ClassePersonagem {
    private Personagem dono;
    private int bonus;

    public Ladino(Personagem dono) {
        super("Ladino");
        this.dono = dono;
        bonus = dono.calcularModificador(dono.getDestreza());
    }

    @Override
    public void aplicarClasse() {
        this.dono = dono;
        dono.setClasse(this);
        dono.setVidaMax(dono.getVidaMax() - 5);
        dono.setVidaAtual(Math.max(1, dono.getVidaAtual() - 5));
        dono.setDestreza(dono.getDestreza() + 4);
    }

    @Override
    public void retirarClasse() {
        dono.setVidaMax(dono.getVidaMax() + 5);
        dono.setVidaAtual(dono.getVidaAtual() + 5);
        dono.setDestreza(dono.getDestreza() - 4);
        dono.setClasse(null);
        this.dono = null;
    }

    @Override
    public void Atacar(Personagem alvo) {
        int dano = d8.rolarDado(1) + bonus;
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        int crit = d20.rolarDado(1);
        int dano = d12.rolarDado(2) + bonus;

        if (crit >= 16) {
            dano *= 3;
            dono.setVidaAtual(Math.min(
                    dono.getVidaAtual() + 5,
                    dono.getVidaMax()
            ));
        }
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}