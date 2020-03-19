package com.example.police_siren;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

public class ActivityFlashing extends AppCompatActivity {

    ImageView imageView ;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashing);
        getSupportActionBar().hide();
        imageView = findViewById(R.id.image_view_id);

        start_siren();
        start_light();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                finish();
            }
        });
    }

    public void start_siren(){
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.police_siren);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @SuppressLint("WrongConstant")
    public void start_light(){
        ObjectAnimator animator = ObjectAnimator.ofInt(imageView, "BackgroundColor", Color.RED,Color.BLUE);

        animator.setDuration(120);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setRepeatMode(Animation.REVERSE);
        animator.setRepeatCount(Animation.INFINITE);

        animator.start();
    }

    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        super.onBackPressed();
    }
}
