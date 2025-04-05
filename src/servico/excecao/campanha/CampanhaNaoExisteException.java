package servico.excecao.campanha;

public class CampanhaNaoExisteException extends CampanhaException {
    public CampanhaNaoExisteException() {
        super("Essa campanha nao existe. Por favor, verifique as informacoes fornecidas e tente novamente.");
    }
}
