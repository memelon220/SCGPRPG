package servico.excecao.personagem;

public class PersonagemNaoExisteException extends PersonagemException {

    public PersonagemNaoExisteException() {
        super("Este personagem nao existe. Por favor, verifique as informacoes fornecidas e tente novamente.");
    }

}
