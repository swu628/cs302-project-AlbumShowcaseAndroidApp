package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;

import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity {

    private static ArrayList<String> searchHistory = new ArrayList<>();

    private SearchView browseSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

//        Set up search view for browse activity
        browseSearch = findViewById(R.id.browse_search_bar);
        browseSearch.clearFocus();
    }

    public static ArrayList<String> getSearchHistory() {

        return searchHistory;
    }

    public static void updateHistory(String album) {

//        Add album to search history
        searchHistory.add(album);
    }

}