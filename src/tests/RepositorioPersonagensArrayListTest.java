package tests;

import dados.personagem.RepositorioPersonagensArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servico.entidade.Personagem;
import static org.junit.jupiter.api.Assertions.*;

class RepositorioPersonagensArrayListTest {

    private RepositorioPersonagensArrayList repTeste;
    private Personagem pTeste_1;
    private Personagem pTeste_2;

    @BeforeEach
    void setUp() {
        repTeste = new RepositorioPersonagensArrayList();
        pTeste_1 = new Personagem("Link");
        pTeste_2 = new Personagem("Zelda");
    }

    @Test
    void testAdicionarPersonagem(){
        repTeste.adicionar(pTeste_1);
        Personagem personagemRecuperado = repTeste.buscar(pTeste_1.getID());
        assertNotNull(personagemRecuperado, "Personagem não foi adicionado");
        assertEquals(pTeste_1.getID(), personagemRecuperado.getID());
        assertEquals("Link", personagemRecuperado.getNome());
    }

    @Test
    void testRemoverPersonagem(){
        int tamanhoInicial = repTeste.getArrayPersonagens().size();
        repTeste.adicionar(pTeste_1);
        String idTeste = pTeste_1.getID();
        repTeste.remover(pTeste_1);
        int tamanhoFinal = repTeste.getArrayPersonagens().size();
        assertEquals(tamanhoFinal, tamanhoInicial);
        assertFalse(repTeste.existe(idTeste));
    }

    @Test
    void testBuscarPersonagem(){
        repTeste.adicionar(pTeste_1);
        repTeste.adicionar(pTeste_2);
        Personagem personagemRecuperado = repTeste.buscar(pTeste_1.getID());
        assertNotNull(personagemRecuperado, "Personagem não foi adicionado");
        assertEquals(pTeste_1.getID(), personagemRecuperado.getID());
        assertEquals("Link", personagemRecuperado.getNome());
    }

    @Test
    void testAtualizarPersonagem(){
        repTeste.adicionar(pTeste_1);
        repTeste.atualizar(pTeste_1, pTeste_2);
        Personagem personagemRecuperado = repTeste.buscar(pTeste_2.getID());
        assertNotNull(personagemRecuperado, "Personagem não foi adicionado");
        assertEquals(pTeste_2.getID(), personagemRecuperado.getID());
        assertEquals("Zelda", personagemRecuperado.getNome());
    }

    @Test
    void testExistePersonagem(){
        repTeste.adicionar(pTeste_1);
        boolean j1 = repTeste.existe(pTeste_1.getID());
        boolean j2 = repTeste.existe(pTeste_2.getID());
        assertTrue(j1);
        assertFalse(j2);
    }

}