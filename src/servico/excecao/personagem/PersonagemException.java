package servico.excecao.personagem;

public class PersonagemException extends Exception {

    private String msg;

    public PersonagemException(String msg) {
        super(msg);
    }

}
