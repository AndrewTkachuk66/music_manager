import dto.Album;
import dto.Artist;
import org.junit.Test;

import java.time.Year;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Customer is a music store. It is needed to store data about artists, albums and tracks.
 * Albums can be 3 types: LP(10+ tracks), EP(2-4 tracks), Single(1 track).
 * Albums have issue date.
 * Track can be in more the one Album.
 * Task:
 * Create DTO classes
 * Create class MusicManager which has following methods:
 * getAlbums()
 * addAlbum(Album)
 * getTracksByAlbum(Album)
 * getAlbumsByYear(Year)
 * getTracksByAuthor(Artist)
 * getAlbumsByTrack(Track)
 * Cover MusicManager with tests
 **/

public class MusicManagerTest {
   private MusicStore musicStore = mock(MusicStore.class);
    private MusicManager musicManager = new MusicManager(musicStore);
    Set<Album> albums = new HashSet<>();
    Set<String> tracks = new HashSet<>();

    @Test
    public void getAlbumsShouldReturnSetOfAlbums() {
        Album album = new Album("Album");
        albums.add(album);
        when(musicStore.getAlbums()).thenReturn(albums);
        assertNotNull(musicManager.getAlbums());
    }

    @Test
    public void addAlbumMethodShouldAddAlbumToSetOfAlbums() {
        Album album = mock(Album.class);
        when(musicStore.getAlbums()).thenReturn(albums);
        musicManager.addAlbum(album);
        assertTrue(albums.contains(album));
    }

    @Test
    public void getTracksByAlbumShouldReturnSetOfTracks() {
        Album albumA = new Album("AlbumA");
        tracks.add("rack1fromAlbumA");
        albumA.setTracks(tracks);
        albums.add(albumA);
        when(musicStore.getAlbums()).thenReturn(albums);
        assertNotNull(musicManager.getTracksByAlbum("AlbumA"));
    }

    @Test(expected = NoSuchElementException.class)
    public void getTracksByAlbumShouldThrowNoSuchEX() {
        tracks.add("rack1fromAlbumA");
        Album albumA = new Album("AlbumB");
        albumA.setTracks(tracks);
        albums.add(albumA);
        when(musicStore.getAlbums()).thenReturn(albums);
        assertNotNull(musicManager.getTracksByAlbum("AlbumA"));
    }

    @Test
    public void getAlbumsByYearsShouldReturnSetOfAlbums() {
        Year year = Year.of(1955);
        Album albumA = new Album("AlbumB");
        albumA.setYear(year);
        albums.add(albumA);
        when(musicStore.getAlbums()).thenReturn(albums);
        assertNotNull(musicManager.getAlbumsByYears(year));
    }

    @Test(expected = NoSuchElementException.class)
    public void getAlbumsByYearsShouldThrowNoSuchEXIfNoAlbumWithCurrentYear() {
        Year year = Year.of(1955);
        Year anotherYear = Year.of(1966);
        Album albumA = new Album("AlbumB");
        albumA.setYear(anotherYear);
        albums.add(albumA);
        when(musicStore.getAlbums()).thenReturn(albums);
        assertNotNull(musicManager.getAlbumsByYears(year));
    }

    @Test
    public void getTracksByAuthorShouldReturnTracksOfAuthor() {
        tracks.add("rack1fromAlbumA");
        Artist artist = mock(Artist.class);
        Album albumA = new Album("AlbumA");
        albumA.setTracks(tracks);
        albumA.setArtist(artist);
        albums.add(albumA);
        when(musicStore.getAlbums()).thenReturn(albums);
        assertNotNull(musicManager.getTracksByAuthor(artist));
    }

    @Test(expected = NoSuchElementException.class)
    public void getTracksByAuthorShouldThrowNoSuchEXIfNoTracksInCurrentAuthor(){
        Artist artist = mock(Artist.class);
        Artist anotherArtist = mock(Artist.class);
        Album albumA = new Album("AlbumA");
        albumA.setArtist(artist);
        albums.add(albumA);
        when(musicStore.getAlbums()).thenReturn(albums);
        assertNotNull(musicManager.getTracksByAuthor(anotherArtist));
    }

    @Test
    public void getAlbumsByTrackShouldReturnSetOfAlbums(){
        Album albumA = new Album("AlbumA");
        tracks.add("Track1");
        albumA.setTracks(tracks);
        albums.add(albumA);
        when(musicStore.getAlbums()).thenReturn(albums);
        assertNotNull(musicManager.getAlbumsByTrack("Track1"));
    }

    @Test(expected = NoSuchElementException.class)
    public void  getAlbumsByTrackShouldShouldThrowNoSuchEXIfNoAlbumsByCurrentTrack(){
        Album albumA = new Album("AlbumA");
        tracks.add("Track1");
        albumA.setTracks(tracks);
        albums.add(albumA);
        when(musicStore.getAlbums()).thenReturn(albums);
        assertNotNull(musicManager.getAlbumsByTrack("Track2"));
    }
}
