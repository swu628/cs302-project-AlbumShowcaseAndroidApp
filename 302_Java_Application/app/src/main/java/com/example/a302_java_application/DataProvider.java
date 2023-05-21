package com.example.a302_java_application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataProvider {

    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> artists = new ArrayList<String>();
    private ArrayList<String> categories = new ArrayList<String>();
    private ArrayList<String> descriptions = new ArrayList<String>();
    private ArrayList<SimpleDateFormat> releaseDates = new ArrayList<SimpleDateFormat>();
    private boolean[] favourites;

    public ArrayList<Album> getAlbums() {
        ArrayList<Album> albumList = new ArrayList<Album>();

        for (int i = 0; i < names.size(); i++) {
            Album newAlbum = new Album(names.get(i), categories.get(i),artists.get(i),
                    descriptions.get(i),releaseDates.get(i), favourites[i]);
            albumList.add(newAlbum);
        }

        return albumList;
    }
}
