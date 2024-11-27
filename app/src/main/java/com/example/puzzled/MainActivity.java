package com.example.puzzled;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void buttonClick(View view){
        Intent buttonClick = new Intent(MainActivity.this,GameScreenA.class);
        startActivity(buttonClick);

        ImageView imageView = findViewById(R.id.NameImg);

        // Create a new AlphaAnimation instance for the fade-in effect
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f); // Start from 0 (invisible) to 1 (fully visible)
        fadeIn.setDuration(1600); // Set the duration to 2 seconds
        fadeIn.setFillAfter(true); // Keeps the animation's end state after the animation is finished

        // Start the animation
        imageView.startAnimation(fadeIn);

        MediaPlayer playSound = MediaPlayer.create(this,R.raw.button_click);
        playSound.start();

    }
}