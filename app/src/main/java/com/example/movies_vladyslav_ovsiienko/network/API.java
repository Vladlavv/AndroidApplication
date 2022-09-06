package com.example.movies_vladyslav_ovsiienko.network;

import com.example.movies_vladyslav_ovsiienko.models.MovieResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;

// interface that retrofit will use to connect to the api endpoints
public interface API {
    // TODO: base url of the api
    public final String  BASE_URL = "https:api.themoviedb.org/3/movie/";

    // TODO: endpoints we want to connect to

    @GET("now_playing?api_key=565e164406aee399ef8faa8b5cb62774&language=en-US&page=1&region=CA")
    public Call<MovieResponseObject> getMovie();
}