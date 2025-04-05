package servico.entidade;

public class Mago extends ClassePersonagem {
    private Personagem dono;
    public Mago(Personagem dono) {
        super("Mago");
        this.dono = dono;
    }

    @Override
    public void aplicarClasse(Personagem dono) {
        dono.setClasse(new Mago(dono));

        dono.setMagia(dono.getMagia() + 10);
        dono.setManaAtual(dono.getManaAtual() + 10);

        dono.setInteligencia(dono.getInteligencia() + 2);
        dono.setMagia(dono.getMagia() + 2);
    }

    @Override
    public void Atacar(Personagem alvo) {
        Dado dado = new Dado(8);
        int dano = dado.rolarDado(1);
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

    public void habilidadeEspecial(Personagem dono, Personagem alvo){
        if(dono.getManaAtual() >= 5) {
            Dado dado = new Dado(10);
            int dano = dado.rolarDado(2);
            alvo.setVidaAtual(alvo.getVidaAtual() - dano);
            dono.setManaAtual(dono.getManaAtual() - 5);
        }
    }

}
