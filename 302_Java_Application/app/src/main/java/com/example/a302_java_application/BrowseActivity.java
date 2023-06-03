package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class BrowseActivity extends AppCompatActivity {

    public static ArrayList<String> searchHistory = new ArrayList<>();
    private SearchView browseSearch;
    private int pos;
    private boolean[] favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        // Recieve any search history from other activities
        Intent intent = getIntent();
        if (intent.getStringArrayListExtra("searched") != null) {
            receiveSearched(intent.getStringArrayListExtra("searched"));
        } else if (intent.getStringExtra("history") != null) {
            updateHistory(intent.getStringExtra("history"));
        }

        // Set up search view for browse activity
        browseSearch = findViewById(R.id.browse_search_bar);
        browseSearch.clearFocus();

        // Get a list of favourite albums
        favourite = getIntent().getBooleanArrayExtra("favourite");
        if (favourite==null) {
            favourite = new boolean[30];
            for (int i=0; i<30; i++) {
                favourite[i] = false;
            }
        }
        pos = getIntent().getIntExtra("pos", 0);

        // Set up search function
        browseSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                // Add the current query to the search history
                updateHistory(query);
                setUpRecycler();

                // Create new intent
                Intent intent = new Intent(BrowseActivity.this, ListActivity.class);
                // Add extras to intent to pass search query to ListActivity
                intent.putExtra("buttonClicked", "Search Result");
                intent.putExtra("query", query);
                // Open search results
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        //// Test recycler by adding some searches
        // updateHistory("XOXO");
        // updateHistory("Palette");

        // Set up recycler view for list of search history
        setUpRecycler();

        // Set up the bottom navigation bar
        setUpBottomNavBar();

    }

    public static ArrayList<String> getSearchHistory() {

        return searchHistory;
    }

    public void setUpRecycler() {

        // Create instance for recycler view
        RecyclerView recyclerView = findViewById(R.id.search_history_recycler);
        // Create instance for adapter for recycler view
        SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(searchHistory, this);
        // Set adapter and layout manager for the recycler view
        recyclerView.setAdapter(searchHistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

    public static void receiveSearched(ArrayList<String> searched) {

        try {
            for (int i = 0; i < searched.size(); i++) {
                updateHistory(searched.get(i));
            }
        } catch (Exception e) {
        }
    }

    public static void updateHistory(String search) {

        // Add album to search history
        searchHistory.add(search);
    }

}