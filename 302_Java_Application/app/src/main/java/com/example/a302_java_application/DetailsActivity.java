package com.example.a302_java_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    /**
     private ViewPager2 viewPager;
     ArrayList<Album> details;
     **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        /**
         // Initiating view pager
         viewPager = findViewById(R.id.pager);
         int[] detailImage = {R.drawable.new_jeans_1, R.drawable.new_jeans_2, R.drawable.new_jeans_3};

         details = new ArrayList<>();

         for (int i=0; i<detailImage.length; i++) {
         Album album = new Album(detailImage[i]);
         details.add(album);
         }

         ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.details);
         viewPager.setAdapter(viewPagerAdapter);
         viewPager.setClipToPadding(false);
         viewPager.setClipChildren(false);
         viewPager.setOffscreenPageLimit(2);
         viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
         **/
    }
}