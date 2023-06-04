package com.example.a302_java_application;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity implements RecyclerListInterface {

    private ArrayList<Album> allAlbums = new ArrayList<>();
    private ArrayList<Album> favouriteAlbums = new ArrayList<>();
    private TextView emptyText;
    private ImageView emptyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // Create DataProvider to get albums
        DataProvider dataProvider = new DataProvider(this);
        // Get list of all albums and save to attribute
        this.allAlbums = dataProvider.getAlbums();

        // Update list of all albums for DataProvider class
        dataProvider.updateAlbumList(this.allAlbums);

        favouriteAlbums = DataProvider.getFavouriteAlbums();

        // Set message if the favourite list is empty
        emptyText = findViewById(R.id.empty_text);
        emptyImage = findViewById(R.id.empty_image);
        if (favouriteAlbums.size()==0) {
            emptyText.setVisibility(View.VISIBLE);
            emptyImage.setVisibility(View.VISIBLE);
        } else {
            emptyText.setVisibility(View.INVISIBLE);
            emptyImage.setVisibility(View.INVISIBLE);
        }

        // Set up the bottom navigation bar
        setUpBottomNavBar();

//        Set up the recycler view for list of favourite albums
        setUpRecycler();
    }

    public void setUpRecycler() {

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
        bottomNavigationView.setSelectedItemId(R.id.bottom_favourites);

        // Open required activities when items clicked
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                // Create new intent
                Intent intent = new Intent(FavouritesActivity.this, MainActivity.class);
                // Open main activity
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;

            } else if (item.getItemId() == R.id.bottom_browse) {
                // Create new intent
                Intent intent = new Intent(FavouritesActivity.this, BrowseActivity.class);
                // Open Browse Activity
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;

            } else {
//                Refresh list of favourite albums
                setUpRecycler();
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
        intent.putExtra("detailImages", favouriteAlbums.get(position).getDetailImage());


        // Switch activity
        startActivity(intent);
    }
}
