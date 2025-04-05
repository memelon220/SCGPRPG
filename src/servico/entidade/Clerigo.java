package servico.entidade;

public class Clerigo extends ClassePersonagem{
    private Personagem dono;

    public Clerigo(Personagem dono){
        super("Clérigo");
        this.dono = dono;
    }


    @Override
    public void aplicarClasse(Personagem dono) {
        dono.setClasse(new Clerigo(dono));

        dono.setMagia(dono.getMagia() + 10);
        dono.setManaAtual(dono.getManaAtual() + 10);

        dono.setVidaMax(dono.getVidaMax()+10);
        dono.setVidaAtual(dono.getVidaAtual()+10);

        dono.setSabedoria(dono.getSabedoria() + 2);
        dono.setCarisma(dono.getCarisma() + 2);
    }

    @Override
    public void Atacar(Personagem alvo) {
        Dado dado = new Dado(8);
        int dano = dado.rolarDado(1) + dono.calcularModificador(dono.getForca());
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

    @Override
    public void habilidadeEspecial(Personagem dono, Personagem alvo) {
        Dado dado = new Dado(8);
        int cura = dado.rolarDado(2) + dono.calcularModificador(dono.getSabedoria()); // Cura base + bônus de sabedoria

        alvo.setVidaAtual(Math.min(
                alvo.getVidaAtual() + cura,
                alvo.getVidaMax()
        ));

    }
}
