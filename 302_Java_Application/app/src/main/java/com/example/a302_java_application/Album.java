package com.example.a302_java_application;


// Class which holds the album item data
public class Album {

    private String name;
    private String category;
    private String artist;
    private String description;
    private boolean liked;

    public Album(String name, String category, String artist, String description, boolean liked) {
        this.name = name;
        this.category = category;
        this.artist = artist;
        this.description = description;
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

    public boolean isLiked() {
        return liked;
    }
}
