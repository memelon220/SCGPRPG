package tests;

import fachada.SCGPRPG;
import iu.TelaBatalha;
import org.junit.jupiter.api.Test;
import servico.entidade.*;
import static org.junit.jupiter.api.Assertions.*;

public class TelaBatalhaTest {

    @Test
    public void testIniciarBatalha_PersonagemNulo_LancaExcecao() {
        SCGPRPG fachada = new SCGPRPG();
        Narrador narrador = new Narrador("teste", 19, "12345678");
        TelaBatalha tela = new TelaBatalha(fachada, narrador);
        Personagem guerreiro = new Personagem("pTeste", 1);
        guerreiro.setNome("Guerreiro");
        guerreiro.setVidaAtual(100);
        guerreiro.setClasse(new Guerreiro(guerreiro));
        assertThrows(NullPointerException.class, () -> {
            tela.iniciarBatalha(guerreiro, null);
        });
    }

}