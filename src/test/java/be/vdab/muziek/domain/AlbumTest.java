package be.vdab.muziek.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AlbumTest {
    private Artiest artiest1;
    private Label label1;
    private Album album1;

    @BeforeEach
    void beforeEach() {
        artiest1 = new Artiest("testArtiest");
        label1 = new Label("testLabel");
        album1 = new Album("testAlbum", 2001, 1234L, 5, artiest1, label1);
    }

    @Test
    void artiest1IsDeArtiestVanAlbum1() {
//        assertThat(artiest1.getAlbums()).containsOnly(album1);
    }
}