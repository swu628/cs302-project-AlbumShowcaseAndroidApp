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
        int position = getIntent().getIntExtra("position", 0);

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
         int[] detailImage = {R.drawable.new_jeans_1, R.drawable.new_jeans_2, R.drawable.new_jeans_3,
                 R.drawable.the_album_1, R.drawable.the_album_2, R.drawable.the_album_3,
                 R.drawable.i_never_die_1, R.drawable.i_never_die_2, R.drawable.i_never_die_3,
                 R.drawable.icy_1, R.drawable.icy_2, R.drawable.icy_3,
                 R.drawable.born_pink_1, R.drawable.born_pink_2, R.drawable.born_pink_3,
                 R.drawable.shooting_star_1, R.drawable.shooting_star_2, R.drawable.shooting_star_3,
                 R.drawable.bad_boy_1, R.drawable.bad_boy_2, R.drawable.bad_boy_3,
                 R.drawable.psycho_1, R.drawable.psycho_2, R.drawable.psycho_3,
                 R.drawable.next_level_1, R.drawable.next_level_2, R.drawable.next_level_3,
                 R.drawable.ive_1, R.drawable.ive_2, R.drawable.ive_3,

                 R.drawable.seventeen_mini_1, R.drawable.seventeen_mini_2, R.drawable.seventeen_mini_3,
                 R.drawable.candy_1, R.drawable.candy_2, R.drawable.candy_3,
                 R.drawable.love_shot_1, R.drawable.love_shot_2, R.drawable.love_shot_3,
                 R.drawable.hello_future_1, R.drawable.hello_future_2, R.drawable.hello_future_3,
                 R.drawable.cherry_bomb_1, R.drawable.cherry_bomb_2, R.drawable.cherry_bomb_3,
                 R.drawable.treasure_1, R.drawable.treasure_2, R.drawable.treasure_3,
                 R.drawable.seventeen_album_1, R.drawable.seventeen_album_2, R.drawable.seventeen_album_3,
                 R.drawable.hot_sauce_1, R.drawable.hot_sauce_2, R.drawable.hot_sauce_3,
                 R.drawable.oddinary_1, R.drawable.oddinary_2, R.drawable.oddinary_3,
                 R.drawable.xoxo_1, R.drawable.xoxo_2, R.drawable.xoxo_3,

                 R.drawable.r_1, R.drawable.r_2, R.drawable.r_3,
                 R.drawable.solo_1, R.drawable.solo_2, R.drawable.solo_3,
                 R.drawable.me_1, R.drawable.me_2, R.drawable.me_3,
                 R.drawable.lalisa_1, R.drawable.lalisa_2, R.drawable.lalisa_3,
                 R.drawable.palette_1, R.drawable.palette_2, R.drawable.palette_3,
                 R.drawable.birthday_1, R.drawable.birthday_2, R.drawable.birthday_3,
                 R.drawable.the_weekend_1, R.drawable.the_weekend_2, R.drawable.the_weekend_3,
                 R.drawable.delight_1, R.drawable.delight_2, R.drawable.delight_3,
                 R.drawable.love_poem_1, R.drawable.love_poem_2, R.drawable.love_poem_3,
                 R.drawable.love_war_1, R.drawable.love_war_2, R.drawable.love_war_3};

         details = new ArrayList<>();
         for (int i = position * 3; i<position*3+3; i++) {
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