package servico.excecao.jogador;

public class JogadorException extends Exception{

    private String msg;

    public JogadorException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}
