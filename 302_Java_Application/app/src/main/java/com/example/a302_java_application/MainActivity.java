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

//      Get the list of most viewed albums
        this.mostViewed = getMostViewed();

//      Create DataProvider to get albums
        DataProvider dataProvider = new DataProvider(context);
//      Get list of all albums and save to attribute
        this.allAlbums = dataProvider.getAlbums();



    }

    public ArrayList<Album> getMostViewed() {

        ArrayList<Album> mostViewed = new ArrayList<>();


        return mostViewed;
    }
}