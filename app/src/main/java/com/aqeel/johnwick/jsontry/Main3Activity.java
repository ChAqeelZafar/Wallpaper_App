package com.aqeel.johnwick.jsontry;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.aqeel.johnwick.jsontry.fragments.AboutFragment;
import com.aqeel.johnwick.jsontry.fragments.HomeFragment;
import com.aqeel.johnwick.jsontry.fragments.LikesFragment;
import com.aqeel.johnwick.jsontry.fragments.SearchFragment;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Main3Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


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
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);
        MenuItem item = menu.findItem(R.id.menu_main);
        item.setVisible(false);
        return true;
    }


}
