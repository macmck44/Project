/*Developer documentation sources below used for bottom navigation view
https://developer.android.com/guide/navigation/navigation-ui#java
https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305*/

package com.example.uuj.moviebuddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NewsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        BottomNavigationView bottomNavView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        Menu menu = bottomNavView.getMenu();
        MenuItem menuitem = menu.getItem(2);
        menuitem.setChecked(true);

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

    }
}
