package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<Album> allAlbums = new ArrayList<>();
    private ArrayList<Album> categoryAlbums = new ArrayList<>();

    // Use view holder later
    private TextView albumCategoryText;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Use view holder later
        albumCategoryText = (TextView) findViewById(R.id.group_category);
        back = (ImageView) findViewById(R.id.detailBack);

//        Create DataProvider to get albums
        DataProvider dataProvider = new DataProvider(this);
//        Get list of all albums and save to attribute
        this.allAlbums = dataProvider.getAlbums();

//        Update list of all albums for DataProvider class
        dataProvider.updateAlbumList(this.allAlbums);

        // Get the list of albums to be displayed based on the chosen category
        // and change the heading based on the chosen category
        String buttonClicked = getIntent().getStringExtra("buttonClicked");
        String query = getIntent().getStringExtra("query");
        if (buttonClicked.equals("Girl Group")) {
            this.categoryAlbums = getGirlGroupAlbums();
            albumCategoryText.setText("Girl Group Albums");
        } else if (buttonClicked.equals("Boy Group")) {
            this.categoryAlbums = getBoyGroupAlbums();
            albumCategoryText.setText("Boy Group Albums");
        } else if (buttonClicked.equals("Soloist")) {
            this.categoryAlbums = getSoloistAlbums();
            albumCategoryText.setText("Soloist Albums");
        } else {
            this.categoryAlbums = getSearchResult(query);
            albumCategoryText.setText("Search Results");
        }

//        Create instance for recycler view
        RecyclerView recyclerList = findViewById(R.id.list_recycler);
//        Create instance for adapter for recycler view
        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(this.categoryAlbums, this);
//        Set adapter and layout manager for the recycler view
        recyclerList.setAdapter(recyclerListAdapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));

//        Set up the bottom navigation bar
        setUpBottomNavBar();

        // Bring user back to the main activity when the back image is clicked
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public ArrayList<Album> getGirlGroupAlbums() {
//        Initialise list of girl group albums and list of all albums
        ArrayList<Album> girlGroupAlbums = new ArrayList<>();
        ArrayList<Album> albumList = this.allAlbums;

//        Get a list of girl group albums
        int i;
        for (i = 0; i < 10; i++) {
            girlGroupAlbums.add(albumList.get(i));
        }
        return girlGroupAlbums;
    }

    public ArrayList<Album> getBoyGroupAlbums() {
//        Initialise list of boy group albums and list of all albums
        ArrayList<Album> boyGroupAlbums = new ArrayList<>();
        ArrayList<Album> albumList = this.allAlbums;

//        Get a list of boy group albums
        int i;
        for (i = 10; i < 20; i++) {
            boyGroupAlbums.add(albumList.get(i));
        }
        return boyGroupAlbums;
    }

    public ArrayList<Album> getSoloistAlbums() {
//        Initialise list of soloist albums and list of all albums
        ArrayList<Album> soloistAlbums = new ArrayList<>();
        ArrayList<Album> albumList = this.allAlbums;

//        Get a list of soloist albums
        int i;
        for (i = 20; i < 30; i++) {
            soloistAlbums.add(albumList.get(i));
        }
        return soloistAlbums;
    }

    public ArrayList<Album> getSearchResult(String search) {

//        Initialise list of search results and list of all albums
        ArrayList<Album> searchResults = new ArrayList<>();
        ArrayList<Album> albumList = this.allAlbums;

//        Get a list of search results
        int i;
        for (i = 0; i < albumList.size(); i++) {
            Album album = albumList.get(i);
            if (album.getName().contains(search) || album.getArtist().contains(search)) {
                searchResults.add(album);
            }
        }
        return searchResults;
    }


    public void setUpBottomNavBar() {

//        Set up bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar);

//        Open required activities when items clicked
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
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
}