package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;

import javax.persistence.EntityManager;
import java.util.Optional;

public class JpaAlbumRepository implements AlbumRepository {
    private final EntityManager manager;

    public JpaAlbumRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Album> findById(long id) {
        return Optional.ofNullable(manager.find(Album.class, id));
    }
}
