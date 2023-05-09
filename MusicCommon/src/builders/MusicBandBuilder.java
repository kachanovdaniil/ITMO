/**

 The MusicBandBuilder class is a builder class for creating MusicBand objects.
 It allows to set values for all properties of the MusicBand object and create an instance of the object
 using the createMusicBand() method.
 */
package builders;
import common.Album;
import common.Coordinates;
import common.MusicBand;
import common.MusicGenre;

import java.time.LocalDate;

public class MusicBandBuilder implements Buildable<MusicBand>{

    private long id;
    private String name;
    private Coordinates coordinates;
    private Long numberOfParticipants;
    private MusicGenre genre;
    private Album bestAlbum;
    private LocalDate creationDate;

    /**
     * Sets the name of the music band.
     *
     * @param name the name of the music band.
     * @return an instance of MusicBandBuilder.
     */
    public MusicBandBuilder setName(String name) {
        if(name == null)
            throw new IllegalArgumentException("The name of the music band cannot be null");
        this.name = name;
        return this;
    }

    /**
     * Sets the coordinates of the location where the music band is located.
     *
     * @param coordinates the coordinates of the location where the music band is located.
     * @return an instance of MusicBandBuilder.
     */
    public MusicBandBuilder setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    /**
     * Sets the number of participants in the music band.
     *
     * @param numberOfParticipants the number of participants in the music band.
     * @return an instance of MusicBandBuilder.
     */
    public MusicBandBuilder setNumberOfParticipants(Long numberOfParticipants) {
        if (numberOfParticipants <= 0)
            throw new IllegalArgumentException("The number of participants in the music band must be greater than 0");
        this.numberOfParticipants = numberOfParticipants;
        return this;
    }

    /**
     * Sets the music genre performed by the music band.
     *
     * @param genre the music genre performed by the music band.
     * @return an instance of MusicBandBuilder.
     */
    public MusicBandBuilder setGenre(MusicGenre genre) {
        this.genre = genre;
        return this;
    }

    /**
     * Sets the best album of the music band.
     *
     * @param bestAlbum the best album of the music band.
     * @return an instance of MusicBandBuilder.
     */
    public MusicBandBuilder setBestAlbum(Album bestAlbum) {
        this.bestAlbum = bestAlbum;
        return this;
    }

    /**
     * Sets the creation date of the music band.
     *
     * @param creationDate the creation date of the music band.
     * @return an instance of MusicBandBuilder.
     */
    public MusicBandBuilder setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    /**
     * Creates a new instance of the MusicBand object based on the set property values.
     *
     * @return an instance of the MusicBand object.
     */
    @Override
    public MusicBand build() {
        return new MusicBand(name, coordinates, creationDate, numberOfParticipants, genre, bestAlbum);
    }

    /**
     * Sets the identifier of the music band.
     *
     * @param id the identifier of the music band.
     * @return an instance of MusicBandBuilder.
     */
    public MusicBandBuilder setId(long id) {
        this.id = id;
        return this;
    }
}