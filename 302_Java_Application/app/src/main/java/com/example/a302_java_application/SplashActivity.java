package com.example.a302_java_application;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.airbnb.lottie.LottieAnimationView;

import java.nio.channels.InterruptedByTimeoutException;

public class SplashActivity extends AppCompatActivity {

    private TextView brandName;
    private LottieAnimationView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // Set up transition
        brandName = (TextView) findViewById(R.id.brand_name);
        logo = (LottieAnimationView) findViewById(R.id.logo);
        Animation splashTransition = AnimationUtils.loadAnimation(this, R.anim.splash_transition);
        brandName.startAnimation(splashTransition);
        logo.startAnimation(splashTransition);

        // Set up app opening sound
        final MediaPlayer sound = MediaPlayer.create(this, R.raw.sound);

        // Open main activity after 6 seconds
        final Intent intent = new Intent (this, MainActivity.class);
        Thread timer = new Thread() {
            public void run () {
                try {
                    sound.start();
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
