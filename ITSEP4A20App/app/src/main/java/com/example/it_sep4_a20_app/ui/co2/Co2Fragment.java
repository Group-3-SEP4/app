package com.example.it_sep4_a20_app.ui.co2;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.it_sep4_a20_app.R;

public class Co2Fragment extends Fragment {

    private Co2ViewModel mViewModel;

    private TextView mCo2Reading;

    public static Co2Fragment newInstance() {
        return new Co2Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_co2, container, false);
        mCo2Reading = root.findViewById(R.id.textView_co2);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Co2ViewModel.class);
        mViewModel.getCO2().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                String co2String = "CO2 Level: " + aDouble + " ppm";
                mCo2Reading.setText(co2String);
            }
        });
    }

}