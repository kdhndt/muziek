package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
class JpaAlbumRepository implements AlbumRepository {
    private final EntityManager manager;

    JpaAlbumRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Album> findById(long id) {
        return Optional.ofNullable(manager.find(Album.class, id));
    }

    @Override
    public List<Album> findAll() {
        return manager.createNamedQuery("Album.findAll", Album.class)
                .setHint("javax.persistence.loadgraph", manager.createEntityGraph("Album.metArtiest"))
                .getResultList();
    }

    @Override
    public List<Album> findByJaar(int jaar) {
        return manager.createNamedQuery("Album.findByJaar", Album.class)
                .setParameter("jaar", jaar)
                .getResultList();
    }
}