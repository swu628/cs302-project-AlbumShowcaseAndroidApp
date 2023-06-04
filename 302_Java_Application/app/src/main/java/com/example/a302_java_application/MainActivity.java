package com.example.a302_java_application;

import static java.util.Arrays.fill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements RecyclerListInterface {

    private ArrayList<Album> allAlbums = new ArrayList<>();
    private ArrayList<Album> mostViewed = new ArrayList<>();
    private DataProvider dataProvider = new DataProvider(this);
    private SearchView searchView;
    private Button girlGroup;
    private Button boyGroup;
    private Button soloist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

//        Get list of all albums and save to attribute
        allAlbums = dataProvider.getAlbums();

//        Update list of all albums for DataProvider class
        dataProvider.updateAlbumList(dataProvider.getAlbums());

//        Get the list of most viewed albums
        mostViewed = getMostViewed();

//        Set up recycler view for the most viewed albums
        setUpRecycler();

        girlGroup = findViewById(R.id.button1);
        boyGroup = findViewById(R.id.button2);
        soloist = findViewById(R.id.button3);

        // Connect category buttons to list activity (using for loops to avoid duplicated code)
        int i;
        for (i=0; i<3; i++) {
            Button temp;
            if (i==0) {
                temp = girlGroup;
            } else if (i==1) {
                temp = boyGroup;
            } else {
                temp = soloist;
            }
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                // Open list activity when the category button is clicked and pass the text appear on
                // the clicked button
                public void onClick(View v) {
                    String buttonClicked = temp.getText().toString();
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("buttonClicked", buttonClicked);
                    startActivity(intent);
                }
            });
        }

//        Set up Search View
          searchView = findViewById(R.id.main_search_bar);
          searchView.clearFocus();

          searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
              @Override
              public boolean onQueryTextSubmit(String query) {

                  dataProvider.updateSearched(query);

                  searchView.setIconified(true);
//                  Add query to search history
//                  Create new intent
                  Intent intent = new Intent(MainActivity.this, ListActivity.class);
//                  Add extra to pass query to ListActivity
                  intent.putExtra("buttonClicked", "Search Result");
                  intent.putExtra("query", query);
//                  Open search results
                  startActivity(intent);
                  return true;
              }

              @Override
              public boolean onQueryTextChange(String newText) {
                  return false;
              }
          });

//        Set up the bottom navigation bar
        setUpBottomNavBar();
    }

    public void setUpRecycler() {

//        Create instance for recycler view
        RecyclerView recyclerView = findViewById(R.id.most_views_recycler);
//        Create instance for adapter for recycler view
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this.mostViewed, this, this);
//        Set adapter and layout manager for the recycler view
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    public void setUpBottomNavBar() {
        // Set up bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        // Open required activities when items clicked
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                return true;

            } else if (item.getItemId() == R.id.bottom_browse) {
                // Create new intent
                Intent intent = new Intent(MainActivity.this, BrowseActivity.class);

                // Open BrowseActivity
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;

            } else {
                // Create new intent
                Intent intent = new Intent(MainActivity.this, FavouritesActivity.class);

                // Open favourite activity
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
        });
    }

//    Gets the list of most viewed albums from the list of all albums
    public ArrayList<Album> getMostViewed() {

//        Initialise list of most viewed albums and list of all albums
        ArrayList<Album> mostViewed = new ArrayList<>();
        ArrayList<Album> albumList = this.allAlbums;

//        Initialise variable to store total number of albums and list of views for all albums
        int nAlbums = albumList.size();
        int[] views = new int[nAlbums];

//        Get a list of views of the albums
        for (int i = 0; i < nAlbums; i++) {
            views[i] = albumList.get(i).getViews();
        }

//        Sort views from least to most
        Arrays.sort(views);

//        Find the albums with the most views and add to the list of most viewed albums
        for (int j = 0; j < 3; j++) {
            int mostView = views[views.length - 1 - j];
            for (int k = 0; k < albumList.size(); k++) {
                Album currentAlbum = albumList.get(k);
                if (currentAlbum.getViews() == mostView) {
                    mostViewed.add(currentAlbum);
                    albumList.remove(k);
                    break;
                }
            }
        }

        return mostViewed;
    }

    @Override
    public void onItemClick(int position) {

        // Initialise intent
        Intent intent = new Intent(this, DetailsActivity.class);

        // Pass the variables to another activity
        intent.putExtra("name", mostViewed.get(position).getName());
        intent.putExtra("artist", mostViewed.get(position).getArtist());
        intent.putExtra("image", mostViewed.get(position).getImage());
        intent.putExtra("releaseDate", mostViewed.get(position).getReleaseDate());
        intent.putExtra("description", mostViewed.get(position).getDescription());
        intent.putExtra("description", mostViewed.get(position).getDescription());
        intent.putExtra("tracklist", mostViewed.get(position).getTracklist());
        intent.putExtra("contain", mostViewed.get(position).getContain());
        intent.putExtra("detailImages", mostViewed.get(position).getDetailImage());

        // Switch activity
        startActivity(intent);

    }
}