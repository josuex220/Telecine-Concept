package com.agplay.tv.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.agplay.tv.R;

import tcking.github.com.giraffeplayer2.VideoView;

public class Playertest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playertest);


        VideoView videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setVideoPath("http://cdn.pipocaflix.net/pasta-1/teste.mp4").getPlayer().start();


    }
}
