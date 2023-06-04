package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

     private ViewPager2 viewPager;
     ArrayList<Album> details;
     private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // Extract the variables being passed
        String name = getIntent().getStringExtra("name");
        String artist = getIntent().getStringExtra("artist");
        int image = getIntent().getIntExtra("image", 0);
        String releaseDate = getIntent().getStringExtra("releaseDate");
        String description = getIntent().getStringExtra("description");
        String tracklist = getIntent().getStringExtra("tracklist");
        String contain = getIntent().getStringExtra("contain");
        int[] detailImages = getIntent().getIntArrayExtra("detailImages");

        // Get the ids from xml file
        back = (ImageView) findViewById(R.id.detailBack);
        TextView nameTextView = (TextView) findViewById(R.id.album_name);
        TextView artistTextView = (TextView) findViewById(R.id.group_name);
        ImageView imageView = (ImageView) findViewById(R.id.album_cover);
        TextView releaseDateTextView = (TextView) findViewById(R.id.release_date);
        TextView descriptionTextView = (TextView) findViewById(R.id.album_description);
        TextView tracklistTextView = (TextView) findViewById(R.id.album_tracklist);
        TextView containTextView = (TextView) findViewById(R.id.album_contain);

        // Set value based on the item chosen
        nameTextView.setText(name);
        artistTextView.setText(artist);
        imageView.setImageResource(image);
        releaseDateTextView.setText(releaseDate);
        descriptionTextView.setText(description);
        tracklistTextView.setText(tracklist);
        containTextView.setText(contain);

         // Initiating view pager and get the images to be displayed
         viewPager = findViewById(R.id.pager);

         details = new ArrayList<>();
         for (int i = 0; i<3; i++) {
             Album album = new Album(detailImages[i]);
             details.add(album);
         }

         // Display detail images using view pager
         ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.details);
         viewPager.setAdapter(viewPagerAdapter);
         viewPager.setClipToPadding(false);
         viewPager.setClipChildren(false);
         viewPager.setOffscreenPageLimit(2);
         viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

         // Bring user back to the main activity when the back image is clicked
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}