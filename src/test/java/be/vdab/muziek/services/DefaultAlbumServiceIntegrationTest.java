package be.vdab.muziek.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Import(DefaultAlbumService.class)
@ComponentScan(value = "be.vdab.muziek.repositories", resourcePattern = "JpaAlbumRepository.class")
@Sql({"/insertArtiest.sql", "/insertLabel.sql", "/insertAlbum.sql"})
public class DefaultAlbumServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String ALBUMS = "albums";
    private final DefaultAlbumService service;
    private final EntityManager manager;

    public DefaultAlbumServiceIntegrationTest(DefaultAlbumService service, EntityManager manager) {
        this.service = service;
        this.manager = manager;
    }

    private long idVanTestAlbum() {
        return jdbcTemplate.queryForObject("select id from albums where barcode = 5678", Long.class);
    }

    @Test
    void wijzigScore() {
        var id = idVanTestAlbum();
        service.wijzigScore(id, 4);
        manager.flush();
        assertThat(countRowsInTableWhere(ALBUMS, "score = 4 and id = " + id)).isOne();
    }
}