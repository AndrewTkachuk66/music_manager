import dto.Album;
import dto.Artist;

import java.time.Year;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class MusicManager {

    private MusicStore musicStore;

    public MusicManager(MusicStore musicStore) {
        this.musicStore = musicStore;
    }

    public Set<Album> getAlbums() {
        return musicStore.getAlbums();
    }

    public void addAlbum(Album album) {
        musicStore.getAlbums().add(album);
    }

    public Set<String> getTracksByAlbum(String albumName) {
        Set<String> tracks = new HashSet<>();
        Set<Album> albums = musicStore.getAlbums();
        for (Album currentAlbum : albums) {
            if (currentAlbum.getName().equals(albumName)) {
                tracks = currentAlbum.getTracks();
            }
        }
        if (tracks.isEmpty())
            throw new NoSuchElementException("No album with such name -" + albumName);
        return tracks;
    }

    public Set<Album> getAlbumsByYears(Year year) {
        Set<Album> albumsOfNeedYear = new HashSet<>();
        Set<Album> albums = musicStore.getAlbums();
        for (Album currentAlbum : albums) {
            if (currentAlbum.getYear().equals(year)) {
                albumsOfNeedYear.add(currentAlbum);
            }
        }
        if (albumsOfNeedYear.isEmpty())
            throw new NoSuchElementException("No album with such year -" + year);
        return albumsOfNeedYear;
    }

    public Set<String> getTracksByAuthor(Artist artist) {
        Set<Album> albums = musicStore.getAlbums();
        Set<String> tracksByArtist = new HashSet<>();
        for (Album currentAlbum : albums) {
            if (currentAlbum.getAuthor().equals(artist)) {
                tracksByArtist = currentAlbum.getTracks();
            }
        }
        if (tracksByArtist.isEmpty())
            throw new NoSuchElementException("No album with such author -" + artist);
        return tracksByArtist;
    }

    public Set<Album> getAlbumsByTrack(String track) {
        Set<Album> albums = musicStore.getAlbums();
        Set<Album> albumsByTrack = new HashSet<>();
        for (Album currentAlbum : albums) {
            Set<String> tracksOfCurrentAlbum = currentAlbum.getTracks();
            for (String currentTrack : tracksOfCurrentAlbum) {
                if (currentTrack.equals(track)) {
                    albumsByTrack.add(currentAlbum);
                }
            }
        }
        if (albumsByTrack.isEmpty())
            throw new NoSuchElementException("No album with such track -" + track);
        return albumsByTrack;
    }
}

