package servico.entidade;

public class Clerigo extends ClassePersonagem {
    private Personagem dono;

    public Clerigo(Personagem dono) {
        super("Clérigo");
    }

    @Override
    public void aplicarClasse() {
        dono.setClasse(new Clerigo(dono));

        dono.setMagia(dono.getMagia() + 10);
        dono.setManaAtual(dono.getManaAtual() + 10);
        dono.setVidaMax(dono.getVidaMax() + 10);
        dono.setVidaAtual(Math.min(dono.getVidaAtual() + 10, dono.getVidaMax()));

        dono.setSabedoria(dono.getSabedoria() + 2);
        dono.setCarisma(dono.getCarisma() + 2);
    }

    @Override
    public void Atacar(Personagem alvo) {
        int acerto = d20.rolarDado(1) + dono.calcularModificador(dono.getForca());

        if(acerto >= alvo.getClasseResistencia()) {
            int dano = d6.rolarDado(1) + dono.calcularModificador(dono.getCarisma());
            alvo.setVidaAtual(alvo.getVidaAtual() - dano);
        }
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {

        if(dono.getManaAtual() >= 5) {
            int cura = d8.rolarDado(2) + dono.calcularModificador(dono.getSabedoria());
            alvo.setVidaAtual(Math.min(alvo.getVidaAtual() + cura, alvo.getVidaMax()));
            dono.setManaAtual(dono.getManaAtual() - 5);
        }
    }

}