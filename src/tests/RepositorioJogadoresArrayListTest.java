package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servico.entidade.Jogador;
import dados.jogador.RepositorioJogadoresArrayList;
import static org.junit.jupiter.api.Assertions.*;

class RepositorioJogadoresArrayListTest {

    private RepositorioJogadoresArrayList repTeste;
    private Jogador jTeste_1;
    private Jogador jTeste_2;

    @BeforeEach
    void setUp() {
        repTeste = new RepositorioJogadoresArrayList();
        jTeste_1 = new Jogador("Raphael", 18, "123");
        jTeste_2 = new Jogador("Luigi", 19, "123");
    }

    @Test
    void testAdicionarJogador() {
        repTeste.adicionar(jTeste_1);
        Jogador jogadorRecuperado = repTeste.buscar("123456");
        assertNotNull(jogadorRecuperado, "Jogador não foi adicionado");
        assertEquals("123456", jogadorRecuperado.getID());
        assertEquals("Raphael", jogadorRecuperado.getNome());
    }

    @Test
    void testRemoverJogador() {
        int tamanhoInicial = repTeste.getArrayJogadores().size();
        repTeste.adicionar(jTeste_1);
        repTeste.remover(jTeste_1);
        int tamanhoFinal = repTeste.getArrayJogadores().size();
        assertEquals(tamanhoFinal, tamanhoInicial);
        assertFalse(repTeste.existe("123456"));
    }

    @Test
    void testBuscarJogador() {
        repTeste.adicionar(jTeste_1);
        repTeste.adicionar(jTeste_2);
        Jogador jogadorRecuperado = repTeste.buscar("654321");
        assertNotNull(jogadorRecuperado, "Jogador não foi adicionado");
        assertEquals("654321", jogadorRecuperado.getID());
        assertEquals("Luigi", jogadorRecuperado.getNome());
    }

    @Test
    void testAtualizarJogador() {
        repTeste.adicionar(jTeste_1);
        repTeste.atualizar(jTeste_1, jTeste_2);
        Jogador jogadorRecuperado = repTeste.buscar("654321");
        assertNotNull(jogadorRecuperado, "Jogador não foi adicionado");
        assertEquals("654321", jogadorRecuperado.getID());
        assertEquals("Luigi", jogadorRecuperado.getNome());
    }

    @Test
    void testExisteJogador(){
        repTeste.adicionar(jTeste_1);
        boolean j1 = repTeste.existe("123456");
        boolean j2 = repTeste.existe("654321");
        assertTrue(j1);
        assertFalse(j2);
    }

}