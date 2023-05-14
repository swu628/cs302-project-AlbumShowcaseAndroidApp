package com.example.a302_java_application;
import java.text.SimpleDateFormat;

// Class which holds the album item data
public abstract class Album {

    private String name;
    private String category;
    private String artist;
    private String description;

    private SimpleDateFormat releaseDate;
    private boolean liked;

    public Album(String name, String category, String artist, String description, SimpleDateFormat releaseDate, boolean liked) {
        this.name = name;
        this.category = category;
        this.artist = artist;
        this.description = description;
        this.releaseDate = releaseDate;
        this.liked = liked;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate.toString();
    }

    public boolean isLiked() {
        return liked;
    }
}
