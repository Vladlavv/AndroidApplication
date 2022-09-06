package com.example.movies_vladyslav_ovsiienko.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movies_vladyslav_ovsiienko.models.Purchase;

import java.util.List;

@Dao
public interface PurchaseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert (Purchase purchaseToInsert);

    @Query("SELECT * FROM purchases_table")
    public List<Purchase> getAllPurchases();

    @Query("SELECT * FROM purchases_table WHERE id = :purchaseId")
    public Purchase getPurchaseById(int purchaseId);

    @Update
    public void update(Purchase purchaseToUpdate);


    @Delete
    public void delete(Purchase purchaseToDelete);
}
