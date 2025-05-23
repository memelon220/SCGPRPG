package servico.entidade;

public class Draconato extends EspeciePersonagem{
    public Draconato(){
        super("Draconato",  "\n" + "Nascidos de dragões, como seu nome proclama, os draconatos caminham orgulhosamente por um mundo que os recebe com uma incompreensão assustadora.");
    }

    @Override
    public void aplicarEspecie(Personagem personagem) {
        personagem.setEspecie(new Draconato());
    }

}
