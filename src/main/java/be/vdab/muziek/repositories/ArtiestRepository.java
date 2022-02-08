package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Artiest;

import java.util.Optional;

public interface ArtiestRepository {
    Optional<Artiest> findById(long id);
}
