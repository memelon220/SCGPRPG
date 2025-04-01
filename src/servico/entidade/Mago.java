package servico.entidade;

public class Mago extends ClassePersonagem {

    public Mago(String nomeClasse) {
        super("dados.Mago");
    }

    @Override
    public void Atacar(Personagem alvo) {
        Dado dado = new Dado(12);
        int dano = dado.rolarDado(1);// Rola um dado de 12 lados (1 a 12)
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
        System.out.println("Explosão de fogo! Dano causado: " + dano);
        System.out.println("Vida restante de" + alvo.getNome() + ":" + alvo.getVidaAtual());
    }


    @Override
    public void aplicarClasse(Personagem personagem) {
        System.out.println("Classe dados.Mago aplicada com sucesso!");
        System.out.println("---------------------------------------");
        personagem.setVidaMax(personagem.getVidaMax() + 6);
        personagem.setInteligencia(personagem.getInteligencia() + 2);
        personagem.setMagia(personagem.getMagia() + 2);
    }
}
