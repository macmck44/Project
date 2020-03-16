package com.example.uuj.moviebuddy;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

/**
 * Created by Owner on 10/03/2020.
 */

public interface Service {

    @GET("movie/popular")
    Call<Response> getPopularMovies(@Query("api_key") String apikey);

    @GET("movie/top_rated")
    Call<Response> getTopRatedMovies(@Query("api_key") String apikey);

    @GET("movie/upcoming")
    Call<Response> getUpcomingMovies(@Query("api_key") String apikey);

    @GET("movie/now_playing")
    Call<Response> getNowPlayingMovies(@Query("api_key") String apikey);
}
