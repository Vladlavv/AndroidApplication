package com.example.movies_vladyslav_ovsiienko.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies_vladyslav_ovsiienko.R;
import com.example.movies_vladyslav_ovsiienko.listeners.RowItemClickListener;
import com.example.movies_vladyslav_ovsiienko.adapter.TicketItemAdapter;
import com.example.movies_vladyslav_ovsiienko.databinding.Fragment2Binding;
import com.example.movies_vladyslav_ovsiienko.db.MyDatabase;
import com.example.movies_vladyslav_ovsiienko.models.Purchase;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment implements RowItemClickListener {

    private Fragment2Binding binding;

    private MyDatabase db;

    private TicketItemAdapter adapter;

    private ArrayList<Purchase> itemsList = new ArrayList<Purchase>();


    public Fragment2(){
        super(R.layout.fragment_2);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        db = MyDatabase.getDatabase(getActivity().getApplicationContext());
        //db
        List<Purchase> purchaseList = db.purchaseDAO().getAllPurchases();
        itemsList.addAll(purchaseList);
        if(itemsList.size() > 0){
            binding.NoTickets.setVisibility(View.INVISIBLE);
        }

        //adapter
        binding.rvItems.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.rvItems.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        adapter = new TicketItemAdapter(getActivity(), itemsList, this);

        binding.rvItems.setAdapter(adapter);



    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRowItemClicked(Purchase purchase){
        db.purchaseDAO().delete(purchase);
        itemsList.remove(purchase);
        Snackbar.make(binding.getRoot(), "Deleted", Snackbar.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
        if(itemsList.size() == 0){
            binding.NoTickets.setVisibility(View.VISIBLE);
        }
    }
}