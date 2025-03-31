//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
     Personagem personagem = new Personagem("Goku", true);
     personagem.exibirFicha();
     personagem.adicionarXP(610);
     personagem.exibirXP();
     personagem.exibirFicha();
     System.out.println("---------------------------------------");

     personagem.rolarDado(2, 6);

    }
}