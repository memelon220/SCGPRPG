package servico.excecao.personagem;

public class EntradaForaDoLimiteException extends PersonagemException {

    public EntradaForaDoLimiteException() {
        super("Entrada fora do limite. Por favor, escolha somente numeros de 8 a 18 para os atributos.");
    }
}
