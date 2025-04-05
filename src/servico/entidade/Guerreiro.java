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
        int dano = dado.rolarDado(1) + (dono.getForca() - 10) / 2;
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

    public void habilidadeEspecial(Personagem dono, Personagem alvo) {
        Dado dado = new Dado(12);
        int dano = dado.rolarDado(1) + dono.calcularModificador(dono.getForca());

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
