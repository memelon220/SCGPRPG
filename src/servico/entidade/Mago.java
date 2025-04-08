package servico.entidade;

public class Mago extends ClassePersonagem {
private Personagem dono;

    public Mago(Personagem dono) {
        super("Mago");
        this.dono = dono;
    }

    @Override
    public void aplicarClasse() {
        this.dono = dono;
        dono.setClasse(new Mago(dono));

        dono.setInteligencia(dono.getInteligencia() + 2);
        dono.setSabedoria(dono.getSabedoria() + 2);

        dono.setManaMax(dono.getManaMax() + 11);
        dono.setManaAtual(dono.getManaAtual() + 11);
    }

    @Override
    public void retirarClasse(){
        dono.setInteligencia(dono.getInteligencia() - 2);
        dono.setSabedoria(dono.getSabedoria() - 2);

        dono.setManaMax(dono.getManaMax() - 11);
        dono.setManaAtual(dono.getManaAtual() - 11);
        dono.setClasse(null);
        this.dono = null;
    }

    @Override
    public void Atacar(Personagem alvo) {
        int acerto = d20.rolarDado(1) + dono.calcularModificador(dono.getForca());
        int dano = d8.rolarDado(1) + dono.calcularModificador(dono.getForca());

        if(acerto >= alvo.getClasseResistencia()) {
            alvo.setVidaAtual(alvo.getVidaAtual() - dano);
        }
    }

    @Override
    public void habilidadeEspecial(Personagem alvo) {
        if(dono.getManaAtual() >= 5) {
            int dano = d10.rolarDado(2);

            alvo.setVidaAtual(alvo.getVidaAtual() - dano);
            dono.setManaAtual(dono.getManaAtual() - 5);
        }
    }
}