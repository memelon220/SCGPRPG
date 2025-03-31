import java.util.Random;

public class Guerreiro extends ClassePersonagem {
    public Guerreiro(String nomeClasse) {
        super("Guerreiro");
    }

    @Override
    public void aplicarClasse(Personagem personagem) {
        System.out.println("Classe Guerreiro aplicada com sucesso!");
        System.out.println("---------------------------------------");
        personagem.setVida(personagem.getVida() + 10);
        personagem.setForca(personagem.getForca() + 2);
        personagem.setConstituicao(personagem.getConstituicao() + 2);
    }

    public void Atacar(Personagem alvo) {
        Random random = new Random();
        int dano = random.nextInt(1, 9); // Rola um dado de 8 lados (1 a 8)
        alvo.setVida(alvo.getVida() - dano);
        System.out.println("Golpe de machado! Dano causado: " + dano);
        System.out.println("Vida restante de" + alvo.getNome() + ":" + alvo.getVida());
    }
}
