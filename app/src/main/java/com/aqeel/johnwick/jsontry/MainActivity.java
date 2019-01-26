package com.aqeel.johnwick.jsontry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aqeel.johnwick.jsontry.adapters.WallpaperAdapter;
import com.aqeel.johnwick.jsontry.models.Wallpaper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Wallpaper> wList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_recycler_recycler);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://pixabay.com/api/?key=11352394-967dbfe8727b610ee5d635714&orientation=vertical&editors_choice=true&image_type=photo&pretty=true&order=latest&per_page=5";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this,"DONE", Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for(int i=0; i <jsonArray.length(); i++){
                        String nUrl = jsonArray.getJSONObject(i).getString("previewURL");
                        String hUrl = jsonArray.getJSONObject(i).getString("largeImageURL");

                        Wallpaper w = new Wallpaper(nUrl, hUrl);
                        wList.add(w);
                        Toast.makeText(MainActivity.this, "inner", Toast.LENGTH_LONG).show();
                        fun();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"NOT", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(jsonObjectRequest);
        //Toast.makeText(MainActivity.this, "size:" + wList.size(), Toast.LENGTH_SHORT).show();



    }
    void fun(){
        recyclerView.setAdapter(new WallpaperAdapter(wList,this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
    }



}
