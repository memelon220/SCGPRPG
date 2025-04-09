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
        Jogador jogadorRecuperado = repTeste.buscar(jTeste_1.getID());
        assertNotNull(jogadorRecuperado, "Jogador não foi adicionado");
        assertEquals(jTeste_1.getID(), jogadorRecuperado.getID());
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
        Jogador jogadorRecuperado = repTeste.buscar(jTeste_1.getID());
        assertNotNull(jogadorRecuperado, "Jogador não foi adicionado");
        assertEquals(jTeste_1.getID(), jogadorRecuperado.getID());
        assertEquals("Raphael", jogadorRecuperado.getNome());
    }

    @Test
    void testAtualizarJogador() {
        repTeste.adicionar(jTeste_1);
        jTeste_1.setNome("Atualizado");
        repTeste.atualizar(jTeste_1);
        Jogador jogadorRecuperado = repTeste.buscar(jTeste_1.getID());
        assertNotNull(jogadorRecuperado, "Jogador não foi adicionado");
        assertEquals(jTeste_1.getID(), jogadorRecuperado.getID());
        assertEquals("Atualizado", jogadorRecuperado.getNome());
    }

    @Test
    void testExisteJogador(){
        repTeste.adicionar(jTeste_1);
        boolean j1 = repTeste.existe(jTeste_1.getID());
        boolean j2 = repTeste.existe(jTeste_2.getID());
        assertTrue(j1);
        assertFalse(j2);
    }

}