package com.aqeel.johnwick.jsontry;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.aqeel.johnwick.jsontry.fragments.AboutFragment;
import com.aqeel.johnwick.jsontry.fragments.HomeFragment;
import com.aqeel.johnwick.jsontry.fragments.LikesFragment;
import com.aqeel.johnwick.jsontry.fragments.SearchFragment;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

public class Main3Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {

    SearchView searchView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setIcon(R.mipmap.ic_launcher);





        MobileAds.initialize(this, getString(R.string.admobId));
        //getActionBar().setTitle("Tyle-Wallpapers");

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());
    }



    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){

            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, fragment).commit();
            //getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, fragment).addToBackStack("home_fragment").commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        


        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_search:
                fragment = new SearchFragment();
                break;

            case R.id.navigation_about:
                fragment = new AboutFragment();
                break;
            case R.id.navigation_likes:
                fragment = new LikesFragment();
                break;
        }

        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbarmenu, menu);
        MenuItem item = menu.findItem(R.id.menu_main);
        item.setVisible(false);




        // Associate searchable configuration with the SearchView
        final SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));


        SearchView searchViewL = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                //searchView.setIconified(true);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(Main3Activity.this, "Query Text Changed" + newText, Toast.LENGTH_LONG).show();
//              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
                //callSearch(newText);
//              }
                return true;
            }

            public void callSearch(String query) {
                query = query.replace(' ','+');
                Toast.makeText(Main3Activity.this, query, Toast.LENGTH_LONG).show();
                HomeFragment homeFragment = new HomeFragment();
                Bundle args = new Bundle();
                args.putString("searchKey" , query);
                homeFragment.setArguments(args);
                loadFragment(homeFragment);


            }

        });

        return true;
    }

    @Override
    public void onBackPressed() {
        searchView.onActionViewCollapsed();
        super.onBackPressed();
    }



}
