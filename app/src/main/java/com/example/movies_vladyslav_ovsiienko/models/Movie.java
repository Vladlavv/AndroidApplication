package com.example.movies_vladyslav_ovsiienko.models;
//https://image.tmdb.org/t/p/w500/
// nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg
//image example

public class Movie {

    public int id;
    public String overview;
    public String release_date;
    public String title;
    public String backdrop_path;
    public float vote_average;

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public float getVote_average() {
        return vote_average;
    }


}
