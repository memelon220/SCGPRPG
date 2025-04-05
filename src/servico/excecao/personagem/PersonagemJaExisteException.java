package servico.excecao.personagem;

public class PersonagemJaExisteException extends PersonagemException {

    public PersonagemJaExisteException() {
        super("Este personagem ja existe e nao pode ser cirado. Por favor, verifique as informacoes fornecidas e tente novamente.");
    }
}
