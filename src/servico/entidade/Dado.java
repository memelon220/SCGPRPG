package servico.entidade;

import java.util.Random;
public class Dado {
    public int lado;
    public Dado(int lado){
        this.lado = lado;
    }

    public int rolarDado(int quantidade) {
        Random random = new Random();
        int aux = 0;
        for (int i = 0; i < quantidade; i++) {
            int dado = random.nextInt(1, this.lado + 1);
            aux += dado;
            System.out.println("Resultado do dado " + (i+1) + ": " + dado);
        }
        System.out.println(quantidade + "d" + this.lado + " = " + aux);
        return aux;
    }

}
