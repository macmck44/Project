package com.example.uuj.moviebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Owner on 24/03/2020.
 */

public class RecDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_detail);

        Bundle b1 = getIntent().getExtras();
        String string1 = b1.getString("title");
        String string11 = b1.getString("news");
        TextView texttitle = (TextView) findViewById(R.id.newstitle);
        texttitle.setText(string1);
        TextView textnews = (TextView) findViewById(R.id.newsnews);
        textnews.setText(string11);
        ImageView newsimage = (ImageView) findViewById(R.id.newsimage);
        int img = b1.getInt("img");
        newsimage.setImageResource(img);

    }

}
