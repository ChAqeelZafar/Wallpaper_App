package com.aqeel.johnwick.jsontry.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aqeel.johnwick.jsontry.R;
import com.aqeel.johnwick.jsontry.adapters.CatAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFragment extends Fragment {


    RecyclerView recyclerView;
    List<String> cList = new ArrayList<>();
    TextView textCat;


    MaterialSearchView searchView;
    //String urlFix = "https://pixabay.com/api/?key=11352394-967dbfe8727b610ee5d635714&image_type=photo&orientation=vertical&editors_choice=true&order=popular&pretty=true&per_page=3&category=";

    //List<Wallpaper> wList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.search_fragment, container, false);






        final String[] st =         {"fashion", "nature", "backgrounds", "science", "education", "people", "feelings", "religion", "health", "places", "animals", "industry", "food", "computer", "sports", "transportation", "travel", "buildings", "business", "music"};




        cList = Arrays.asList(st);






        recyclerView = v.findViewById(R.id.search_recycler_recycler);


        loadRecycler();

        return v;















//        firestore = FirebaseFirestore.getInstance();
//
//
//        firestore.collection("categoriesUrl")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                String name = document.get("name").toString();
//                                String url = document.get("url").toString();
//                                Wallpaper w = new Wallpaper(url, name);
//                                wList.add(w);
//                                loadRecycler();
//                            }
//                        } else {
//                            Toast.makeText(getContext(), "Error getting documents.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });




























//final String[] st =         {"fashion", "nature", "backgrounds", "science", "education", "people", "feelings", "religion", "health", "places", "animals", "industry", "food", "computer", "sports", "transportation", "travel", "buildings", "business", "music"};
//        //String[] st =         {"fashion", "nature", "backgrounds"};
//        final RequestQueue queue = Volley.newRequestQueue(getContext());
//
//        for(int j=0; j<st.length;j++){
//            String url = urlFix + st[j];
//            final int finalJ = j;
//            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    //Toast.makeText(MainActivity.this,"DONE", Toast.LENGTH_SHORT).show();
//                    try {
//                        JSONArray jsonArray = response.getJSONArray("hits");
//                        Random r = new Random();
//                        int ran = (r.nextInt(2) + 0);
//                            String nUrl = jsonArray.getJSONObject(ran).getString("previewURL");
//                            Wallpaper w = new Wallpaper(nUrl, st[finalJ]);
//                            wList.add(w);
//                            //Collections.shuffle(wList);
//                            //Toast.makeText(getContext(), "inner", Toast.LENGTH_LONG).show();
//                            loadRecycler();
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//
//
//                    }
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getContext(),error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//            queue.add(jsonObjectRequest);
//
 //       }


//        searchView = v.findViewById(R.id.search_searchview);
//        searchView.setSubmitButtonEnabled(false);
//
//
//
//        recyclerView = v.findViewById(R.id.search_recycler_recycler);
//
//
//        loadRecycler();
//
//        return v;


    }


    void loadRecycler(){
        recyclerView.setAdapter(new CatAdapter(cList,getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }
//    void loadRecycler(){
//        recyclerView.setAdapter(new CatAdapter(wList,getContext()));
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
//    }




    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            ((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, fragment).addToBackStack("search_fragment").commit();
            return true;
        }
        return false;
    }

}
