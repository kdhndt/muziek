package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Artiest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
class JpaArtiestRepository implements ArtiestRepository {

    private final EntityManager manager;

    public JpaArtiestRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Artiest> findById(long id) {
        return Optional.ofNullable(manager.find(Artiest.class, id));
    }
}
