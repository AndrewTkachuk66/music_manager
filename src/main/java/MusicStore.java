import dto.Album;
import java.util.HashSet;
import java.util.Set;

public class MusicStore {
    private Set<Album> albums = new HashSet<>();

    public Set<Album> getAlbums() {
        return albums;
    }
}
