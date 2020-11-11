package com.example.it_sep4_a20_app;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import com.example.it_sep4_a20_app.ui.co2.Co2Fragment;
import com.example.it_sep4_a20_app.ui.home.HomeFragment;
import com.example.it_sep4_a20_app.ui.parameter.ParameterFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
{
    private TextView co2View;
    private MainActivityViewModel viewModel;
    private DrawerLayout drawerLayout;
    private NavigationView drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        co2View = findViewById(R.id.co2);
        viewModel.getCO2().observe(this, new Observer<Double>()
        {
            @Override
            public void onChanged(Double aDouble)
            {
                String co2String = "CO2 Level: " + aDouble;
                co2View.setText(co2String);
            }
        });


        drawerLayout = (DrawerLayout) findViewById(R.id.layout_drawer_activity);

        drawer = (NavigationView) findViewById(R.id.view);
        setupDrawerContent(drawer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.menu_item_home:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.menu_item_parameter:
                fragmentClass = ParameterFragment.class;
                break;
            case R.id.menu_item_co2:
                fragmentClass = Co2Fragment.class;
                break;
            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.menu_item_home, fragment).commit();


        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }



}