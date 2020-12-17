package com.example.it_sep4_a20_app;


import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.it_sep4_a20_app.data.models.Device;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;
import java.util.List;

/**
 * @author Claire Zubiaurre
 */
public class MainActivity extends AppCompatActivity
{
    private MainActivityViewModel viewModel;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new MainActivityViewModel(getApplication());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.rgb(103, 62, 106));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder
                ( R.id.nav_liveReadings
                , R.id.nav_night_overview
                , R.id.nav_detailed_readings
                , R.id.nav_settings)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Lookup navigation view
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        // Inflate the header view at runtime
        //View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        // We can now look up items within the header if needed
        //ImageView ivHeaderPhoto = headerLayout.findViewById(R.id.imageView);

    }

    private void refreshOptionsMenu(Menu menu) {
        List<Device> devices = viewModel.getDevices();
        menu.clear();

        for (int i=0; i<devices.size(); i++) {
            MenuItem item = menu.add(devices.get(i).getName());

            int finalI = i;
            item.setOnMenuItemClickListener (new MenuItem.OnMenuItemClickListener(){
                @Override
                public boolean onMenuItemClick (MenuItem item){
                    viewModel.storeSelectedDeviceId(devices.get(finalI).getRoomId().toString());
                    return true;
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        refreshOptionsMenu(menu);

        ActiveDevice device = ActiveDevice.getInstance();
        device.registerOnDeviceChangeListener(new OnDeviceChangeListener() {
            @Override
            public void OnDeviceChange(Device device) {
                refreshOptionsMenu(menu);
            }
        });

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}