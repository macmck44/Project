/*Developer documentation sources below used for bottom navigation view
https://developer.android.com/guide/navigation/navigation-ui#java
https://code.tutsplus.com/tutorials/how-to-code-a-bottom-navigation-bar-for-an-android-app--cms-30305

Following sources used to help with TheMovieDB API
https://developers.themoviedb.org/3/getting-started/introduction
https://www.themoviedb.org/documentation/api*/


package com.example.uuj.moviebuddy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
//import android.graphics.Movie;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Movie> movieList;
    ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    public static final String LOG_TAG = MovieAdapter.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.movie_detail);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
                Toast.makeText(HomeActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView bottomNavView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        Menu menu = bottomNavView.getMenu();
        MenuItem menuitem = menu.getItem(0);
        menuitem.setChecked(true);


        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())   {

                    case R.id.ic_home:
                        break;

                    case R.id.ic_account:
                        Intent intent = new Intent(HomeActivity.this, com.example.uuj.moviebuddy.AccountActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.ic_news:
                        Intent intent1 = new Intent(HomeActivity.this, com.example.uuj.moviebuddy.NewsActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_rec:
                        Intent intent2 = new Intent(HomeActivity.this, com.example.uuj.moviebuddy.RecActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_maps:
                        Intent intent3 = new Intent(HomeActivity.this, com.example.uuj.moviebuddy.MapsActivity.class);
                        startActivity(intent3);
                        break;

                }

                return false;
            }
        });

    }

    private void initViews()    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching movies...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        recyclerView = (RecyclerView) findViewById(R.id.movie_recyclerview);
        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)   {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }   else    {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        loadJSON();

    }

    public Activity getActivity()   {
        Context context = this;
        while (context instanceof ContextWrapper)   {
            if(context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    private void loadJSON() {
        try{
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty())   {
                Toast.makeText(getApplicationContext(), "Need API Key", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return;
            }

            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<Response> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    List<com.example.uuj.moviebuddy.Movie> movies = response.body().getResults();
                    recyclerView.setAdapter(new MovieAdapter(getApplicationContext(), movies));
                    recyclerView.smoothScrollToPosition(0);
                    if(swipeRefreshLayout.isRefreshing())   {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Toast.makeText(HomeActivity.this, "Error Fetching Data", Toast.LENGTH_SHORT).show();
                }
            });

        } catch(Exception e)    {
            Toast.makeText(HomeActivity.this, "Error Fetching Data", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)   {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())    {
            case R.id.menu_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
