package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Artiest;
import be.vdab.muziek.domain.Label;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.repositories.AlbumRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultAlbumServiceTest {
    private DefaultAlbumService service;
    @Mock
    private AlbumRepository repository;
    private Album album;

    @BeforeEach
    void beforeEach() {
        service = new DefaultAlbumService(repository);
        var artiest1 = new Artiest("testArtiest1");
        var label1 = new Label("testLabel1");
        album = new Album("test", 2022, 9999L, 5, artiest1, label1);
    }

    @Test
    void wijzigScore() {
        when(repository.findById(1)).thenReturn(Optional.of(album));
        service.wijzigScore(1, 7);
        assertThat(album.getScore()).isEqualTo(7);
        verify(repository).findById(1);
    }

    @Test
    void wijzigScoreVoorOnbestaandAlbum() {
        assertThatExceptionOfType(AlbumNietGevondenException.class).isThrownBy(() -> service.wijzigScore(-1, 8));
        verify(repository).findById(-1);
    }

}