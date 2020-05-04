/*
TheMovieDB implementation sourced from here:
https://developers.themoviedb.org/3/getting-started/introduction
https://www.supinfo.com/articles/single/7849-developing-popular-movies-application-in-android-using-retrofit
*/

package com.example.uuj.moviebuddy;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Owner on 10/03/2020.
 */

public class Client {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient()  {
        if (retrofit == null)   {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
