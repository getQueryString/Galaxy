package com.example.galaxy.OnAction;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.example.galaxy.Main.Main;

public class StartPauseMusicOnClick {

    Main m = Main.getInstance();

    @SuppressLint("SetTextI18n")
    public StartPauseMusicOnClick() {
        m.startPauseMusic.setOnClickListener(v -> {
            if (!m.mediaPlayerHackerwar.isPlaying()) {
                m.mediaPlayerHackerwar.start();
                m.startPauseMusic.setText("Pause music");
                m.startPauseMusic.setBackgroundColor(Color.rgb(0, 255, 0));
            } else {
                m.mediaPlayerHackerwar.pause();
                m.startPauseMusic.setText("Resume music");
                m.startPauseMusic.setBackgroundColor(Color.rgb(255, 0, 0));
            }
        });
    }
}
