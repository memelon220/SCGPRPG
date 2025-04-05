package servico.entidade;

public class Ladino extends ClassePersonagem {
    private Personagem dono;
    public Ladino(Personagem dono) {
        super("Ladino");
        this.dono = dono;
    }

    @Override
    public void aplicarClasse() {
        this.dono = dono;
        dono.setClasse(this);

        // Ajuste de atributos
        dono.setVidaMax(dono.getVidaMax() - 5);
        dono.setVidaAtual(Math.max(1, dono.getVidaAtual() - 5));
        dono.setDestreza(dono.getDestreza() + 4);
    }

    @Override
    public void Atacar(Personagem alvo) {
        int acerto = d20.rolarDado(1) + dono.calcularModificador(dono.getDestreza());

        if(acerto >= alvo.getClasseResistencia()) {
            int dano = d8.rolarDado(1) + dono.calcularModificador(dono.getDestreza());
            alvo.setVidaAtual(alvo.getVidaAtual() - dano);
        }
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        int crit = d20.rolarDado(1);
        int dano = d12.rolarDado(2) + dono.calcularModificador(dono.getDestreza());
        int acerto = d20.rolarDado(1);


        if (acerto >= alvo.getClasseResistencia()) {
            if(crit >= 16) {
                dano *= 3;
                dono.setVidaAtual(Math.min(
                        dono.getVidaAtual() + 5,
                        dono.getVidaMax()
                ));
            }

            alvo.setVidaAtual(alvo.getVidaAtual() - dano);
        }
    }
}