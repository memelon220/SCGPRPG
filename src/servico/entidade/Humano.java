package servico.entidade;

public class Humano extends EspeciePersonagem{
    public Humano(){
        super("Humano", "Na visão da maioria dos mundos, os humanos são as raças mais jovens entre as comuns, chegaram tarde ao cenário mundial e tiveram vida curta em comparação aos anões, elfos e dragões.");
    }

    @Override
    public void aplicarEspecie(Personagem personagem) {
        personagem.setEspecie(new Humano());
    }
}