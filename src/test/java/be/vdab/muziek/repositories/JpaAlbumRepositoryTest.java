package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Artiest;
import be.vdab.muziek.domain.Track;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql({"/insertArtiest.sql", "/insertLabel.sql", "/insertAlbum.sql"})
@Import(JpaAlbumRepository.class)
class JpaAlbumRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaAlbumRepository repository;
    private static final String ALBUMS = "albums";
    private final EntityManager manager;

    JpaAlbumRepositoryTest(JpaAlbumRepository repository, EntityManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    private long idVanTestAlbum() {
        return jdbcTemplate.queryForObject("select id from albums where barcode = 5678", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestAlbum()))
                .hasValueSatisfying(album -> assertThat(album.getNaam()).isEqualTo("testAlbum"));
    }

    @Test
    void findByOnbestaandeId() {
        assertThat(repository.findById(-1L)).isNotPresent();
    }

    @Test
    void findAll() {
        assertThat(repository.findAll())
                .hasSize(countRowsInTable(ALBUMS))
                .extracting(Album::getNaam)
                .isSorted();
    }

    //N+1 test
    @Test
    void testEntityGraph() {
        var albums = repository.findAll();
        manager.clear();
        assertThat(albums)
                .extracting(Album::getArtiest)
                .extracting(Artiest::getNaam)
                .isNotNull();
    }

    @Test
    void tracksLezen() {
        assertThat(repository.findById(idVanTestAlbum()))
                .hasValueSatisfying(
                        album -> assertThat(album.getTracks())
                                .containsOnly(new Track("testTrack", LocalTime.of(23, 50, 26))));
    }

    @Test
    void artiestLazyLoaded() {
        assertThat(repository.findById(idVanTestAlbum()))
                .hasValueSatisfying(album -> assertThat(album.getArtiest().getNaam()).isEqualTo("testArtiest"));
    }

    @Test
    void labelLazyLoaded() {
        assertThat(repository.findById(idVanTestAlbum()))
                .hasValueSatisfying(album -> assertThat(album.getLabel().getNaam()).isEqualTo("testLabel"));
    }
}