package be.vdab.muziek.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AlbumTest {
/*    private Artiest artiest1;
    private Artiest artiest2;
    private Label label1;
    private Label label2;
    private Album album1;

    @BeforeEach
    void beforeEach() {
        artiest1 = new Artiest("testArtiest1");
        artiest2 = new Artiest("testArtiest2");
        album1 = new Album("testAlbum", 2001, 1234L, 5, artiest1);
    }

    @Test void wijzigScore() {
        album1.setScore(7);
        assertThat(album1.getScore()).isEqualTo(7);
    }

    //album kant

    @Test
    void album1KomtVoorInDeAlbumsVanArtiest1() {
        assertThat(album1.getArtiest()).isEqualTo(artiest1);
        assertThat(artiest1.getAlbums()).contains(album1);
    }

    @Test
    void album1VerhuistVanArtiest1NaarArtiest2() {
        album1.setArtiest(artiest2);
        assertThat(artiest2.getAlbums()).contains(album1);
        assertThat(artiest1.getAlbums()).isEmpty();
    }

    @Test
    void eenNullArtiestInDeSetterMislukt() {
        assertThatNullPointerException().isThrownBy(() -> album1.setArtiest(null));
    }

    //artiest kant

    @Test
    void album1VerhuistVanArtiest1NaarArtiest2AndereKant() {
        assertThat(artiest2.add(album1)).isTrue();
        assertThat(artiest1.getAlbums()).doesNotContain(album1);
        assertThat(artiest2.getAlbums()).containsOnly(album1);
        assertThat(album1.getArtiest()).isEqualTo(artiest2);
    }*/
}