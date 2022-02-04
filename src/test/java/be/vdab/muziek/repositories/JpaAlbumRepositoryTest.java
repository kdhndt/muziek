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

    //inclusief lazy loading test
    @Test
    void findById() {
        assertThat(repository.findById(idVanTestAlbum()))
                .hasValueSatisfying(album -> {
                    assertThat(album.getNaam()).isEqualTo("testAlbum");
                    assertThat(album.getArtiest().getNaam()).isEqualTo("testArtiest");
                    //tracks lezen
                    assertThat(album.getTijd()).isEqualTo(LocalTime.of(23, 50, 26));
                });
    }

    @Test
    void findByOnbestaandeId() {
        assertThat(repository.findById(-1L)).isNotPresent();
    }

    //inclusief N+1 test
    @Test
    void findAll() {
        var albums = repository.findAll();
        manager.clear();
        assertThat(albums)
                .hasSize(countRowsInTable(ALBUMS))
                .extracting(Album::getNaam)
                .isSortedAccordingTo(String::compareToIgnoreCase);
        //controle of de artiesten ook geladen zijn
        assertThat(albums)
                .extracting(Album::getArtiest)
                .extracting(Artiest::getNaam)
                .isNotNull();
    }
}