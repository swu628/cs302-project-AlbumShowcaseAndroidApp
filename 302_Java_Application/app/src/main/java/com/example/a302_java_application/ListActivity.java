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

//        Create instance for recycler view
        RecyclerView recyclerList = findViewById(R.id.list_recycler);
//        Create instance for adapter for recycler view
        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(this.allAlbums, this);
//        Set adapter and layout manager for the recycler view
        recyclerList.setAdapter(recyclerListAdapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));

        //        Set up bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), BrowseActivity.class));
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