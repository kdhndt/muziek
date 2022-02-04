package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository {
    Optional<Album> findById(long id);
    List<Album> findAll();
    List<Album> findByJaar(int jaar);
}