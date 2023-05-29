package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

     private ViewPager2 viewPager;
     ArrayList<Album> details;
     private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Extract the variables being passed
        String name = getIntent().getStringExtra("name");
        String artist = getIntent().getStringExtra("artist");
        int image = getIntent().getIntExtra("image", 0);
        String releaseDate = getIntent().getStringExtra("releaseDate");

        // Get the ids from xml file
        back = (ImageView) findViewById(R.id.detailBack);
        TextView nameTextView = (TextView) findViewById(R.id.album_name);
        TextView artistTextView = (TextView) findViewById(R.id.group_name);
        ImageView imageView = (ImageView) findViewById(R.id.album_cover);
        TextView releaseDateTextView = (TextView) findViewById(R.id.release_date);

        // Set value based on the item chosen
        nameTextView.setText(name);
        artistTextView.setText(artist);
        imageView.setImageResource(image);
        releaseDateTextView.setText(releaseDate);

         // Initiating view pager and get the images to be displayed
         viewPager = findViewById(R.id.pager);
         int[] detailImage = {R.drawable.new_jeans_1, R.drawable.new_jeans_2, R.drawable.new_jeans_3};
         details = new ArrayList<>();
         for (int i=0; i<detailImage.length; i++) {
            Album album = new Album(detailImage[i]);
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