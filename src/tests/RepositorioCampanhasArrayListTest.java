package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servico.entidade.Campanha;
import dados.campanha.RepositorioCampanhasArrayList;
import servico.entidade.Narrador;

import static org.junit.jupiter.api.Assertions.*;

class RepositorioCampanhasArrayListTest {

    private RepositorioCampanhasArrayList repTeste;
    private Campanha cTeste_1;
    private Campanha cTeste_2;


    @BeforeEach
    void setUp() {
        repTeste = new RepositorioCampanhasArrayList();
        cTeste_1 = new Campanha(new Narrador("Pedro", 20, "123"), "Aventura 1", "Uma aventura aí", "12/12/2025","Ativa");
        cTeste_2 = new Campanha(new Narrador("Pedro", 25, "123"), "Aventura 2", "Uma aventura aí pt 2", "12/12/2026", "Inativa");
    }

    @Test
    void testAdicionarCampanha(){
        repTeste.adicionar(cTeste_1);
        Campanha campanhaRecuperada = repTeste.buscar("123456");
        assertNotNull(campanhaRecuperada, "Campanha não foi adicionada");
        assertEquals("123456", campanhaRecuperada.getID());
        assertEquals("Aventura 1", campanhaRecuperada.getNome());
    }

    @Test
    void testRemoverCampanha() {
        int tamanhoInicial = repTeste.getArrayCampanhas().size();
        repTeste.adicionar(cTeste_1);
        repTeste.remover(cTeste_1);
        int tamanhoFinal = repTeste.getArrayCampanhas().size();
        assertEquals(tamanhoFinal, tamanhoInicial);
        assertFalse(repTeste.existe("123456"));
    }

    @Test
    void testBuscarCampanha() {
        repTeste.adicionar(cTeste_1);
        repTeste.adicionar(cTeste_2);
        Campanha campanhaRecuperada = repTeste.buscar("654321");
        assertNotNull(campanhaRecuperada, "Campanha não foi adicionada");
        assertEquals("654321", campanhaRecuperada.getID());
        assertEquals("Aventura 2", campanhaRecuperada.getNome());
    }

    @Test
    void testAtualizarCampanha() {
        repTeste.adicionar(cTeste_1);
        repTeste.atualizar(cTeste_1, cTeste_2);
        Campanha campanhaRecuperada = repTeste.buscar("654321");
        assertNotNull(campanhaRecuperada, "Campanha não foi adicionada");
        assertEquals("654321", campanhaRecuperada.getID());
        assertEquals("Aventura 2", campanhaRecuperada.getNome());
    }

    @Test
    void testExisteCampanha(){
        repTeste.adicionar(cTeste_1);
        boolean c1 = repTeste.existe("123456");
        boolean c2 = repTeste.existe("654321");
        assertTrue(c1);
        assertFalse(c2);
    }

}