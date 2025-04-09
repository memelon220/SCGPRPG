package tests;

import dados.jogador.RepositorioJogadoresArquivo;
import servico.entidade.Jogador;
import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class RepositorioJogadoresArquivoTest {

    private static final String TEST_ID = "J999999";
    private static RepositorioJogadoresArquivo repositorio;
    private static Jogador jogadorTeste;

    @BeforeAll
    static void setUp() {
        new File("jogadores.dat").delete();
        repositorio = new RepositorioJogadoresArquivo();
        jogadorTeste = new Jogador("Teste1", 18, "123");
        jogadorTeste.setID(TEST_ID);
    }

    @AfterAll
    static void tearDown() {
        new File("jogadores.dat").delete();
    }

    @Test
    void testAdicionar() {
        repositorio.adicionar(jogadorTeste);
        assertTrue(repositorio.existe(TEST_ID));
    }

    @Test
    void testRemover() {
        repositorio.adicionar(jogadorTeste);
        repositorio.remover(jogadorTeste);
        assertFalse(repositorio.existe(TEST_ID));
    }

    @Test
    void testAtualizar() {
        repositorio.adicionar(jogadorTeste);
        jogadorTeste.setNome("Atualizado");
        repositorio.atualizar(jogadorTeste);
        assertEquals("Atualizado", jogadorTeste.getNome());
    }

    @Test
    void testBuscar() {
        repositorio.adicionar(jogadorTeste);
        Jogador encontrado = repositorio.buscar(TEST_ID);
        assertNotNull(encontrado);
        assertEquals(TEST_ID, encontrado.getID());
    }

    @Test
    void testExiste() {
        repositorio.adicionar(jogadorTeste);
        assertTrue(repositorio.existe(TEST_ID));
        assertFalse(repositorio.existe("ID_INEXISTENTE"));
    }

    @Test
    void testListar() {
        assertDoesNotThrow(() -> repositorio.listar());
    }

}