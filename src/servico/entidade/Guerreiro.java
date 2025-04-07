package servico.entidade;

public class Guerreiro extends ClassePersonagem {

    private Personagem dono;

    public Guerreiro(Personagem dono) {
        super("Guerreiro");
        this.dono = dono;
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
    public void retirarClasse(){
        dono.setForca(dono.getForca() - 2);
        dono.setConstituicao(dono.getConstituicao() - 2);

        dono.setVidaAtual(dono.getVidaAtual() - 11);
        dono.setVidaMax(dono.getVidaMax() - 11);
        dono.setClasse(null);
        this.dono = null;
    }

    @Override
    public void Atacar(Personagem alvo) {

        int acerto = d20.rolarDado(1) + dono.calcularModificador(dono.getForca());

        if (acerto >= alvo.getClasseResistencia()) {
            int dano = d8.rolarDado(1) + dono.calcularModificador(dono.getForca());
            alvo.setVidaAtual(alvo.getVidaAtual() - dano);
        }
    }

    public void habilidadeEspecial(Personagem alvo) {

        int acerto = d20.rolarDado(1) + dono.calcularModificador(dono.getForca());

        if (acerto >= alvo.getClasseResistencia()) {
            int dano = d12.rolarDado(1) + dono.calcularModificador(dono.getForca());

            //Remove da vida do alvo o dano rolado
            alvo.setVidaAtual(alvo.getVidaAtual() - dano);

            //Adiciona metade do dano aplicado no alvo como vida no dono da classe se a soma for maior que a vida máxima,
            //a vida vai pro máximo sem ultrapassar o limite
            dono.setVidaAtual(Math.min(
                    dono.getVidaAtual() + (dano/2),
                    dono.getVidaMax()
            ));
        }
    }
}
