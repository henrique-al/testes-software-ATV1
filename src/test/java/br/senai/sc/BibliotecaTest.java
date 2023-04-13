package br.senai.sc;

import br.senai.sc.model.biblioteca.Biblioteca;
import br.senai.sc.model.biblioteca.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BibliotecaTest {

    private Biblioteca biblioteca;
    private final Livro livro1 = new Livro("Harry Potter e a pedra filosofal",
            "J. K. Rowling",
            "Aventura",
            1997);
    private final Livro livro2 = new Livro("Harry Potter e a câmara secreta",
            "J. K. Rowling",
            "Aventura",
            1998);

    @BeforeEach
    public void setUp(){
        biblioteca = new Biblioteca("Biblioteca do Fulano");
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
    }

    @Test
    public void getLivrosTest(){
        assertEquals(List.of(livro1, livro2), biblioteca.getLivros());
    }

    @Test
    public void addLivroTest(){
        assertFalse(biblioteca.adicionarLivro(null));
        assertTrue(biblioteca.adicionarLivro(new Livro("Harry Potter e o prisioneiro de azkaban",
                "J. K. Rowling",
                "Aventura",
                1999))
        );
        assertEquals(new Livro("Harry Potter e a ordem da fenix",
                "J. K. Rowling",
                "Aventura",
                2003).getAutor(), biblioteca.getLivros().get(0).getAutor());
    }

    @Test
    public void quantidadeLivrosTest(){
        assertEquals(2, biblioteca.getQuantidadeLivros());
    }

    @Test
    public void getNomeTest(){
        assertEquals("Biblioteca do Fulano", biblioteca.getNome());
    }

    @Test
    public void setNomeTest(){
        biblioteca.setNome("Biblioteca municipal");
        assertEquals("Biblioteca municipal", biblioteca.getNome());
    }

    @Test
    public void removerLivroTest(){
        assertFalse(biblioteca.removerLivro(new Livro("", "", "", 10)));
        assertTrue(biblioteca
                .removerLivro(
                        biblioteca
                                .buscarLivroPorTitulo("Harry Potter e a pedra filosofal")
                )
        );
    }

    @Test
    public void livroPorTituloTest(){
        assertNull(biblioteca.buscarLivroPorTitulo("Jogos Vorazes"));
        biblioteca.adicionarLivro(new Livro("Harry Potter e a pedra filosofal",
                "J. K. Rowling",
                "Aventura",
                2001));
        assertEquals("Harry Potter e a pedra filosofal",
                biblioteca
                        .buscarLivroPorTitulo("Harry Potter e a pedra filosofal")
                        .getTitulo());
    }

    @Test
    public void buscarLivrosPorAutorTest(){
        assertEquals(0, biblioteca.buscarLivrosPorAutor("Julio Verne").size());
        assertEquals(2, biblioteca.buscarLivrosPorAutor("J. K. Rowling").size());
    }

    @Test
    public void buscarLivrosPorGenero(){
        assertEquals(0, biblioteca.buscarLivrosPorGenero("Romance").size());
        assertEquals(2, biblioteca.buscarLivrosPorGenero("Aventura").size());
    }

}
