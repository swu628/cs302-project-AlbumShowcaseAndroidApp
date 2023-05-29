package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<Album> allAlbums = new ArrayList<>();
    private ArrayList<Album> categoryAlbums = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        Create DataProvider to get albums
        DataProvider dataProvider = new DataProvider(this);
//        Get list of all albums and save to attribute
        this.allAlbums = dataProvider.getAlbums();

//        Update list of all albums for DataProvider class
        dataProvider.updateAlbumList(this.allAlbums);

        // Get the list of albums to be displayed based on the chosen category
        String buttonClicked = getIntent().getStringExtra("buttonClicked");
        if (buttonClicked.equals("Girl Group")) {
            this.categoryAlbums = getGirlGroupAlbums();
        } else if (buttonClicked.equals("Boy Group")) {
            this.categoryAlbums = getBoyGroupAlbums();
        } else {
            this.categoryAlbums = getSoloistAlbums();
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