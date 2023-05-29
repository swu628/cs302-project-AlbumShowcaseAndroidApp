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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

//        Set up search view for browse activity
//        browseSearch = findViewById(R.id.browse_search_bar);
//        browseSearch.clearFocus();

//        Test recycler by adding some searches
        updateHistory("XOXO");
        updateHistory("Palette");

//        Set up recycler view for list of search history
        setUpRecycler();

//        Set up the bottom navigation bar
        setUpBottomNavBar();

    }

    public static ArrayList<String> getSearchHistory() {

        return searchHistory;
    }

    public void setUpRecycler() {

//        Create instance for recycler view
        RecyclerView recyclerView = findViewById(R.id.search_history_recycler);
//        Create instance for adapter for recycler view
        SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(searchHistory, this);
//        Set adapter and layout manager for the recycler view
        recyclerView.setAdapter(searchHistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void setUpBottomNavBar() {

//        Set up bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.browse_bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.bottom_browse);

//        Open required activties when items clicked
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_browse) {
                return true;
            } else {
                return false;
            }

        });
    }

    public static void updateHistory(String album) {

//        Add album to search history
        searchHistory.add(album);
    }

    public void setupRecyclerView() {

//        Create instance for recycler view
        RecyclerView recyclerView = findViewById(R.id.search_history_recycler);
//        Create instance for adapter for recycler view
        SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(searchHistory, this);
//        Set adapter and layout manager for the recycler view
        recyclerView.setAdapter(searchHistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void setupBottomNavBar() {

        //        Set up bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.browse_bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.bottom_browse);

//        Define what happens when an menu item is clicked
        bottomNavigationView.setOnItemSelectedListener(item -> {

//            Start respective activities when different items are clicked
            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_browse) {
                return true;
            } else {
                return false;
            }

        });
    }

}