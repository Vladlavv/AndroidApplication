package com.example.movies_vladyslav_ovsiienko.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies_vladyslav_ovsiienko.databinding.CustomRowLayoutBinding;
import com.example.movies_vladyslav_ovsiienko.db.MyDatabase;
import com.example.movies_vladyslav_ovsiienko.models.Movie;
import com.example.movies_vladyslav_ovsiienko.models.Purchase;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final Context context;
    private final ArrayList<Movie> itemArrayList;
    CustomRowLayoutBinding binding;


    public ItemAdapter(Context context, ArrayList<Movie> items){
        this.itemArrayList = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(CustomRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Movie currentItem = itemArrayList.get(position);
        holder.bind(context, currentItem);
    }

    @Override
    public int getItemCount() {
        Log.d("ItemAdapter", "getItemCount: Number of items " +this.itemArrayList.size() );
        return this.itemArrayList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        CustomRowLayoutBinding itemBinding;
        private MyDatabase db = MyDatabase.getDatabase(itemView.getContext());;

        public ItemViewHolder(CustomRowLayoutBinding binding){
            super(binding.getRoot());
            this.itemBinding = binding;
        }



        public void bind(Context context, Movie currentItem){
            // TODO: Write the code to update recycler view's row layout


            float rating = currentItem.getVote_average() * 10;
            int ratingInt = (int) rating;
            String ratingString =  Integer.toString(ratingInt) + "%";
            String releaseString = "Released: " + currentItem.getRelease_date();
            String imageURL = "https://image.tmdb.org/t/p/w500/" + currentItem.getBackdrop_path();
            itemBinding.tvName.setText(currentItem.getTitle());
            itemBinding.tvRating.setText(ratingString);
            itemBinding.tvRelease.setText(releaseString);
            itemBinding.tvDescription.setText(currentItem.getOverview());


            Glide.with(context)
                    .load(imageURL)
                    .into(itemBinding.ivImage);

            itemBinding.btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Purchase purchaseFromDB = db.purchaseDAO().getPurchaseById(currentItem.getId());
                    if(purchaseFromDB == null){
                        Purchase purchase = new Purchase(currentItem.getId(), currentItem.getTitle(), 1);
                        db.purchaseDAO().insert(purchase);;
                    }else{
                        purchaseFromDB.setQuantity(purchaseFromDB.getQuantity() + 1);
                        db.purchaseDAO().update(purchaseFromDB);
                    }
                    Snackbar.make(itemBinding.getRoot(), "Ticket Purchased", Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }
}
