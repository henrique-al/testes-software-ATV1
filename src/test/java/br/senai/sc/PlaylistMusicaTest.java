package br.senai.sc;

import br.senai.sc.model.playlist.Musica;
import br.senai.sc.model.playlist.PlaylistMusica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlaylistMusicaTest {
    private PlaylistMusica playlist;

    @BeforeEach
    void setUp() {
        playlist = new PlaylistMusica("Minha Playlist");
    }

    @Test
    void getNomeTest() {
        assertEquals("Minha Playlist", playlist.getNome());
    }

    @Test
    void setNomeTest() {
        playlist.setNome("Nova Playlist");
        assertEquals("Nova Playlist", playlist.getNome());
    }

    @Test
    public void tamanhoPlaylistTest(){
        playlist.adicionarMusica(new Musica("Titulo 1", "Artista 1", 300));
        assertEquals(1, playlist.getQuantidadeMusicas());
    }

    @Test
    public void getMusicasTest(){
        Musica musica1 = new Musica("Titulo 1", "Artista 1", 300);
        Musica musica2 = new Musica("Titulo 2", "Artista 2", 300);
        playlist.adicionarMusica(musica1);
        playlist.adicionarMusica(musica2);
        assertEquals(List.of(musica1, musica2), playlist.getMusicas());
    }

    @Test
    void adicionarMusicaTest() {
        assertTrue(playlist.adicionarMusica(new Musica("Titulo 1", "Artista 1", 300)));
        assertEquals(1, playlist.getQuantidadeMusicas());
        assertFalse(playlist.adicionarMusica(null));
        assertEquals(1, playlist.getQuantidadeMusicas());
    }

    @Test
    void removerMusicaTest() {
        Musica musica = new Musica("Titulo 1", "Artista 1",300);
        playlist.adicionarMusica(musica);
        assertTrue(playlist.removerMusica(musica));
        assertEquals(0, playlist.getQuantidadeMusicas());
    }

    @Test
    void buscarMusicaPorTituloTest() {
        Musica musica = new Musica("Titulo 1", "Artista 1", 300);
        playlist.adicionarMusica(musica);
        assertEquals(musica, playlist.buscarMusicaPorTitulo("Titulo 1"));
        assertNull(playlist.buscarMusicaPorTitulo("Titulo 2"));
    }

    @Test
    void buscarMusicasPorArtistaTest() {
        Musica musica1 = new Musica("Titulo 1", "Artista 1", 300);
        Musica musica2 = new Musica("Titulo 2", "Artista 2", 300);
        playlist.adicionarMusica(musica1);
        playlist.adicionarMusica(musica2);
        List<Musica> musicasPorArtista = playlist.buscarMusicasPorArtista("Artista 1");
        assertEquals(1, musicasPorArtista.size());
        assertTrue(musicasPorArtista.contains(musica1));
        assertFalse(musicasPorArtista.contains(musica2));
    }

    @Test
    void ordenarPorTituloTest() {
        Musica musica1 = new Musica("Titulo 2", "Artista 2", 300);
        Musica musica2 = new Musica("Titulo 1", "Artista 1", 300);
        playlist.adicionarMusica(musica1);
        playlist.adicionarMusica(musica2);
        playlist.ordenarPorTitulo();
        assertEquals(List.of(musica2, musica1), playlist.getMusicas());
    }

    @Test
    void OrdenarPorArtistaTest() {
        Musica musica1 = new Musica("Titulo 1", "Artista 2", 300);
        Musica musica2 = new Musica("Titulo 2", "Artista 1", 300);
        playlist.adicionarMusica(musica1);
        playlist.adicionarMusica(musica2);
        playlist.ordenarPorArtista();
        assertEquals(List.of(musica2, musica1), playlist.getMusicas());
    }
}
