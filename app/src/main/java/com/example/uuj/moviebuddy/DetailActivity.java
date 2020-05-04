/*
TheMovieDB implementation sourced from here:
https://developers.themoviedb.org/3/getting-started/introduction
https://www.supinfo.com/articles/single/7849-developing-popular-movies-application-in-android-using-retrofit
*/

package com.example.uuj.moviebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

/**
 * Created by Owner on 10/03/2020.
 */

public class DetailActivity extends AppCompatActivity {
    TextView namemovie, plotsynopsis, userrating, releasedate;
    ImageView imageview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        imageview = (ImageView) findViewById(R.id.movie_thumb);
        namemovie = (TextView) findViewById(R.id.movtitle);
        plotsynopsis = (TextView) findViewById(R.id.plotsynopsis);
        userrating = (TextView) findViewById(R.id.movierating);
        releasedate = (TextView) findViewById(R.id.releasedate);

        Intent intent1 = getIntent();
        if (intent1.hasExtra("original_title")) {
            String thumb = getIntent().getExtras().getString("poster_path");
            String moviename = getIntent().getExtras().getString("original_title");
            String synopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String daterelease = getIntent().getExtras().getString("release_date");

            Glide.with(this).load(thumb).placeholder(R.drawable.ic_action_load).into(imageview);
            namemovie.setText(moviename);
            plotsynopsis.setText(synopsis);
            userrating.setText(rating);
            releasedate.setText(daterelease);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void initCollapsingToolbar()    {
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isshow = false;
            int scrollrange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollrange == -1)  {
                    scrollrange = appBarLayout.getTotalScrollRange();
                }
                if (scrollrange + verticalOffset == 0)  {
                    collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                    isshow = true;
                } else if (isshow)  {
                    collapsingToolbarLayout.setTitle(" ");
                    isshow = false;
                }
            }
        });
    }
}
