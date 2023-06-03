package com.example.a302_java_application;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity implements RecyclerListInterface {

    private ArrayList<Album> allAlbums = new ArrayList<>();
    private ArrayList<Album> favouriteAlbums = new ArrayList<>();
    private int pos;
    private boolean[] favourite = new boolean[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);


        // Create DataProvider to get albums
        DataProvider dataProvider = new DataProvider(this);
        // Get list of all albums and save to attribute
        this.allAlbums = dataProvider.getAlbums();

        // Update list of all albums for DataProvider class
        dataProvider.updateAlbumList(this.allAlbums);

        favourite = getIntent().getBooleanArrayExtra("favourite");
        pos = getIntent().getIntExtra("pos", 0);

        // Get a list of favourite albums
        int i;
        for (i = 0; i < 10; i++) {
            if (favourite[i]==true) {
                allAlbums.get(i+pos).setLikedToTrue();
                favouriteAlbums.add(allAlbums.get(i+pos));
            }
        }

        // Set up the bottom navigation bar
        setUpBottomNavBar();

        // Create instance for recycler view
        RecyclerView recyclerList = findViewById(R.id.favourites_recycler);
        // Create instance for adapter for recycler view
        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(this.favouriteAlbums, this, this);
        // Set adapter and layout manager for the recycler view
        recyclerList.setAdapter(recyclerListAdapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpBottomNavBar() {
        // Set up bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar);

        // Open required activities when items clicked
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                // Create new intent
                Intent intent = new Intent(FavouritesActivity.this, MainActivity.class);
                // Pass the favourite album to browse activity
                intent.putExtra("favourite", favourite);
                intent.putExtra("pos", pos);
                // Open main activity
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;

            } else if (item.getItemId() == R.id.bottom_browse) {
                // Create new intent
                Intent intent = new Intent(FavouritesActivity.this, BrowseActivity.class);
                // Pass the favourite album to browse activity
                intent.putExtra("favourite", favourite);
                intent.putExtra("pos", pos);
                // Open Browse Activity
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;

            } else {
                return true;
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);

        // Pass the variables to another activity
        intent.putExtra("name", favouriteAlbums.get(position).getName());
        intent.putExtra("artist", favouriteAlbums.get(position).getArtist());
        intent.putExtra("image", favouriteAlbums.get(position).getImage());
        intent.putExtra("releaseDate", favouriteAlbums.get(position).getReleaseDate());
        intent.putExtra("description", favouriteAlbums.get(position).getDescription());
        intent.putExtra("tracklist", favouriteAlbums.get(position).getTracklist());
        intent.putExtra("contain", favouriteAlbums.get(position).getContain());

        // Get the position of the album
        int p = 0;
        for (int i=0; i<30; i++) {
            for (int j=0; j<30; j++) {
                if (favouriteAlbums.get(position).getName().equals(allAlbums.get(j).getName())) {
                    p=j;
                }
            }
        }
        intent.putExtra("position", p);

        // Switch activity
        startActivity(intent);
    }
}
