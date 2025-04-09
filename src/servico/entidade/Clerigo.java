package servico.entidade;

public class Clerigo extends ClassePersonagem {
    private Personagem dono;
    private int bonus;

    public Clerigo(Personagem dono) {
        super("Clérigo");
        this.dono = dono;
        bonus = dono.calcularModificador(dono.getCarisma());
    }

    @Override
    public void aplicarClasse() {
        dono.setClasse(new Clerigo(dono));

        dono.setManaMax(dono.getManaMax() + 10);
        dono.setManaAtual(dono.getManaAtual() + 10);
        dono.setVidaMax(dono.getVidaMax() + 10);
        dono.setVidaAtual(Math.min(dono.getVidaAtual() + 10, dono.getVidaMax()));

        dono.setSabedoria(dono.getSabedoria() + 2);
        dono.setCarisma(dono.getCarisma() + 2);
    }

    @Override
    public void retirarClasse() {
        dono.setManaMax(dono.getManaMax() - 10);
        dono.setManaAtual(dono.getManaAtual() - 10);
        dono.setVidaMax(dono.getVidaMax() - 10);
        dono.setVidaAtual(Math.min(dono.getVidaAtual() - 10, dono.getVidaMax()));

        dono.setSabedoria(dono.getSabedoria() + 2);
        dono.setCarisma(dono.getCarisma() - 2);

        dono.setClasse(null);
        this.dono = null;
    }

    @Override
    public void Atacar(Personagem alvo) {
        int dano = d6.rolarDado(1) + bonus;
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        int cura;
        if (dono.getManaAtual() >= 5) {
            cura = d8.rolarDado(2) + bonus;
            dono.setManaAtual(dono.getManaAtual() - 5);
        } else {
            cura = 1;
        }
        alvo.setVidaAtual(Math.min(alvo.getVidaAtual() + cura, alvo.getVidaMax()));
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}