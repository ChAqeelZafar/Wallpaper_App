package com.aqeel.johnwick.jsontry.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.aqeel.johnwick.jsontry.R;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FullImageFragment extends Fragment {
    String url ;
            ImageView imageView1;
    ImageButton imageButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.full_image_fragment, container, false);

        imageView1 = v.findViewById(R.id.fullimage_img_img);
        imageButton = v.findViewById(R.id.fullimage_btn_download);

        imageButton.setBackgroundResource(R.drawable.downloadwall);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.setBackgroundResource(R.drawable.tickwall);
            }
        });


        Bundle bundle = getArguments();
        if(bundle!=null) {
            url = getArguments().getString("fullImgLink");
        }
        Toast.makeText(getContext(), url, Toast.LENGTH_SHORT).show();


        if(!url.equals("")){
            Glide.with(v.getContext()).load(url).into(imageView1);




        }

        return v;


    }
}
