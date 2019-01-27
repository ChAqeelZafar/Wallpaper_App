package com.aqeel.johnwick.jsontry.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqeel.johnwick.jsontry.R;
import com.aqeel.johnwick.jsontry.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.Holder>{
    List<String> cList = new ArrayList<>() ;
    Context ctx;

    public CatAdapter(List<String> cList, Context ctx) {
        this.cList = cList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_category,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
       final String catName = cList.get(position);

       holder.textView.setText(catName.toUpperCase());
       holder.imageView.setImageResource(getImageId(ctx, catName));

       holder.parentCard.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               HomeFragment homeFragment = new HomeFragment ();
               Bundle args = new Bundle();
               args.putString("category", catName);
               homeFragment.setArguments(args);
               loadFragment(homeFragment);
           }
       });

    }

    @Override
    public int getItemCount() {
        return cList.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        CardView parentCard;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.viewholder_cat_img);
            textView = itemView.findViewById(R.id.viewholder_cat_name);
            parentCard = itemView.findViewById(R.id.viewholder_cat_cardview);
        }
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            ((AppCompatActivity)ctx).getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, fragment).addToBackStack("home_fragment").commit();
            return true;
        }
        return false;
    }
}
