package com.example.a302_java_application;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;


public class DataProvider {

    private Context context;
    static ArrayList<Album> allAlbums = new ArrayList<>();

    static ArrayList<String> searchedAlbums = new ArrayList<>();

    static ArrayList<Album> viewedAlbums = new ArrayList<>();

    static ArrayList<Album> favouriteAlbums = new ArrayList<>();

    int[] imageNames = {R.drawable.new_jeans, R.drawable.the_album,
            R.drawable.i_never_die, R.drawable.icy,
            R.drawable.born_pink, R.drawable.shooting_star, R.drawable.bad_boy,
            R.drawable.psycho, R.drawable.next_level, R.drawable.ive, R.drawable.seventeen_mini,
            R.drawable.candy, R.drawable.love_shot, R.drawable.hello_future, R.drawable.cherry_bomb,
            R.drawable.treasure, R.drawable.seventeen_album, R.drawable.hot_sauce,
            R.drawable.oddinary, R.drawable.xoxo, R.drawable.r, R.drawable.solo, R.drawable.me,
            R.drawable.lalisa, R.drawable.palette, R.drawable.birthday, R.drawable.the_weekend,
            R.drawable.delight, R.drawable.love_poem, R.drawable.love_war};

    int[][]detailImage = {{R.drawable.new_jeans_1, R.drawable.new_jeans_2, R.drawable.new_jeans_3},
        {R.drawable.the_album_1, R.drawable.the_album_2, R.drawable.the_album_3},
        {R.drawable.i_never_die_1, R.drawable.i_never_die_2, R.drawable.i_never_die_3},
        {R.drawable.icy_1, R.drawable.icy_2, R.drawable.icy_3},
        {R.drawable.born_pink_1, R.drawable.born_pink_2, R.drawable.born_pink_3},
        {R.drawable.shooting_star_1, R.drawable.shooting_star_2, R.drawable.shooting_star_3},
        {R.drawable.bad_boy_1, R.drawable.bad_boy_2, R.drawable.bad_boy_3},
        {R.drawable.psycho_1, R.drawable.psycho_2, R.drawable.psycho_3},
        {R.drawable.next_level_1, R.drawable.next_level_2, R.drawable.next_level_3},
        {R.drawable.ive_1, R.drawable.ive_2, R.drawable.ive_3},

        {R.drawable.seventeen_mini_1, R.drawable.seventeen_mini_2, R.drawable.seventeen_mini_3},
        {R.drawable.candy_1, R.drawable.candy_2, R.drawable.candy_3},
        {R.drawable.love_shot_1, R.drawable.love_shot_2, R.drawable.love_shot_3},
        {R.drawable.hello_future_1, R.drawable.hello_future_2, R.drawable.hello_future_3},
        {R.drawable.cherry_bomb_1, R.drawable.cherry_bomb_2, R.drawable.cherry_bomb_3},
        {R.drawable.treasure_1, R.drawable.treasure_2, R.drawable.treasure_3},
        {R.drawable.seventeen_album_1, R.drawable.seventeen_album_2, R.drawable.seventeen_album_3},
        {R.drawable.hot_sauce_1, R.drawable.hot_sauce_2, R.drawable.hot_sauce_3},
        {R.drawable.oddinary_1, R.drawable.oddinary_2, R.drawable.oddinary_3},
        {R.drawable.xoxo_1, R.drawable.xoxo_2, R.drawable.xoxo_3},

        {R.drawable.r_1, R.drawable.r_2, R.drawable.r_3},
        {R.drawable.solo_1, R.drawable.solo_2, R.drawable.solo_3},
        {R.drawable.me_1, R.drawable.me_2, R.drawable.me_3},
        {R.drawable.lalisa_1, R.drawable.lalisa_2, R.drawable.lalisa_3},
        {R.drawable.palette_1, R.drawable.palette_2, R.drawable.palette_3},
        {R.drawable.birthday_1, R.drawable.birthday_2, R.drawable.birthday_3},
        {R.drawable.the_weekend_1, R.drawable.the_weekend_2, R.drawable.the_weekend_3},
        {R.drawable.delight_1, R.drawable.delight_2, R.drawable.delight_3},
        {R.drawable.love_poem_1, R.drawable.love_poem_2, R.drawable.love_poem_3},
        {R.drawable.love_war_1, R.drawable.love_war_2, R.drawable.love_war_3}};

    public DataProvider(Context context) {

        this.context = context;
    }

    public ArrayList<Album> getAlbums() {

        ArrayList<Album> albumList = new ArrayList<Album>();
        String[] names = context.getResources().getStringArray(R.array.album_names);
        String[] artists = context.getResources().getStringArray(R.array.album_artist);
        String[] categories = context.getResources().getStringArray(R.array.album_category);
        String[] dates = context.getResources().getStringArray(R.array.album_release_date);
        String[] descriptions = context.getResources().getStringArray(R.array.album_description);
        String[] tracklists = context.getResources().getStringArray(R.array.album_tracklist);
        String[] contains = context.getResources().getStringArray(R.array.album_contain);

        for (int i = 0; i < names.length; i++) {

//            Generate random number of views for album item
            Random rand = new Random();
            int randView = rand.nextInt(20);
            int[] detailImages = detailImage[i];

//            Create and add album item to list of albums
            Album newAlbum = new Album(names[i], categories[i], artists[i], descriptions[i], tracklists[i],
                    contains[i], imageNames[i], dates[i], false, randView, detailImages);
            albumList.add(newAlbum);
        }

//        Return list of albums
        return albumList;
    }
    public static ArrayList<String> getSearchedAlbums() {return searchedAlbums;}

    public static ArrayList<Album> getFavouriteAlbums() {return favouriteAlbums;}

    public static void clearSearched() {
        searchedAlbums = new ArrayList<>();
    }

    public static void clearViewed() {
        viewedAlbums = new ArrayList<>();
    }
    public static void clearFavourites() {
        favouriteAlbums = new ArrayList<>();
    }

    public static void updateSearched(String album) {
        searchedAlbums.add(album);
    }

    public static void updateViewed(Album album) {

        for (int i = 0; i < viewedAlbums.size(); i++) {
            if (viewedAlbums.get(i).getName().equals(album.getName())) {
                return;
            }
        }
        viewedAlbums.add(album);
    }

    public static void updateFavourites(Album album, boolean add) {
        if (add) {

            for (int i = 0; i < favouriteAlbums.size(); i++) {
                if (favouriteAlbums.get(i).getName().equals(album.getName())) {
                    return;
                }
            }
            favouriteAlbums.add(album);
        } else if (!add) {
            for (int i = 0; i < favouriteAlbums.size(); i++) {
                if (favouriteAlbums.get(i).getName().equals(album.getName())) {
                    favouriteAlbums.remove(favouriteAlbums.get(i));
                }
            }
        }
    }

    public ArrayList<Album> findFavourites(ArrayList<Album> albums) {

        for (int i = 0; i < favouriteAlbums.size(); i++) {
            for (int j = 0; j < albums.size(); j++) {
                if (favouriteAlbums.get(i).getName().equals(albums.get(j).getName())) {
                    albums.get(j).setLikedToTrue();
                }
            }
        }

        return albums;
    }

//    Update static list of all albums for ease of use for all activity classes
    public static void updateAlbumList(ArrayList<Album> albumList) {
        allAlbums = albumList;
    }

}
