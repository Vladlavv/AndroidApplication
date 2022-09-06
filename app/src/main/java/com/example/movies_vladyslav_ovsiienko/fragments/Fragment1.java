package com.example.movies_vladyslav_ovsiienko.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies_vladyslav_ovsiienko.R;
import com.example.movies_vladyslav_ovsiienko.adapter.ItemAdapter;
import com.example.movies_vladyslav_ovsiienko.databinding.Fragment1Binding;
import com.example.movies_vladyslav_ovsiienko.models.Movie;
import com.example.movies_vladyslav_ovsiienko.models.MovieResponseObject;
import com.example.movies_vladyslav_ovsiienko.network.API;
import com.example.movies_vladyslav_ovsiienko.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment1 extends Fragment {

    private Fragment1Binding binding;

    public Fragment1(){
        super(R.layout.fragment_1);
    }

    private ArrayList<Movie> itemsList = new ArrayList<Movie>();

    private ItemAdapter adapter;

    private API api;

    //list of all movies
    private List<Movie> MovieFromApiList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //adapter
        binding.rvItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvItems.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new ItemAdapter(getActivity(), itemsList);
        binding.rvItems.setAdapter(adapter);

        //configure api
        this.api = RetrofitClient.getInstance().getApi();
        Call<MovieResponseObject> request = api.getMovie();
        request.enqueue(new Callback<MovieResponseObject>() {
            @Override
            public void onResponse(Call<MovieResponseObject> call, Response<MovieResponseObject> response) {
                if(response.isSuccessful() == false){
                    Log.d("API", "Error from API with response code" + response.code());
                    return;
                }
                //retrieve content
                MovieResponseObject obj = response.body();

                MovieFromApiList = obj.getResults();

                //update items list
                itemsList.clear();
                if(MovieFromApiList != null) {

                    itemsList.addAll(MovieFromApiList);
                    Log.d("API", "Get API results" + itemsList.size() );
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieResponseObject> call, Throwable t) {
                Log.d("API", t.getMessage());
            }
        });
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = Fragment1Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}