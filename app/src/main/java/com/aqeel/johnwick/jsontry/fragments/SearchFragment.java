package com.aqeel.johnwick.jsontry.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.aqeel.johnwick.jsontry.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import br.com.mauker.materialsearchview.MaterialSearchView;

public class SearchFragment extends Fragment {
    MaterialSearchView searchView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.search_fragment, container, false);
        searchView = v.findViewById(R.id.search_view);


        String sug[] = {"fashion", "nature", "backgrounds", "science", "education", "people", "feelings", "religion", "health", "places", "animals", "industry", "food", "computer", "sports", "transportation", "travel", "buildings", "business", "music" };
       searchView.addSuggestions(sug);


        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Do something when the suggestion list is clicked.
                String suggestion = searchView.getSuggestionAtPosition(position);

                searchView.setQuery(suggestion, true);
            }
        });







        return v;


    }

}
