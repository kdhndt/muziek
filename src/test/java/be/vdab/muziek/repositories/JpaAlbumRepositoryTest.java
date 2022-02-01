package be.vdab.muziek.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql({"/insertArtiest.sql", "/insertLabel.sql", "/insertAlbum.sql"})
@Import(JpaAlbumRepository.class)
class JpaAlbumRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JpaAlbumRepository repository;

    JpaAlbumRepositoryTest(JpaAlbumRepository repository) {
        this.repository = repository;
    }

    private long idVanTestAlbum() {
        return jdbcTemplate.queryForObject("select id from albums where barcode = 5678", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestAlbum()))
                .hasValueSatisfying(album -> assertThat(album.getNaam()).isEqualTo("testAlbum"));
    }
}