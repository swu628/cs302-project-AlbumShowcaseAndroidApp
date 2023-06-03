package com.example.a302_java_application;

// Class which holds the album item data
public class Album {

    private String name;
    private String category;
    private String artist;
    private String description;
    private String tracklist;
    private String contain;
    private int image;
    private String releaseDate;
    private boolean liked;
    private int views;

    private int detailImage;

    public Album(String name, String category, String artist, String description, String tracklist,
                 String contain, int image, String releaseDate, boolean liked, int views) {
                 //int detailImage) {
        this.name = name;
        this.category = category;
        this.artist = artist;
        this.description = description;
        this.tracklist = tracklist;
        this.contain = contain;
        this.image = image;
        this.releaseDate = releaseDate;
        this.liked = liked;
        this.views = views;
    }

    public Album(int detailImage) {
        this.detailImage = detailImage;
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
    public String getTracklist() {
        return tracklist;
    }
    public String getContain() {
        return contain;
    }

    public int getImage() { return image; }

    // public int getDetailImage() {return detailImage;}

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getDetailImage() {return detailImage;};

    public boolean isLiked() {
        return liked;
    }

    public void setLikedToTrue() {this.liked=true;}

    public void setLikedToFalse() {this.liked=false;}

    public int getViews() { return views; }

    public void updateViews() {this.views++;}
}
