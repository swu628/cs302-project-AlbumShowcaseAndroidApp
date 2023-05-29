package com.example.a302_java_application;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;


public class DataProvider {

    private Context context;
    static ArrayList<Album> allAlbums;

    int[] imageNames = {R.drawable.new_jeans, R.drawable.the_album,
            R.drawable.i_never_die, R.drawable.icy,
            R.drawable.born_pink, R.drawable.shooting_star, R.drawable.bad_boy,
            R.drawable.psycho, R.drawable.next_level, R.drawable.ive, R.drawable.seventeen_mini,
            R.drawable.candy, R.drawable.love_shot, R.drawable.hello_future, R.drawable.cherry_bomb,
            R.drawable.treasure, R.drawable.seventeen_album, R.drawable.hot_sauce,
            R.drawable.oddinary, R.drawable.xoxo, R.drawable.r, R.drawable.solo, R.drawable.me,
            R.drawable.lalisa, R.drawable.palette, R.drawable.birthday, R.drawable.the_weekend,
            R.drawable.delight, R.drawable.love_poem, R.drawable.love_war};

    // int[] detailsImageNames = {R.drawable.new_jeans_1, R.drawable.new_jeans_2, R.drawable.new_jeans_3};

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
//        int[] imageNames = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
//                R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
//                R.drawable.ic_launcher_foreground};
        String[] descriptions = context.getResources().getStringArray(R.array.album_description);

        for (int i = 0; i < names.length; i++) {

//            Generate random number of views for album item
            Random rand = new Random();
            int randView = rand.nextInt(20);

//            Create and add album item to list of albums
            Album newAlbum = new Album(names[i], categories[i], artists[i], descriptions[i],
                    imageNames[i], dates[i], false, randView);
                    // detailsImageNames[i*3]);
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
