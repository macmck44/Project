/*
Developer documentation sources below used for bottom navigation view
https://developer.android.com/guide/navigation/navigation-ui#java
https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305

Youtube API sourced from here:
https://developers.google.com/youtube/android/player
*/

package com.example.uuj.moviebuddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends YouTubeBaseActivity{

    Button playbutton;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    List<String> trailerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeplayer);
        playbutton = (Button) findViewById(R.id.play_btn);
        BottomNavigationView bottomNavView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        Menu menu = bottomNavView.getMenu();
        MenuItem menuitem = menu.getItem(2);
        menuitem.setChecked(true);
        trailerList.add("XEMwSdne6UE");
        trailerList.add("pHjTOzGU9oI");
        trailerList.add("mXcZ7WDsVwk");
        trailerList.add("RxAtuMu_ph4");
        trailerList.add("vf1aW1z437I");
        trailerList.add("J0hTmzISOlQ");
        trailerList.add("fl2r3Fwxz_o");
        trailerList.add("sfM7_JLk-84");
        trailerList.add("eyzxu26-Wqk");
        trailerList.add("qSqVVswa420");

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())   {

                    case R.id.ic_home:
                        Intent intent = new Intent(NewsActivity.this, com.example.uuj.moviebuddy.HomeActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_account:
                        Intent intent1 = new Intent(NewsActivity.this, com.example.uuj.moviebuddy.AccountActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_news:
                        break;

                    case R.id.ic_rec:
                        Intent intent2 = new Intent(NewsActivity.this, com.example.uuj.moviebuddy.RecActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_maps:
                        Intent intent3 = new Intent(NewsActivity.this, com.example.uuj.moviebuddy.MapsActivity.class);
                        startActivity(intent3);
                        break;

                }

                return false;
            }
        });

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideos(trailerList);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(NewsActivity.this, "Failed to load video", Toast.LENGTH_SHORT).show();
            }
        };

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(getString(R.string.youtube_api_key), onInitializedListener);
            }
        });
    }
}
