package com.ice.zztopp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.la_grange);

        Button playButton = (Button) findViewById(R.id.play_btn);
        Button pauseButton = (Button) findViewById(R.id.pause_btn);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                releaseMediaPlayer();
                mediaPlayer.start();
                Toast.makeText(MainActivity.this, "Play", Toast.LENGTH_SHORT).show();
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                releaseMediaPlayer();

                mediaPlayer.pause();
                Toast.makeText(MainActivity.this, "Pause", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void releaseMediaPlayer() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
