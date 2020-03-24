/*Developer documentation sources below used for bottom navigation view
https://developer.android.com/guide/navigation/navigation-ui#java
https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305*/

package com.example.uuj.moviebuddy;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RecActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec);

        BottomNavigationView bottomNavView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        Menu menu = bottomNavView.getMenu();
        MenuItem menuitem = menu.getItem(3);
        menuitem.setChecked(true);
        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.movienews1);
        linearLayout1.setClickable(true);
        ImageView imageView1 = (ImageView) findViewById(R.id.quietplaceimg);
        TextView textView1 = (TextView) findViewById(R.id.quietplacetitle);
        TextView textView11 = (TextView) findViewById(R.id.quietplacenews);
        final String string1 = textView1.getText().toString();
        final String string11 = textView11.getText().toString();
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.movienews2);
        linearLayout2.setClickable(true);
        ImageView imageView2 = (ImageView) findViewById(R.id.badboysimg);
        TextView textView2 = (TextView) findViewById(R.id.badboystitle);
        TextView textView22 = (TextView) findViewById(R.id.badboysnews);
        final String string2 = textView2.getText().toString();
        final String string22 = textView22.getText().toString();
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.movienews3);
        linearLayout3.setClickable(true);
        ImageView imageView3 = (ImageView) findViewById(R.id.blackwidowimg);
        TextView textView3 = (TextView) findViewById(R.id.blackwidowtitle);
        TextView textView33 = (TextView) findViewById(R.id.blackwidownews);
        final String string3 = textView3.getText().toString();
        final String string33 = textView33.getText().toString();
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.movienews4);
        linearLayout4.setClickable(true);
        ImageView imageView4 = (ImageView) findViewById(R.id.ghostbustersimg);
        TextView textView4 = (TextView) findViewById(R.id.ghostbusterstitle);
        TextView textView44 = (TextView) findViewById(R.id.ghostbustersnews);
        final String string4 = textView4.getText().toString();
        final String string44 = textView44.getText().toString();
        LinearLayout linearLayout5 = (LinearLayout) findViewById(R.id.movienews5);
        linearLayout5.setClickable(true);
        ImageView imageView5 = (ImageView) findViewById(R.id.potterimg);
        TextView textView5 = (TextView) findViewById(R.id.pottertitle);
        TextView textView55 = (TextView) findViewById(R.id.potternews);
        final String string5 = textView5.getText().toString();
        final String string55 = textView55.getText().toString();
        LinearLayout linearLayout6 = (LinearLayout) findViewById(R.id.movienews6);
        linearLayout6.setClickable(true);
        ImageView imageView6 = (ImageView) findViewById(R.id.sonicimg);
        TextView textView6 = (TextView) findViewById(R.id.sonictitle);
        TextView textView66 = (TextView) findViewById(R.id.sonicnews);
        final String string6 = textView6.getText().toString();
        final String string66 = textView66.getText().toString();
        LinearLayout linearLayout7 = (LinearLayout) findViewById(R.id.movienews7);
        linearLayout7.setClickable(true);
        ImageView imageView7 = (ImageView) findViewById(R.id.spidermanimg);
        TextView textView7 = (TextView) findViewById(R.id.spidermantitle);
        TextView textView77 = (TextView) findViewById(R.id.spidermannews);
        final String string7 = textView7.getText().toString();
        final String string77 = textView77.getText().toString();
        LinearLayout linearLayout8 = (LinearLayout) findViewById(R.id.movienews8);
        linearLayout8.setClickable(true);
        ImageView imageView8 = (ImageView) findViewById(R.id.starwarsimg);
        TextView textView8 = (TextView) findViewById(R.id.starwarstitle);
        TextView textView88 = (TextView) findViewById(R.id.starwarsnews);
        final String string8 = textView8.getText().toString();
        final String string88 = textView88.getText().toString();
        LinearLayout linearLayout9 = (LinearLayout) findViewById(R.id.movienews9);
        linearLayout9.setClickable(true);
        ImageView imageView9 = (ImageView) findViewById(R.id.topgunimg);
        TextView textView9 = (TextView) findViewById(R.id.topguntitle);
        TextView textView99 = (TextView) findViewById(R.id.topgunnews);
        final String string9 = textView9.getText().toString();
        final String string99 = textView99.getText().toString();
        LinearLayout linearLayout10 = (LinearLayout) findViewById(R.id.movienews10);
        linearLayout10.setClickable(true);
        ImageView imageView10 = (ImageView) findViewById(R.id.venomimg);
        TextView textView10 = (TextView) findViewById(R.id.venomtitle);
        TextView textView100 = (TextView) findViewById(R.id.venomnews);
        final String string10 = textView10.getText().toString();
        final String string100 = textView100.getText().toString();

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail1 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.RecDetailActivity.class);
                intentdetail1.putExtra("title", string1);
                intentdetail1.putExtra("news", string11);
                intentdetail1.putExtra("img", R.drawable.quietplace);
                startActivity(intentdetail1);

            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail2 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.RecDetailActivity.class);
                intentdetail2.putExtra("title", string2);
                intentdetail2.putExtra("news", string22);
                intentdetail2.putExtra("img", R.drawable.badboys);
                startActivity(intentdetail2);

            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail3 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.RecDetailActivity.class);
                intentdetail3.putExtra("title", string3);
                intentdetail3.putExtra("news", string33);
                intentdetail3.putExtra("img", R.drawable.blackwidow);
                startActivity(intentdetail3);

            }
        });

        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail4 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.RecDetailActivity.class);
                intentdetail4.putExtra("title", string4);
                intentdetail4.putExtra("news", string44);
                intentdetail4.putExtra("img", R.drawable.ghostbusters);
                startActivity(intentdetail4);

            }
        });

        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail5 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.RecDetailActivity.class);
                intentdetail5.putExtra("title", string5);
                intentdetail5.putExtra("news", string55);
                intentdetail5.putExtra("img", R.drawable.potter);
                startActivity(intentdetail5);

            }
        });

        linearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail6 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.RecDetailActivity.class);
                intentdetail6.putExtra("title", string6);
                intentdetail6.putExtra("news", string66);
                intentdetail6.putExtra("img", R.drawable.sonic);
                startActivity(intentdetail6);

            }
        });

        linearLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail7 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.RecDetailActivity.class);
                intentdetail7.putExtra("title", string7);
                intentdetail7.putExtra("news", string77);
                intentdetail7.putExtra("img", R.drawable.spiderman);
                startActivity(intentdetail7);

            }
        });

        linearLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail8 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.RecDetailActivity.class);
                intentdetail8.putExtra("title", string8);
                intentdetail8.putExtra("news", string88);
                intentdetail8.putExtra("img", R.drawable.starwars);
                startActivity(intentdetail8);

            }
        });

        linearLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail9 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.RecDetailActivity.class);
                intentdetail9.putExtra("title", string9);
                intentdetail9.putExtra("news", string99);
                intentdetail9.putExtra("img", R.drawable.topgun);
                startActivity(intentdetail9);

            }
        });

        linearLayout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdetail10 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.RecDetailActivity.class);
                intentdetail10.putExtra("title", string10);
                intentdetail10.putExtra("news", string100);
                intentdetail10.putExtra("img", R.drawable.venom);
                startActivity(intentdetail10);

            }
        });

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())   {

                    case R.id.ic_home:
                        Intent intent = new Intent(RecActivity.this, com.example.uuj.moviebuddy.HomeActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_account:
                        Intent intent1 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.AccountActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_news:
                        Intent intent2 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.NewsActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_rec:
                        break;

                    case R.id.ic_maps:
                        Intent intent3 = new Intent(RecActivity.this, com.example.uuj.moviebuddy.MapsActivity.class);
                        startActivity(intent3);
                        break;

                }

                return false;
            }
        });

    }
}