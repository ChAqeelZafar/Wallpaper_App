package com.aqeel.johnwick.jsontry.fragments;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import com.aqeel.johnwick.jsontry.extras.AppDatabase;
import com.aqeel.johnwick.jsontry.models.Wallpaper;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

public class FullImageFragment extends Fragment {
    String urlLargeImg, urlPreviewImg, urlFullHd, urlToLoad, imgDetails, urlWebImg ;
    ImageView imageView1 ;
    ImageButton downloadImgBtn, favImgBtn;


    Boolean isFav = false, isDownloaded = false, isFullHd=false, isBlurred=false;

    AppDatabase db;


    private RewardedVideoAd mRewardedVideoAd;


    private int requestCode;
    private int grantResults[];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);

        View v = inflater.inflate(R.layout.full_image_fragment, container, false);

        rewardedAdSetup();
        loadRewardedVideoAd();




        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},requestCode);


        onRequestPermissionsResult(requestCode,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},grantResults);


        db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "roomDatabase").allowMainThreadQueries().fallbackToDestructiveMigration().build();


        imageView1 = v.findViewById(R.id.fullimage_img_img);

        favImgBtn = v.findViewById(R.id.fullimage_btn_favourite);
        downloadImgBtn = v.findViewById(R.id.fullimage_btn_download);

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
                if(!isDownloaded) {
                    downloadImgBtn.setBackgroundResource(R.drawable.tickwall);
                    downloadImg();
                }
                else{
                    Toast.makeText(getContext(), "Already Downloaded", Toast.LENGTH_LONG).show();
                }
            }
        });




        Bundle bundle = getArguments();
        if(bundle!=null) {
            urlLargeImg = getArguments().getString("largeImageURL");
            urlPreviewImg = getArguments().getString("previewImageURL");
            urlWebImg = getArguments().getString("webformatURL");
            urlFullHd = getArguments().getString("fullHdUrl");
            isFav = getArguments().getBoolean("isFav");
            imgDetails = getArguments().getString("imgDetails");


        }
        Toast.makeText(getContext(), urlLargeImg, Toast.LENGTH_SHORT).show();



        if(!isFav){
            favImgBtn.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);

        }else{
            favImgBtn.setBackgroundResource(R.drawable.ic_favorite_black_24dp);

        }

     loadGlide();

        return v;

    }

    private void loadGlide() {
        if(!urlLargeImg.equals("") && !isFullHd){
            Glide.with(getContext()).load(urlLargeImg).into(imageView1);
        }
        else       if(!urlFullHd.equals("") && isFullHd){
            Glide.with(getContext()).load(urlFullHd).into(imageView1);
        }
    }


    @Override
    public void onResume() {
        mRewardedVideoAd.resume(getContext());
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(getContext());
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(getContext());
        super.onDestroy();
    }






    private void rewardedAdSetup() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getContext());
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                Toast.makeText(getContext(),"onRewardedVideoAdLoaded()",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onRewardedVideoAdOpened() {
                Toast.makeText(getContext(),"onRewardedVideoAdOpened()",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedVideoStarted() {
                Toast.makeText(getContext(),"onRewardedVideoStarted()",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onRewardedVideoAdClosed() {
                Toast.makeText(getContext(),"onRewardedVideoAdClosed()",Toast.LENGTH_LONG).show();

                loadRewardedVideoAd();


            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                Toast.makeText(getContext(),"Full Hd image is loading",Toast.LENGTH_LONG).show();

                isFullHd = true;
                loadGlide();

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                Toast.makeText(getContext(),"onRewardedVideoAdLeftApplication()",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {
                Toast.makeText(getContext(),"onRewardedVideoAdFailedToLoad",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onRewardedVideoCompleted() {
                Toast.makeText(getContext(),"onRewardedVideoCompleted()",Toast.LENGTH_LONG).show();
                isFullHd = true;

            }
        });

    }

    private void loadRewardedVideoAd() {
        Toast.makeText(getContext(),"LoadVideoAD", Toast.LENGTH_LONG).show();
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
    }


    @Override // android recommended class to handle permissions
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "permission granted", Toast.LENGTH_SHORT).show();


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.uujm
                    Toast.makeText(getContext(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

                    //app cannot function without this permission for now so close it...
                    onDestroy();
                }
                return;
            }

            // other 'case' line to check fosr other
            // permissions this app might request
        }
    }

    private void downloadImg() {



        Bitmap bmp = ((BitmapDrawable) imageView1.getDrawable()).getBitmap();


        File storageLoc = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); //context.getExternalFilesDir(null);
        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        File file = new File(storageLoc, fileName + ".jpg");

        try{
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            scanFile(getContext(), Uri.fromFile(file));
            Toast.makeText(getContext(),"Wallpaper Downloded", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }




    }

    private static void scanFile(Context context, Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        context.sendBroadcast(scanIntent);

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
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                    Toast.makeText(getContext(),"In isLoadedVideoAD", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getContext(),"Error LoadVideoAD", Toast.LENGTH_LONG).show();

                }
                //Toast.makeText(getContext(), "high res clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.actionmenu_detail:
                DetailsFragment detailsFragment= new DetailsFragment ();
                Bundle args = new Bundle();
                args.putString("imgDetails", imgDetails);
                args.putString("urlImgBlur", urlWebImg);



                detailsFragment.setArguments(args);
                    loadFragment(detailsFragment);



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
        Wallpaper wallpaperTemp = new Wallpaper(urlPreviewImg,  urlWebImg,urlLargeImg, urlFullHd, true, imgDetails);

        if(isFav){
            isFav = false;
        }
        else{
            isFav = true;
        }
        if(isFav){
            favImgBtn.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
            db.wallpaperDao().deleteWallpaper(wallpaperTemp.getLargeImageURL());
            db.wallpaperDao().insertAll(wallpaperTemp);
            Toast.makeText(getContext(),"Room Added", Toast.LENGTH_SHORT).show();
            //addFavToFirestore();

        }
        else{
            favImgBtn.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
            db.wallpaperDao().deleteWallpaper(wallpaperTemp.getLargeImageURL());
            Toast.makeText(getContext(),"Room Deleted", Toast.LENGTH_SHORT).show();

        }
    }

//    void addFavToFirestore(){
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//
//        Map<String, Object> wallpaperFirestore = new HashMap<>();
//        wallpaperFirestore.put("urlPreviewImg", urlPreviewImg);
//        wallpaperFirestore.put("urlLargeImg", urlLargeImg);
//        wallpaperFirestore.put("urlFullHd", urlFullHd);
//
//// Add a new document with a generated ID
//        firestore.collection("favourites").document(uid).collection(uid)
//                .add(wallpaperFirestore)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getContext(), "Added succesfully", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getContext(), "Added succesfully", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//    }


    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            ((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, fragment).addToBackStack("full_image_fragment").commit();
            return true;
        }
        return false;
    }


}
