package servico.entidade;

public class Mago extends ClassePersonagem {

    public Mago() {
        super("Mago");
    }

    @Override
    public void Atacar(Personagem alvo) {
        Dado dado = new Dado(12);
        int dano = dado.rolarDado(1);// Rola um dado de 12 lados (1 a 12)
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
    }


    @Override
    public void aplicarClasse(Personagem personagem) {
        personagem.setClasse(new Mago());
        personagem.setVidaMax(personagem.getVidaMax() + 6);
        personagem.setInteligencia(personagem.getInteligencia() + 2);
        personagem.setMagia(personagem.getMagia() + 2);
    }
}
