package com.example.uuj.moviebuddy;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Owner on 10/03/2020.
 */

public class Response {

    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<Movie> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public List<Movie> getMovies() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public void setMovies(List<Movie> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
