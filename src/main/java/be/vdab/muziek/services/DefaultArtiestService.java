package be.vdab.muziek.services;

import be.vdab.muziek.domain.Artiest;
import be.vdab.muziek.repositories.ArtiestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
class DefaultArtiestService implements ArtiestService {
    private final ArtiestRepository artiestRepository;

    DefaultArtiestService(ArtiestRepository artiestRepository) {
        this.artiestRepository = artiestRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Artiest> findById(long id) {
        return artiestRepository.findById(id);
    }
}
