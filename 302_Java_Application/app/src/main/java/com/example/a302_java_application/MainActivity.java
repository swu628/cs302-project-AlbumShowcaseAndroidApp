package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Album> allAlbums = new ArrayList<>();
    ArrayList<Album> mostViewed = new ArrayList<>();
    Context context = getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Create DataProvider to get albums
        DataProvider dataProvider = new DataProvider(context);
//        Get list of all albums and save to attribute
        this.allAlbums = dataProvider.getAlbums();

//        Update list of all albums for DataProvider class 
        dataProvider.updateAlbumList(this.allAlbums);

//        Get the list of most viewed albums
        this.mostViewed = getMostViewed();



    }

    public ArrayList<Album> getMostViewed() {

        ArrayList<Album> mostViewed = new ArrayList<>();
        ArrayList<Album> albumList = allAlbums;

        for (int i = 0; i < 3; i++) {

        }

        return mostViewed;
    }
}