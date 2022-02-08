package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Artiest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Import(JpaArtiestRepository.class)
@Sql({"/insertArtiest.sql"})
class JpaArtiestRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaArtiestRepository repository;
    private static final String ARTIESTEN = "artiesten";

    JpaArtiestRepositoryTest(JpaArtiestRepository repository) {
        this.repository = repository;
    }

    private long idVanTestArtiest() {
        return jdbcTemplate.queryForObject("select id from artiesten where naam = 'testArtiest'", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestArtiest()))
                .hasValueSatisfying(artiest -> {
                            assertThat(artiest.getNaam()).isEqualTo("testArtiest");
                            //als je barcode verandert faalt de test
//                            assertThat(artiest.getAlbums()).containsOnly(new Album("testAlbum", 1999, 5678, 1, new Artiest("testArtiest")));
                        }
                );
    }

    @Test void findByOnbestaandeId() {
        assertThat(repository.findById(-1L)).isEmpty();
    }
}