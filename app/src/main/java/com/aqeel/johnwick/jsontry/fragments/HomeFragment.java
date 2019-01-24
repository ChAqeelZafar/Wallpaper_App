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
import com.aqeel.johnwick.jsontry.Main3Activity;
import com.aqeel.johnwick.jsontry.MainActivity;
import com.aqeel.johnwick.jsontry.R;
import com.aqeel.johnwick.jsontry.adapters.WallpaperAdapter;
import com.aqeel.johnwick.jsontry.models.Wallpaper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView1 = view.findViewById(R.id.home_recycler_recycler);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
//        textView = v.findViewById(R.id.home_text);
//        textView.setText("Aqeel0");

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="https://pixabay.com/api/?key=11352394-967dbfe8727b610ee5d635714&orientation=vertical&editors_choice=true&image_type=photo&pretty=true&order=latest&per_page=20";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(MainActivity.this,"DONE", Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for(int i=0; i <jsonArray.length(); i++){
                        String nUrl = jsonArray.getJSONObject(i).getString("webformatURL");
                        String hUrl = jsonArray.getJSONObject(i).getString("largeImageURL");

                        Wallpaper w = new Wallpaper(nUrl, hUrl);
                        wList.add(w);
                        Toast.makeText(getContext(), "inner", Toast.LENGTH_LONG).show();
                        fun();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"NOT", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(jsonObjectRequest);
        //Toast.makeText(MainActivity.this, "size:" + wList.size(), Toast.LENGTH_SHORT).show();


        //return super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.home_fragment, null);


    }
    void fun(){
        recyclerView1.setAdapter(new WallpaperAdapter(wList,getContext()));
        recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),3));
    }
}
