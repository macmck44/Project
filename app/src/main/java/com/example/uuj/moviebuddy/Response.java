/*
TheMovieDB implementation sourced from here:
https://developers.themoviedb.org/3/getting-started/introduction
https://www.supinfo.com/articles/single/7849-developing-popular-movies-application-in-android-using-retrofit
*/

package com.example.uuj.moviebuddy;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Owner on 10/03/2020.
 */

public class Response {

    @SerializedName("results")
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public List<Movie> getMovies() {
        return results;
    }

}
