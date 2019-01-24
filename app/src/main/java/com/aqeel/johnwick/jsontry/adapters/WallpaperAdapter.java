package com.aqeel.johnwick.jsontry.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aqeel.johnwick.jsontry.R;
import com.aqeel.johnwick.jsontry.models.Wallpaper;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.Holder>{
    List<Wallpaper> wList = new ArrayList<>() ;
    Context ctx;

    public WallpaperAdapter(List<Wallpaper> wList, Context ctx) {
        this.wList = wList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Wallpaper wallpaper = wList.get(position);
        String url = wallpaper.getUrl();
        if(!url.equals("")){
            Glide.with(this.ctx).load(url).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return wList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.viewholder_img_img);
        }
    }
}
