package com.example.movies_vladyslav_ovsiienko.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="purchases_table")
public class Purchase {
    @PrimaryKey(autoGenerate = false)
    private int id;
    private String movieTitle;
    private int quantity;

    public Purchase(int id, String movieTitle, int quantity){
        this.id = id;
        this.movieTitle = movieTitle;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
