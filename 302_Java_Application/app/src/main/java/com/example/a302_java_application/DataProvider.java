package com.example.a302_java_application;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.Random;


public class DataProvider {

    private Context context;
    static ArrayList<Album> allAlbums;


    public DataProvider(Context context) {

        this.context = context;
    }

    public ArrayList<Album> getAlbums() {

        ArrayList<Album> albumList = new ArrayList<Album>();
        String[] names = context.getResources().getStringArray(R.array.album_names);
        String[] artists = context.getResources().getStringArray(R.array.album_artist);
        String[] categories = context.getResources().getStringArray(R.array.album_category);
        String[] dates = context.getResources().getStringArray(R.array.album_release_date);
//        int[] imageNames = context.getResources().getIntArray(R.array.album_image);
        int[] imageNames = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground};
        String[] descriptions = context.getResources().getStringArray(R.array.album_description);

        for (int i = 0; i < names.length; i++) {

//            Generate random number of views for album item
            Random rand = new Random();
            int randView = rand.nextInt(20);

//            Create and add album item to list of albums
            Album newAlbum = new Album(names[i], categories[i], artists[i], descriptions[i],
                    imageNames[i], dates[i], false, randView);
            albumList.add(newAlbum);
        }

//        Return list of albums
        return albumList;
    }


//    Update static list of all albums for ease of use for all activity classes
    public static void updateAlbumList(ArrayList<Album> albumList) {
        allAlbums = albumList;
    }

}
