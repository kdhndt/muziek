package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Optional<Album> findById(long id);
    List<Album> findAll();
    void wijzigScore(long id, int score);
    List<Album> findByJaar(int jaar);

}
