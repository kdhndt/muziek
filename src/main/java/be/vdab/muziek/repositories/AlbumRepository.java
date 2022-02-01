package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;

import java.util.Optional;

public interface AlbumRepository {
    Optional<Album> findById(long id);
}