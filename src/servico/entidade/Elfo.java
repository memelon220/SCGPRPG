package servico.entidade;

public class Elfo extends EspeciePersonagem {
    public Elfo() {
        super("Elfo", "\n" +
                "Elfos têm orelhas pontudas e não têm pelos faciais e corporais. Eles vivem por cerca de 750 anos e não dormem, mas entram em transe quando precisam descansar.");
    }
    @Override
    public void aplicarEspecie(Personagem personagem) {
        personagem.setEspecie(new Elfo());
    }
}
