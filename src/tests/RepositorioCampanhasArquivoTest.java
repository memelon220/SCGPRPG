package tests;

import dados.campanha.RepositorioCampanhasArquivo;
import servico.entidade.Campanha;
import servico.entidade.Narrador;
import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class RepositorioCampanhasArquivoTest {

    private static RepositorioCampanhasArquivo repositorio;
    private static Campanha campanhaTeste;

    @BeforeAll
    static void setUp() {
        new File("campanhas.dat").delete();
        repositorio = new RepositorioCampanhasArquivo();
        campanhaTeste = new Campanha(new Narrador("Pedro", 20, "123"), "Aventura 1", "Uma aventura aí","ABERTA", 4);
    }

    @AfterAll
    static void tearDown() {
        new File("campanhas.dat").delete();
    }

    @Test
    void testAdicionar() {
        repositorio.adicionar(campanhaTeste);
        assertTrue(repositorio.existe(campanhaTeste.getID()));
    }

    @Test
    void testRemover() {
        repositorio.adicionar(campanhaTeste);
        repositorio.remover(campanhaTeste);
        assertFalse(repositorio.existe(campanhaTeste.getID()));
    }

    @Test
    void testAtualizar() {
        repositorio.adicionar(campanhaTeste);
        campanhaTeste.setNome("Aventura 2");
        repositorio.atualizar(campanhaTeste);
        Campanha recuperada = repositorio.buscar(campanhaTeste.getID());
        assertEquals("Aventura 2", recuperada.getNome());
    }

    @Test
    void testBuscar() {
        repositorio.adicionar(campanhaTeste);
        Campanha encontrada = repositorio.buscar(campanhaTeste.getID());
        assertNotNull(encontrada);
        assertEquals(campanhaTeste.getID(), encontrada.getID());
    }

    @Test
    void testExiste() {
        repositorio.adicionar(campanhaTeste);
        assertTrue(repositorio.existe(campanhaTeste.getID()));
        assertFalse(repositorio.existe("ID_INEXISTENTE"));
    }

    @Test
    void testListar() {
        assertDoesNotThrow(() -> repositorio.listar());
    }

}