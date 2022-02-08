package be.vdab.muziek.domain;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@NamedEntityGraph(name = Album.MET_ARTIEST, attributeNodes = @NamedAttributeNode("artiest"))
@Entity
@Table(name = "albums")
public class Album {
    public static final String MET_ARTIEST = "Album.metArtiest";

    @Id
    private long id;
    private String naam;
    private int jaar;
    //geen getter voor nodig, dit is enkel voor equals en hashCode
    private long barcode;
    @Range(min=0, max=10)
    private int score;

    //verzameling value objects met een eigen type -- een album bestaat uit tracks, zonder album zijn er geen tracks
    @ElementCollection
    @CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumId"))
    private Set<Track> tracks = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "labelId")
    private Label label;

    public Album(String naam, int jaar, long barcode, int score/*, Artiest artiest*//*, Label label*/) {
        this.naam = naam;
        this.jaar = jaar;
        this.barcode = barcode;
        this.score = score;
//        setArtiest(artiest);
//        setLabel(label);
    }

    protected Album() {
    }

    //domain logica komt hier, niet later in controller/service layer
    public LocalTime getTijd() {
        var totaal = LocalTime.MIN;
        for (var track : tracks) {
            var tijd = track.getTijd();
            totaal = totaal.plusHours(tijd.getHour()).plusMinutes(tijd.getMinute()).plusSeconds(tijd.getSecond());
        }
        return totaal;
    }

    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }

    /*    public boolean addTrack(Track track) {
        return tracks.add(track);
    }*/

/*    public void setArtiest(Artiest artiest) {
        if (!artiest.getAlbums().contains(this)) {
            artiest.add(this);
        }
        this.artiest = artiest;
    }*/

/*    public void setLabel(Label label) {
        if (!label.getAlbums().contains(this)) {
            label.add(this);
        }
        this.label = label;
    }*/

    public void setScore(int score) {
        this.score = score;
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
