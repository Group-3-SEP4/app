package com.example.it_sep4_a20_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    //hey
    private TextView co2View;
    private MainActivityViewModel viewModel;

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
    }
}