package be.vdab.muziek.domain;

import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artiesten")
public class Artiest {
    @Id private long id;
    private String naam;

/*    @OneToMany(mappedBy = "artiest")
    @OrderBy("jaar")
    private Set<Album> albums = new LinkedHashSet<>();*/

    public Artiest(String naam) {
        this.naam = naam;
    }

    protected Artiest() {
    }
/*
    public Set<Album> getAlbums() {
        return Collections.unmodifiableSet(albums);
    }*/

/*    public boolean add(Album album) {
        var toegevoegd = albums.add(album);
        var vorigeArtiest = album.getArtiest();
        if (vorigeArtiest != null && vorigeArtiest != this) {
            vorigeArtiest.albums.remove(album);
        }
        if (this != vorigeArtiest) {
            album.setArtiest(this);
        }
        return toegevoegd;
    }*/

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
