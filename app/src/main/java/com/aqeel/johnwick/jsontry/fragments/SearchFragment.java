package com.aqeel.johnwick.jsontry.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.SearchView;
import android.widget.TextView;

import com.aqeel.johnwick.jsontry.R;
import com.aqeel.johnwick.jsontry.adapters.CatAdapter;
import com.aqeel.johnwick.jsontry.adapters.WallpaperAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends Fragment {

    SearchView searchView;
    RecyclerView recyclerView;
    List<String> cList;
    TextView textCat;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.search_fragment, container, false);

//String[] st =         {"fashion", "nature", "backgrounds", "science", "education", "people", "feelings", "religion", "health", "places", "animals", "industry", "food", "computer", "sports", "transportation", "travel", "buildings", "business", "music"};
        String[] st =         {"fashion", "nature", "backgrounds"};

        textCat = v.findViewById(R.id.search_text_cat);
        textCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {







            }
        });
        cList = Arrays.asList(st);

        searchView = v.findViewById(R.id.search_searchview);
searchView.setSubmitButtonEnabled(false);



recyclerView = v.findViewById(R.id.search_recycler_recycler);


        loadRecycler();

        return v;


    }

    void loadRecycler(){
        recyclerView.setAdapter(new CatAdapter(cList,getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }



}
