package be.vdab.muziek.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id private long id;
    private String naam;
    private int jaar;
    private long barcode;
    private int score;

    //verzameling value objects met een eigen type
    //een album bestaat uit tracks, zonder album zijn er geen tracks
    @ElementCollection
    @CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumId"))
    @OrderBy("naam")
    private Set<Track> tracks = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artiestId")
    //toegang tot artiest eigenschappen vanuit Album
    private Artiest artiest;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "labelId")
    //toegang tot label eigenschappen vanuit Album
    private Label label;

    public Album(String naam, int jaar, long barcode, int score, Artiest artiest, Label label) {
        this.naam = naam;
        this.jaar = jaar;
        this.barcode = barcode;
        this.score = score;
        setArtiest(artiest);
        setLabel(label);
    }

    protected Album() {
    }

    public boolean addTrack(Track track) {
        return tracks.add(track);
    }

    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }

    //TODO: check this
    public void setArtiest(Artiest artiest) {
        if (!artiest.getAlbums().contains(this)) {
            artiest.add(this);
        }
        this.artiest = artiest;
    }

    public void setLabel(Label label) {
        if (!label.getAlbums().contains(this)) {
            label.add(this);
        }
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public int getJaar() {
        return jaar;
    }

    public long getBarcode() {
        return barcode;
    }

    public int getScore() {
        return score;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return barcode == album.barcode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }
}
