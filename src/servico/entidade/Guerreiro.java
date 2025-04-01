package servico.entidade;

public class Guerreiro extends ClassePersonagem {
    public Guerreiro(String nomeClasse) {
        super("dados.Guerreiro");
    }

    @Override
    public void Atacar(Personagem alvo) {
        Dado dado = new Dado(8);
        int dano = dado.rolarDado(1);// Rola um dado de 8 lados (1 a 8)
        alvo.setVidaAtual(alvo.getVidaAtual() - dano);
        System.out.println("Golpe de machado! Dano causado: " + dano);
        System.out.println("Vida restante de" + alvo.getNome() + ":" + alvo.getVidaAtual());
    }

    @Override
    public void aplicarClasse(Personagem personagem) {
        System.out.println("Classe dados.Guerreiro aplicada com sucesso!");
        System.out.println("---------------------------------------");
        personagem.setVidaMax(personagem.getVidaMax() + 10);
        personagem.setForca(personagem.getForca() + 2);
        personagem.setConstituicao(personagem.getConstituicao() + 2);
    }

}
