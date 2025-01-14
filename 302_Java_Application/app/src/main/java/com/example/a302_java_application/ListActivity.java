package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListActivity extends AppCompatActivity implements RecyclerListInterface {

    ArrayList<Album> allAlbums = new ArrayList<>();
    private ArrayList<Album> categoryAlbums = new ArrayList<>();

    private String searchHistory;

    // Use view holder later
    private TextView albumCategoryText;
    private ImageView back;
    private int pos;
    private boolean[] favourite = new boolean[30];
    private TextView emptySearchText;
    private LottieAnimationView emptySearchImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // Use view holder later
        albumCategoryText = (TextView) findViewById(R.id.group_category);
        back = (ImageView) findViewById(R.id.detailBack);
        emptySearchText = (TextView) findViewById(R.id.empty_search_text);
        emptySearchImage = (LottieAnimationView) findViewById(R.id.empty_search_image);

//        Create DataProvider to get albums
        DataProvider dataProvider = new DataProvider(this);
//        Get list of all albums and save to attribute
        this.allAlbums = dataProvider.getAlbums();

//        Update list of all albums for DataProvider class
        dataProvider.updateAlbumList(this.allAlbums);

        // Get the list of albums to be displayed based on the chosen category
        // and change the heading based on the chosen category
        String buttonClicked = getIntent().getStringExtra("buttonClicked");
        if (getIntent().getStringExtra("query") != null) {
            searchHistory = getIntent().getStringExtra("query");
        }
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
            this.categoryAlbums = getSearchResult(searchHistory);
            albumCategoryText.setText("Search Results");
            if (this.categoryAlbums.isEmpty()) {
                emptySearchText.setVisibility(View.VISIBLE);
                emptySearchImage.setVisibility(View.VISIBLE);
            }
        }

//        Set up recycler view for list of albums
        setUpRecycler();

//        Set up the bottom navigation bar
        setUpBottomNavBar();

        // Bring user back to the main activity when the back image is clicked
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
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
            if (album.getName().toUpperCase().contains(search.toUpperCase()) ||
                    album.getArtist().toUpperCase().contains(search.toUpperCase())) {
                searchResults.add(album);
            }
        }
        return searchResults;
    }

    public void setUpRecycler() {
//        Create instance for recycler view
        RecyclerView recyclerList = findViewById(R.id.list_recycler);
        DataProvider dataProvider = new DataProvider(this);
//        Create instance for adapter for recycler view
        ArrayList<Album> displayAlbums = dataProvider.findFavourites(this.categoryAlbums);
        RecyclerListAdapter recyclerListAdapter = new RecyclerListAdapter(displayAlbums, this, this);
//        Set adapter and layout manager for the recycler view
        recyclerList.setAdapter(recyclerListAdapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
    }


    public void setUpBottomNavBar() {
        // Set up bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar);

        // Open required activities when items clicked
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                // Create new intent
                Intent intent = new Intent(ListActivity.this, MainActivity.class);

                // Open main activity
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;

            } else if (item.getItemId() == R.id.bottom_browse) {
                // Create new intent
                Intent intent = new Intent(ListActivity.this, BrowseActivity.class);

                // Pass search history to BrowseActivity
                if (searchHistory != null) {
                    intent.putExtra("history", searchHistory);
                }

                // Open browse activity
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;

            } else {
                // Create new intent
                Intent intent = new Intent(ListActivity.this, FavouritesActivity.class);

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
        DataProvider.updateViewed(categoryAlbums.get(position));

        // Pass the variables to another activity
        intent.putExtra("name", categoryAlbums.get(position).getName());
        intent.putExtra("artist", categoryAlbums.get(position).getArtist());
        intent.putExtra("image", categoryAlbums.get(position).getImage());
        intent.putExtra("releaseDate", categoryAlbums.get(position).getReleaseDate());
        intent.putExtra("description", categoryAlbums.get(position).getDescription());
        intent.putExtra("tracklist", categoryAlbums.get(position).getTracklist());
        intent.putExtra("contain", categoryAlbums.get(position).getContain());
        intent.putExtra("detailImages", categoryAlbums.get(position).getDetailImage());

        // Switch activity
        startActivity(intent);
    }
}