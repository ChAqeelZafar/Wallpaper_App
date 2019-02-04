package com.aqeel.johnwick.jsontry.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.aqeel.johnwick.jsontry.R;
import com.aqeel.johnwick.jsontry.adapters.WallpaperAdapter;
import com.aqeel.johnwick.jsontry.models.Wallpaper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView1;
    List<Wallpaper> wList = new ArrayList<>();
    TextView textView;
    String url;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);

        wList.clear();
        url ="https://pixabay.com/api/?key=11352394-967dbfe8727b610ee5d635714&orientation=vertical&pretty=true&per_page=10";

        Bundle bundle = getArguments();
        if(bundle!=null) {
            if(bundle.containsKey("searchKey")){
                String cat = getArguments().getString("searchKey");
                url = url + "&q=" + cat;
            }else {
                String cat = getArguments().getString("category");
                url = url + "&category=" + cat;
            }

        }



        recyclerView1 = v.findViewById(R.id.home_recycler_recycler);

        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(MainActivity.this,"DONE", Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for(int i=0; i <jsonArray.length(); i++){
                        String pUrl = jsonArray.getJSONObject(i).getString("previewURL");
                        String wUrl = jsonArray.getJSONObject(i).getString("webformatURL");
                        String hUrl = jsonArray.getJSONObject(i).getString("fullHDURL");
                        String lUrl = jsonArray.getJSONObject(i).getString("largeImageURL");

                        int views  = jsonArray.getJSONObject(i).getInt("views");
                        int downloads  = jsonArray.getJSONObject(i).getInt("downloads");
                        int favorites = jsonArray.getJSONObject(i).getInt("favorites");
                        int likes = jsonArray.getJSONObject(i).getInt("likes");
                        int comments = jsonArray.getJSONObject(i).getInt("comments");
                        int imageHeight = jsonArray.getJSONObject(i).getInt("imageHeight");
                        int imageWidth = jsonArray.getJSONObject(i).getInt("imageWidth");
                        int imageSize = jsonArray.getJSONObject(i).getInt("imageSize");

                        String user = jsonArray.getJSONObject(i).getString("user");
                        String tags = jsonArray.getJSONObject(i).getString("tags");

                        String imgDetails =
                                "\nUser : " + user +
                                        "\nTags : " + tags +
                                "\nTotal Downloads : " + downloads +
                                "\nTotal Views : " + views +
                                "\nFavorities : " + favorites +
                                "\nTotal Likes : " + likes +
                                "\nComments : " + comments +
                                "\nImage height : " + imageHeight+
                                "\nImage width : " + imageWidth +
                                "\nImage Size : " + imageSize/1048576 + " Mb";
                       Wallpaper w = new Wallpaper(pUrl,wUrl,lUrl,hUrl,false, imgDetails);


                        //Wallpaper w = new Wallpaper(nUrl, hUrl, lUrl, false);
                        wList.add(w);
                        Collections.shuffle(wList);
                        //Toast.makeText(getContext(), "inner", Toast.LENGTH_LONG).show();
                        loadrecycler();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Api did not work", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(jsonObjectRequest);

        return v;


    }
    void loadrecycler(){
        recyclerView1.setAdapter(new WallpaperAdapter(wList,getContext()));
        recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),3));
    }
}
