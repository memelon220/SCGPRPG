package servico.entidade;

public class Halfling extends EspeciePersonagem{
    public Halfling() {
        super("Halfling", "\n" +
                "Halflings são um povo afável e alegre. Eles prezam os laços de família e amizade, assim como os confortos do lar e da lareira, abrigando poucos sonhos de ouro ou glória. Mesmo aventureiros entre eles geralmente se aventuram no mundo por razões de comunidade, amizade, desejo de viajar ou curiosidade.");

    }
    @Override
    public void aplicarEspecie(Personagem personagem) {
        personagem.setEspecie(new Halfling());
        System.out.println("Especie Halfling aplicada com sucesso!");
        System.out.println("---------------------------------------");
    }
}
