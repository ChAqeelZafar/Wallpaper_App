package com.aqeel.johnwick.jsontry.fragments;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.aqeel.johnwick.jsontry.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FullImageFragment extends Fragment {
    String urlLargeImg, urlPreviewImg, urlFullHd ;
    ImageView imageView1;
    ImageButton downloadImgBtn, favImgBtn;
    FirebaseFirestore firestore;

    Boolean isFav = false;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);

        View v = inflater.inflate(R.layout.full_image_fragment, container, false);
        firestore = FirebaseFirestore.getInstance();

        imageView1 = v.findViewById(R.id.fullimage_img_img);

        favImgBtn = v.findViewById(R.id.fullimage_btn_favourite);
        downloadImgBtn = v.findViewById(R.id.fullimage_btn_download);

        favImgBtn.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        downloadImgBtn.setBackgroundResource(R.drawable.downloadwall);

        favImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favImgBtnClicked();
            }
        });


        downloadImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImgBtn.setBackgroundResource(R.drawable.tickwall);
            }
        });


        Bundle bundle = getArguments();
        if(bundle!=null) {
            urlLargeImg = getArguments().getString("largeImageURL");
            urlPreviewImg = getArguments().getString("previewImageURL");
            urlFullHd = getArguments().getString("fullHdUrl");

        }
        Toast.makeText(getContext(), urlLargeImg, Toast.LENGTH_SHORT).show();

        //url = "data:image/jpeg;base64,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";
        if(!urlLargeImg.equals("")){
            Glide.with(v.getContext()).load(urlLargeImg).into(imageView1);




        }

        return v;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actionbarmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.actionmenu_setAs:
                Toast.makeText(getContext(), "set as clicked", Toast.LENGTH_SHORT).show();
                setAsWallpaper();
                return true;
            case R.id.actionmenu_high:
                Toast.makeText(getContext(), "high res clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setAsWallpaper(){

        Bitmap bmpImg = ((BitmapDrawable)imageView1.getDrawable()).getBitmap();

        WallpaperManager wallManager = WallpaperManager.getInstance(getContext());
        try {
            wallManager.setBitmap(bmpImg);
            Toast.makeText(getContext(), "Wallpaper Set Successfully!!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getContext(), "Setting WallPaper Failed!!", Toast.LENGTH_SHORT).show();
        }
    }

    void favImgBtnClicked(){
        if(isFav){
            isFav = false;
        }
        else{
            isFav = true;
        }
        if(isFav){
            favImgBtn.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
            addFavToFirestore();

        }
        else{
            favImgBtn.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);

        }
    }

    void addFavToFirestore(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        Map<String, Object> wallpaperFirestore = new HashMap<>();
        wallpaperFirestore.put("urlPreviewImg", urlPreviewImg);
        wallpaperFirestore.put("urlLargeImg", urlLargeImg);
        wallpaperFirestore.put("urlFullHd", urlFullHd);

// Add a new document with a generated ID
        firestore.collection("favourites").document(uid).collection(uid)
                .add(wallpaperFirestore)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "Added succesfully", Toast.LENGTH_SHORT).show();

                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Added succesfully", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
