package com.example.a302_java_application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class DataProvider {

    private ArrayList<String> names = new ArrayList<String>(Arrays.asList("New Jeans 1st EP 'New " +
            "Jeans'", "THE ALBUM", "I NEVER DIE", "Love War", "BORN PINK"));
    private ArrayList<String> artists = new ArrayList<String>(Arrays.asList("NewJeans", "BLACKPINK",
            "(G)I-DLE", "YENA", "BLACKPINK"));
    private ArrayList<String> categories = new ArrayList<String>(Arrays.asList("Girl Group", "Girl Group",
            "Girl Group", "Girl Group", "Girl Group"));
    private ArrayList<String> descriptions = new ArrayList<String>(Arrays.asList("Description", "Description",
            "Description", "Description", "Description"));

    private ArrayList<String> images = new ArrayList<String>(Arrays.asList("image", "image", "image",
            "image", "image"));
    private ArrayList<String> releaseDates = new ArrayList<String>(Arrays.asList("01/08/22", "02/10/20", "12/03/22",
            "17/01/23", "16/09/22"));
    private boolean[] favourites = {false, false, false, false, false};

    public ArrayList<Album> getAlbums() {
        ArrayList<Album> albumList = new ArrayList<Album>();

        for (int i = 0; i < names.size(); i++) {
            Album newAlbum = new Album(names.get(i), categories.get(i),artists.get(i),
                    descriptions.get(i), images.get(i), releaseDates.get(i), favourites[i]);
            albumList.add(newAlbum);
        }

        return albumList;
    }
}
