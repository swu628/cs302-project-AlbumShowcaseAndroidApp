package com.example.a302_java_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Album> allAlbums = new ArrayList<>();
    private ArrayList<Album> mostViewed = new ArrayList<>();

    private SearchView searchView;

    private Button girlGroup;
    private Button boyGroup;
    private Button soloist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Create DataProvider to get albums
        DataProvider dataProvider = new DataProvider(this);
//        Get list of all albums and save to attribute
        this.allAlbums = dataProvider.getAlbums();

//        Update list of all albums for DataProvider class
        dataProvider.updateAlbumList(dataProvider.getAlbums());

//        Get the list of most viewed albums
        this.mostViewed = getMostViewed();

//        Set up recycler view for most viewed albums
        setupRecyclerView();

//        Set up bottom navigation bar
        setupBottomNavBar();

//        Set up Search View
//        this.searchView = findViewById(R.id.main_search_bar);
//        searchView.clearFocus();

    }


    public ArrayList<Album> getAllAlbums() {
        return allAlbums;
    }

    public void setupRecyclerView() {
        //        Create instance for recycler view
        RecyclerView recyclerView = findViewById(R.id.most_views_recycler);
//        Create instance for adapter for recycler view
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this.mostViewed, this);
//        Set adapter and layout manager for the recycler view
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    public void setupBottomNavBar() {

        girlGroup = (Button) findViewById(R.id.button1);
        boyGroup = (Button) findViewById(R.id.button2);
        soloist = (Button) findViewById(R.id.button3);

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
                public void onClick(View v) {openListActivity();}
            });
        }

//        Set up Search View
//        this.searchView = findViewById(R.id.main_search_bar);
//        searchView.clearFocus();


//        Set up bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.home_bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

//        Define what happens when an item is clicked
        bottomNavigationView.setOnItemSelectedListener(item -> {

//            Start respective activities when different items are clicked
            if (item.getItemId() == R.id.bottom_home) {
                return true;
            } else if (item.getItemId() == R.id.bottom_browse) {
                startActivity(new Intent(getApplicationContext(), BrowseActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else {
                return false;
            }

        });

    }
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

//        Sort views from
        Arrays.sort(views);

//        Find the albums with the most views and add to the list of most viewed albums
        for (int j = 0; j < 3; j++) {
            int mostView = views[views.length - 1 - j];
            for (int k = 0; k < albumList.size(); k++) {
                Album currentAlbum = albumList.get(k);
                if (currentAlbum.getViews() == mostView) {
                    mostViewed.add(currentAlbum);
                    albumList.remove(k);
                }
            }
        }

        return mostViewed;
    }

    // Open list activity when the category button is clicked
    public void openListActivity() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

}