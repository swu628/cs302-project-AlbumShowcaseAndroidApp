package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity {

//    private static ArrayList<String> searchHistory = new ArrayList<>();
    private static ArrayList<String> searchHistory = new ArrayList<>();

    private SearchView browseSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

//        Set up search view for browse activity
        browseSearch = findViewById(R.id.browse_search_bar);
        browseSearch.clearFocus();

//        Test recycler by adding some searches
        updateHistory("XOXO");
        updateHistory("Palette");

//        Create instance for recycler view
        RecyclerView recyclerView = findViewById(R.id.search_history_recycler);
//        Create instance for adapter for recycler view
        SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(this.searchHistory, this);
//        Set adapter and layout manager for the recycler view
        recyclerView.setAdapter(searchHistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public static ArrayList<String> getSearchHistory() {

        return searchHistory;
    }

    public static void updateHistory(String album) {

//        Add album to search history
        searchHistory.add(album);
    }

}