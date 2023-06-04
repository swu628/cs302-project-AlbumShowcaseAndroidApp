package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class BrowseActivity extends AppCompatActivity implements RecyclerListInterface {
    private SearchView browseSearch;
    private int pos;
    private boolean[] favourite;

    private DataProvider dataProvider = new DataProvider(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        if (DataProvider.searchedAlbums != null) {
            // Set up recycler view for list of search history
            setUpHistoryRecycler();
        }

        if (DataProvider.viewedAlbums != null) {
//            Set up recycler for list of viewed albums
            setUpViewedRecycler();
        }
        // Set up the bottom navigation bar
        setUpBottomNavBar();

        ImageView delete = findViewById(R.id.delete_image);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Clear search history
                DataProvider.clearSearched();
                setUpHistoryRecycler();
                DataProvider.clearViewed();
                setUpViewedRecycler();
            }
        });

        // Set up search view for browse activity
        browseSearch = findViewById(R.id.browse_search_bar);
        browseSearch.clearFocus();

        // Set up search function
        browseSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                browseSearch.setIconified(true);
                dataProvider.updateSearched(query);

                // Create new intent
                Intent intent = new Intent(BrowseActivity.this, ListActivity.class);
                // Add extras to intent to pass search query to ListActivity
                intent.putExtra("buttonClicked", "Search Result");
                intent.putExtra("query", query);
                // Open search results
                startActivity(intent);

                setUpHistoryRecycler();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

    }

    public void setUpHistoryRecycler() {

        // Create instance for recycler view
        RecyclerView recyclerView = findViewById(R.id.search_history_recycler);
        // Create instance for adapter for recycler view
        SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(dataProvider.getSearchedAlbums(), this);
        // Set adapter and layout manager for the recycler view
        recyclerView.setAdapter(searchHistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void setUpViewedRecycler() {
        //        Create instance for recycler view
        RecyclerView recyclerList = findViewById(R.id.view_history_recycler);
//        Create instance for adapter for recycler view
        ArrayList<Album> displayAlbums = dataProvider.findFavourites(DataProvider.viewedAlbums);
        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(displayAlbums, this, this);
//        Set adapter and layout manager for the recycler view
        recyclerList.setAdapter(recyclerListAdapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpBottomNavBar() {
        // Set up bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.browse_bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.bottom_browse);

        // Open required activities when items clicked
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                // Open main activity
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;

            } else if (item.getItemId() == R.id.bottom_browse) {
                return true;

            } else {
                // Create new intent
                Intent intent = new Intent(BrowseActivity.this, FavouritesActivity.class);

                // Pass the favourite album to browse activity
                intent.putExtra("favourite", favourite);
                intent.putExtra("pos", pos);

                // Open favourite activity
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        // Initialise intent
        Intent intent = new Intent(this, DetailsActivity.class);

        // Pass the variables to another activity

        intent.putExtra("name", DataProvider.viewedAlbums.get(position).getName());
        intent.putExtra("artist", DataProvider.viewedAlbums.get(position).getArtist());
        intent.putExtra("image", DataProvider.viewedAlbums.get(position).getImage());
        intent.putExtra("releaseDate", DataProvider.viewedAlbums.get(position).getReleaseDate());
        intent.putExtra("description", DataProvider.viewedAlbums.get(position).getDescription());
        intent.putExtra("tracklist", DataProvider.viewedAlbums.get(position).getTracklist());
        intent.putExtra("contain", DataProvider.viewedAlbums.get(position).getContain());
        intent.putExtra("detailImages", DataProvider.viewedAlbums.get(position).getDetailImage());

        // Switch activity
        startActivity(intent);
    }
}