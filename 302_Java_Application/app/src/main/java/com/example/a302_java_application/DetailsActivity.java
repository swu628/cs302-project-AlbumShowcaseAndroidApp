package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

     private ViewPager2 viewPager;
     ArrayList<Album> details;
     private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        back = (ImageView) findViewById(R.id.detailBack);

         // Initiating view pager
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