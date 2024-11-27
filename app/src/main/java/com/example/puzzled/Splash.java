package com.example.puzzled;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imageview1 = findViewById(R.id.lowdingimg1);
        imageview1.animate().alpha(0.7f).rotation(1800).setDuration(1800);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent nextScreen = new Intent(Splash.this,MainActivity.class);
                startActivity(nextScreen);
                //it goes to next screen without use any button directly goes to next screen
                finish();
                //after complete the game we want to close or go back to game then it goes directly out of game
            }
        },2000);

    }
}