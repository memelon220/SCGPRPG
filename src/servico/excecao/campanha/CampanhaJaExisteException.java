package servico.excecao.campanha;

public class CampanhaJaExisteException extends CampanhaException {

    public CampanhaJaExisteException() {
        super("Essa campanha ja existe e nao pode ser adicionada novamente.");
    }


}
