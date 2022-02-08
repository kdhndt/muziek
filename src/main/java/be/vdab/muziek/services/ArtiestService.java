package be.vdab.muziek.services;

import be.vdab.muziek.domain.Artiest;

import java.util.Optional;

public interface ArtiestService {
    Optional<Artiest> findById(long id);
}
