package dto;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class Album {
    private String name;
    private Year year;
    private Artist artist;
    private Set<String> tracks = new HashSet<>();

    public Album(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public Set<String> getTracks() {
        return tracks;
    }

    public void setTracks(Set<String> tracks) {
        this.tracks = tracks;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Artist getAuthor() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
