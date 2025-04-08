package servico.excecao.campanha;

public class PersonagemJaEstaNaCampanhaException extends CampanhaException {
    public PersonagemJaEstaNaCampanhaException() {
        super("O personagem escolhido ja esta na campanha!");

    }
}
