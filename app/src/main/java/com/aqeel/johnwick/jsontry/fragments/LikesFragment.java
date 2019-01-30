package com.aqeel.johnwick.jsontry.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aqeel.johnwick.jsontry.R;
import com.aqeel.johnwick.jsontry.adapters.WallpaperAdapter;
import com.aqeel.johnwick.jsontry.extras.AppDatabase;
import com.aqeel.johnwick.jsontry.models.Wallpaper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


public class LikesFragment extends Fragment {

    RecyclerView recyclerView;
    AppDatabase db;
    List<Wallpaper> wallpaperList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.likes_fragment, container, false);
        recyclerView = v.findViewById(R.id.likes_recycler_recycler);


        db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "roomDatabase").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        wallpaperList = db.wallpaperDao().getAll();
        Toast.makeText(getContext(), wallpaperList.size() + " : size", Toast.LENGTH_SHORT).show();

        loadRecycler();


       return v;
    }

    void loadRecycler(){
        recyclerView.setAdapter(new WallpaperAdapter(wallpaperList,getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

}
