package be.vdab.muziek.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "labels")
public class Label {
    @Id private long id;
    String naam;

/*    @OneToMany(mappedBy = "label")
    @OrderBy("jaar")
    private Set<Album> albums = new LinkedHashSet<>();*/

    public Label(String naam) {
        this.naam = naam;
    }

    protected Label() {
    }

/*    public Set<Album> getAlbums() {
        return Collections.unmodifiableSet(albums);
    }*/

/*    public boolean add(Album album) {
        var toegevoegd = albums.add(album);
        var vorigLabel = album.getLabel();
        if (vorigLabel != null && vorigLabel != this) {
            vorigLabel.albums.remove(album);
        }
        if (this != vorigLabel) {
            album.setLabel(this);
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
