package servico.entidade;

public class Ladino extends ClassePersonagem {
    private Personagem dono;
    public Ladino(Personagem dono) {
        super("Ladino");
        this.dono = dono;
    }

    @Override
    public void aplicarClasse(Personagem dono) {
        dono.setClasse(new Ladino(dono));
        dono.setVidaMax(dono.getVidaMax() - 5);
        dono.setVidaAtual(dono.getVidaAtual() - 5);
        dono.setInteligencia(dono.getDestreza() + 2);
        dono.setInteligencia(dono.getInteligencia() + 2);
    }

    @Override
    public void Atacar(Personagem alvo) {
        Dado dado = new Dado(10);
        int dano = dado.rolarDado(1);// Rola um dado de 10 lados (1 a 10)
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

    @Override
    public void habilidadeEspecial(Personagem dono, Personagem alvo){
        Dado dadoCrit = new Dado(20);
        Dado dadoDano = new Dado(12);

        int dano = dadoDano.rolarDado(2);
        int crit = dadoCrit.rolarDado(1);

        if(crit >= 16){
            dano *= 3;
            dono.setVidaAtual(dono.getVidaAtual() + 5);
            if (dono.getVidaAtual() > dono.getVidaMax()){
                dono.setVidaAtual(dono.getVidaMax());
            }
        }
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }

}
