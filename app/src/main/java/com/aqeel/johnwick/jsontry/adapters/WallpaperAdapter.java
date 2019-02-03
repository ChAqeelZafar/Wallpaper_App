package com.aqeel.johnwick.jsontry.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.aqeel.johnwick.jsontry.R;
import com.aqeel.johnwick.jsontry.fragments.FullImageFragment;
import com.aqeel.johnwick.jsontry.models.Wallpaper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.ybq.android.spinkit.style.ChasingDots;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.Holder>{
    List<Wallpaper> wList = new ArrayList<>() ;
    Context ctx;
    ChasingDots chasingDots = new ChasingDots();

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
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        holder.progressBar.setVisibility(View.VISIBLE);

        final Wallpaper wallpaper = wList.get(position);
        String url = wallpaper.getWebformatURL();
        if(!url.equals("")){
            Glide.with(this.ctx).load(url).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.progressBar.setVisibility(View.GONE);

                    return false;
                }
            }).into(holder.imageView);

        }



        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullImageFragment fullImageFragment = new FullImageFragment ();
                Bundle args = new Bundle();
                args.putString("previewImageURL", wallpaper.getUrl());
                args.putString("webformatURL", wallpaper.getWebformatURL());
                args.putString("largeImageURL", wallpaper.getLargeImageURL());
                args.putString("fullHdUrl", wallpaper.getHighUrl());
                args.putBoolean("isFav", wallpaper.getFav());
                args.putString("imgDetails", wallpaper.getImgDetails());


                fullImageFragment.setArguments(args);
//                Intent intent = new Intent(ctx, Main2Activity.class);
//                ctx.startActivity(intent);
                loadFragment(fullImageFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ProgressBar progressBar;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.viewholder_img_img);
            progressBar = itemView.findViewById(R.id.viewholder_progressbar);
            progressBar.setVisibility(View.VISIBLE);

        }
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            ((AppCompatActivity)ctx).getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, fragment).addToBackStack("home_fragment").commit();
            return true;
        }
        return false;
    }
}
