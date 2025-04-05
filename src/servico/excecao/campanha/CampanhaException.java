package servico.excecao.campanha;

public class CampanhaException extends Exception {

    private String msg;

    public CampanhaException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }


}
