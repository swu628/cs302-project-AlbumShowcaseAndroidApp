package com.example.a302_java_application;

// Class which holds the album item data
public class Album {

    private String name;
    private String category;
    private String artist;
    private String description;
    private int image;
    private String releaseDate;
    private boolean liked;
    private int views;

    public Album(String name, String category, String artist, String description, int image,
                 String releaseDate, boolean liked, int views) {
        this.name = name;
        this.category = category;
        this.artist = artist;
        this.description = description;
        this.image = image;
        this.releaseDate = releaseDate;
        this.liked = liked;
        this.views = views;
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

    public int getImage() { return image; }

    public String getReleaseDate() {
        return releaseDate;
    }

    public boolean isLiked() {
        return liked;
    }

    public int getViews() { return views; }

    public void updateViews() {this.views++;}
}
