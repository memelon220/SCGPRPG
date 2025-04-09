package tests;

import fachada.SCGPRPG;
import iu.TelaGerenciamentoCampanha;
import org.junit.jupiter.api.Test;
import servico.entidade.Narrador;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class TelaGerenciamentoCampanhaTestSimplificado {

    @Test
    public void testListarCampanhas_SemCampanhas_ExibeMensagem() {
        SCGPRPG fachadaReal = new SCGPRPG();
        Narrador narradorReal = new Narrador("teste", 18, "12345678");
        TelaGerenciamentoCampanha tela = new TelaGerenciamentoCampanha(fachadaReal, narradorReal);
        tela.listarCampanhas();
    }

    @Test
    public void testExistemJogadores_ListaVazia_RetornaFalse() {
        TelaGerenciamentoCampanha tela = new TelaGerenciamentoCampanha(null, null);
        assertFalse(tela.existemJogadores(new ArrayList<>()));
    }
}
