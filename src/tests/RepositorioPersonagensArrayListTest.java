package tests;

import dados.jogador.RepositorioJogadoresArrayList;
import dados.personagem.RepositorioPersonagensArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servico.entidade.Jogador;
import servico.entidade.Personagem;
import servico.entidade.Jogador;
import dados.personagem.RepositorioPersonagensArrayList;
import static org.junit.jupiter.api.Assertions.*;

class RepositorioPersonagensArrayListTest {

    private RepositorioPersonagensArrayList repTeste;
    private Personagem pTeste_1;
    private Personagem pTeste_2;
    private Jogador jTeste_1;
    private Jogador jTeste_2;

    @BeforeEach
    void setUp() {
        repTeste = new RepositorioPersonagensArrayList();
        jTeste_1 = new Jogador("Raphael", 18, "123456");
        jTeste_2 = new Jogador("Luigi", 19, "654321");
        pTeste_1 = new Personagem("Link", true);
        pTeste_2 = new Personagem("Zelda", true);
    }

    @Test
    void testAdicionarPersonagem(){
        repTeste.adicionar(pTeste_1);
        Personagem personagemRecuperado = repTeste.buscar(jTeste_1, "Link");
        assertNotNull(personagemRecuperado, "Personagem não foi adicionado");
        assertEquals(jTeste_1, personagemRecuperado.getJogador());
        assertEquals("Link", personagemRecuperado.getNome());
    }


}