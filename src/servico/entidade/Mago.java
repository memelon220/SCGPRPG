package servico.entidade;

public class Mago extends ClassePersonagem {
    private Personagem dono;
    private int bonus;


    public Mago(Personagem dono) {
        super("Mago");
        this.dono = dono;
        bonus = dono.calcularModificador(dono.getSabedoria());
    }

    @Override
    public void aplicarClasse() {
        dono.setClasse(new Mago(dono));

        dono.setInteligencia(dono.getInteligencia() + 2);
        dono.setSabedoria(dono.getSabedoria() + 2);

        dono.setManaMax(dono.getManaMax() + 11);
        dono.setManaAtual(dono.getManaAtual() + 11);
    }

    @Override
    public void retirarClasse() {
        dono.setInteligencia(dono.getInteligencia() - 2);
        dono.setSabedoria(dono.getSabedoria() - 2);

        dono.setManaMax(dono.getManaMax() - 11);
        dono.setManaAtual(dono.getManaAtual() - 11);
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
        int dano;
        if (dono.getManaAtual() >= 5) {
            dano = d10.rolarDado(2) + bonus;
            alvo.setVidaAtual(alvo.getVidaAtual() - dano);
            dono.setManaAtual(dono.getManaAtual() - 5);
        } else {
            dano = 1 + bonus;
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