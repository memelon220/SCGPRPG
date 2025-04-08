package servico.excecao.campanha;

public class CampanhaLotadaException extends CampanhaException{
    private final int limite;

    public CampanhaLotadaException(int limite) {
        super("A campanha atingiu o numero maximo de " + limite + " jogadores");
        this.limite = limite;
    }

    public int getLimite() {
        return limite;
    }
}
