package common.descriptions;

import common.builders.AlbumBuilder;
import common.builders.Buildable;
import common.builders.CoordinatesBuilder;
import common.builders.MusicBandBuilder;
import common.Album;
import common.Coordinates;
import common.MusicBand;
import common.MusicGenre;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class MusicBandDescription extends LoadDescription<MusicBand> implements Serializable {
    {
        MusicBandBuilder musicBandBuilder = new MusicBandBuilder();
        this.builder = musicBandBuilder;
        fields = new ArrayList<>(Arrays.asList(new LoadDescription<>("Name of Music Band", musicBandBuilder::setName, null, String.class),
                new CoordinatesDescription(musicBandBuilder::setCoordinates),
                new LoadDescription<LocalDate>("Creation Date",musicBandBuilder::setCreationDate, null, LocalDate.class),
                new LoadDescription<Long>("Number of participants", musicBandBuilder::setNumberOfParticipants, null, Long.class),
                new AlbumDescription(musicBandBuilder::setBestAlbum),
                new LoadDescription<MusicGenre>("Genre", musicBandBuilder::setGenre, null, MusicGenre.class)));
    }

    public MusicBandDescription(SerialFunction<MusicBand, Object> fieldSetter) {
        super("Music Band", fieldSetter, MusicBand.class);
    }

    public MusicBandDescription() {
        super("Music Band",
                (SerialFunction<MusicBand, ?>) null,
                MusicBand.class);
    }


}
