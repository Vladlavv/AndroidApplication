package com.example.movies_vladyslav_ovsiienko.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_vladyslav_ovsiienko.listeners.RowItemClickListener;
import com.example.movies_vladyslav_ovsiienko.databinding.CustomRowLayout2Binding;
import com.example.movies_vladyslav_ovsiienko.models.Purchase;

import java.util.ArrayList;

public class TicketItemAdapter extends RecyclerView.Adapter<TicketItemAdapter.ItemViewHolder> {

    private final Context context;
    private final ArrayList<Purchase> itemArrayList;
    CustomRowLayout2Binding binding;

    //click listener
    private final RowItemClickListener clickListener;


    public TicketItemAdapter(Context context, ArrayList<Purchase> items, RowItemClickListener clickListener){
        this.itemArrayList = items;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(CustomRowLayout2Binding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Purchase currentItem = itemArrayList.get(position);
        holder.bind(context, currentItem, clickListener);
    }

    @Override
    public int getItemCount() {
        Log.d("TicketItemAdapter", "getItemCount: Number of items " +this.itemArrayList.size() );
        return this.itemArrayList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        CustomRowLayout2Binding itemBinding;

        public ItemViewHolder(CustomRowLayout2Binding binding){
            super(binding.getRoot());
            this.itemBinding = binding;
        }



        public void bind(Context context, Purchase currentItem, RowItemClickListener clickListener){
            itemBinding.tvMovieTitle.setText(currentItem.getMovieTitle());
            itemBinding.tvTickets.setText("Tickets Purchased: " + currentItem.getQuantity());

            // setup click handler
            itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onRowItemClicked(currentItem);
                }
            });
        }
    }
}
