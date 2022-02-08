package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
class DefaultAlbumService implements AlbumService {

    private final AlbumRepository repository;

    DefaultAlbumService(AlbumRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Album> findById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAll() {
        return repository.findAll();
    }

    @Override
    public void wijzigScore(long id, int score) {
        //locken niet nodig?
        repository.findById(id)
                .orElseThrow(AlbumNietGevondenException::new)
                //record lezen en wijzigen in RAM geheugen zorgt ervoor dat JPA de database wijziging voor jou doet (geen JPQL query met update ... nodig)
                .setScore(score);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findByJaar(int jaar) {
        return repository.findByJaar(jaar);
    }

}
