package com.example.videostreaming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.videostreaming.databinding.ActivityMainBinding;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import com.google.android.exoplayer2.MediaItem;

public class MainActivity extends AppCompatActivity {

    String videoLink ="https://firebasestorage.googleapis.com/v0/b/fir-storage-7fcf9.appspot.com/o/Uploads%2Fvideo%2F%23%D8%AA%D9%85%D9%8A%D9%85_%D8%A7%D9%84%D8%A8%D8%B1%D8%BA%D9%88%D8%AB%D9%8A%20-%20with%20Music%20%D8%A7%D9%84%D8%B5%D9%88%D8%AA%20%D8%A7%D9%84%D8%A3%D8%B5%D9%84%D9%8A%20-%20Nada%20Milhem.mp4?alt=media&token=c8ce74db-c5f8-461a-95d8-896b3712a744";
    ActivityMainBinding binding;
    PlayerView pv;

    SimpleExoPlayer player ;
    boolean playWhenReady = true;
    long curentPostion = 0 ;
    int curentWindow = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pv = binding.playerView;

    }

    private void  initPlayer(){
        player = new SimpleExoPlayer.Builder(this).build();
        pv.setPlayer(player);
        MediaItem item = MediaItem.fromUri(videoLink);
        player.setMediaItem(item);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(curentWindow , curentPostion);
        player.prepare();


    }



    private  void  relesePlayer(){
        if(player != null){
            playWhenReady = player.getPlayWhenReady();
            curentWindow = player.getCurrentWindowIndex();
            curentPostion = player.getCurrentPosition();
            player = null ;

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        initPlayer();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(player != null){
            initPlayer();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        relesePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        relesePlayer();
    }
}