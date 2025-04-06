package servico.entidade;

public class Anão extends EspeciePersonagem {

    public Anão(){
        super("Anão",  "\n" + "Ousados e resistentes, os anões são conhecidos como " +
                "guerreiros habilidosos, mineradores e trabalhadores de pedra e metal. Embora tenham bem menos " +
                "de 1,5 m de altura, os anões são tão largos e compactos que podem pesar tanto quanto um humano" +
                " com quase 60 cm a mais. Sua coragem e resistência também são facilmente páreo para qualquer " +
                "um dos povos maiores.");

    }

    @Override
    public void aplicarEspecie(Personagem personagem){
        personagem.setEspecie(new Anão());
    }

}
