package tests;

import dados.personagem.RepositorioPersonagensArquivo;
import servico.entidade.Personagem;
import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class RepositorioPersonagensArquivoTest {

    private static RepositorioPersonagensArquivo repositorio;
    private static Personagem personagemTeste;

    @BeforeAll
    static void setUp() {
        new File("personagens.dat").delete();
        repositorio = new RepositorioPersonagensArquivo();
        personagemTeste = new Personagem("Link", 1);
    }

    @AfterAll
    static void tearDown() {
        new File("personagens.dat").delete();
    }

    @Test
    void testAdicionar() {
        repositorio.adicionar(personagemTeste);
        assertTrue(repositorio.existe(personagemTeste.getID()));
    }

    @Test
    void testRemover() {
        repositorio.adicionar(personagemTeste);
        repositorio.remover(personagemTeste);
        assertFalse(repositorio.existe(personagemTeste.getID()));
    }

    @Test
    void testAtualizar() {
        repositorio.adicionar(personagemTeste);
        Personagem novoPersonagem = new Personagem("Atualizado1", 1);
        repositorio.atualizar(personagemTeste, novoPersonagem.getID());
        Personagem recuperado = repositorio.buscar(novoPersonagem.getID());
        assertEquals("Atualizado1", recuperado.getNome());
    }

    @Test
    void testBuscar() {
        repositorio.adicionar(personagemTeste);
        Personagem encontrado = repositorio.buscar(personagemTeste.getID());
        assertNotNull(encontrado);
        assertEquals(personagemTeste.getID(), encontrado.getID());
    }

    @Test
    void testExiste() {
        repositorio.adicionar(personagemTeste);
        assertTrue(repositorio.existe(personagemTeste.getID()));
        assertFalse(repositorio.existe("ID_INEXISTENTE"));
    }

    @Test
    void testListar() {
        assertDoesNotThrow(() -> repositorio.listar());
    }

}