package com.example.it_sep4_a20_app.ui.nightoverview;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.it_sep4_a20_app.R;

public class NightOverviewFragment extends Fragment
{

    //Overview
    private TextView mTempMin, mTempMax, mTempAvg,
            mHumiMin, mHumiMax, mHumiAvg,
            mCo2Min, mCo2Max, mCo2Avg;

    //Last 7 days
    private TextView mTempMin7days, mTempMax7days, mTempAvg7days,
            mHumiMin7days, mHumiMax7days, mHumiAvg7days,
            mCo2Min7days, mCo2Max7days, mCo2Avg7days;

    private ImageView mTempImage, mCo2Image, mHumiImage;

    private NightOverviewViewModel mViewModel;
    private SharedPreferences mPrefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_nightoverview, container, false);
        findViews(root);
        mViewModel = new ViewModelProvider(this).get(NightOverviewViewModel.class);
        mPrefs = root.getContext().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setObserver();
        return root;
    }

    private void setObserver()
    {
        mViewModel.getNightOverview().observe(getViewLifecycleOwner(), nightOverview ->
        {
            mTempMin.setText(getResources().getString(R.string.text_temperature_min, nightOverview.getTempMin()));
            mTempMax.setText(getResources().getString(R.string.text_temperature_max, nightOverview.getTempMax()));
            mTempAvg.setText(getResources().getString(R.string.text_temperature_avg, nightOverview.getTempAvg()));

            mHumiMin.setText(getResources().getString(R.string.text_humidity_min, nightOverview.getHumiMin()));
            mHumiMax.setText(getResources().getString(R.string.text_humidity_max, nightOverview.getHumiMax()));
            mHumiAvg.setText(getResources().getString(R.string.text_humidity_avg, nightOverview.getHumiAvg()));

            mCo2Min.setText(getResources().getString(R.string.text_co2_min, nightOverview.getCo2Min()));
            mCo2Max.setText(getResources().getString(R.string.text_co2_max, nightOverview.getCo2Max()));
            mCo2Avg.setText(getResources().getString(R.string.text_co2_avg, nightOverview.getCo2Avg()));

            mTempMin7days.setText(getResources().getString(R.string.text_temperature_min, nightOverview.getTempMin7days()));
            mTempMax7days.setText(getResources().getString(R.string.text_temperature_max, nightOverview.getTempMax7days()));
            mTempAvg7days.setText(getResources().getString(R.string.text_temperature_avg, nightOverview.getTempAvg7days()));

            mCo2Min7days.setText(getResources().getString(R.string.text_humidity_min, nightOverview.getCo2Min7days()));
            mCo2Max7days.setText(getResources().getString(R.string.text_humidity_max, nightOverview.getCo2Max7days()));
            mCo2Avg7days.setText(getResources().getString(R.string.text_humidity_avg, nightOverview.getCo2Avg7days()));

            mHumiMin7days.setText(getResources().getString(R.string.text_co2_min, nightOverview.getHumiMin7days()));
            mHumiMax7days.setText(getResources().getString(R.string.text_co2_max, nightOverview.getHumiMax7days()));
            mHumiAvg7days.setText(getResources().getString(R.string.text_co2_avg, nightOverview.getHumiAvg7days()));

            if(mViewModel.isPreferredTemperature(nightOverview.getTempAvg()))
            {
                mTempImage.setImageResource(R.drawable.ic_baseline_check_box_24);
            }
            else
            {
                mTempImage.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24);
            }

            if(mViewModel.isPreferredCo2(nightOverview.getCo2Avg()))
            {
                mCo2Image.setImageResource(R.drawable.ic_baseline_check_box_24);
            }
            else
            {
                mCo2Image.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24);
            }

            if(mViewModel.isPreferredHumidity(nightOverview.getHumiAvg()))
            {
                mHumiImage.setImageResource(R.drawable.ic_baseline_check_box_24);
            }
            else
            {
                mHumiImage.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24);
            }
        });
    }

    private void findViews(@NonNull View root)
    {
        //Temperature
        mTempMin = root.findViewById(R.id.text_temperature_min);
        mTempMax = root.findViewById(R.id.text_temperature_max);
        mTempAvg = root.findViewById(R.id.text_temperature_avg);
        //Co2
        mCo2Min = root.findViewById(R.id.text_co2_min);
        mCo2Max = root.findViewById(R.id.text_co2_max);
        mCo2Avg = root.findViewById(R.id.text_co2_avg);
        //Humidity
        mHumiMin = root.findViewById(R.id.text_humidity_min);
        mHumiMax = root.findViewById(R.id.text_humidity_max);
        mHumiAvg = root.findViewById(R.id.text_humidity_avg);

        //Temperature 7 days
        mTempMin7days = root.findViewById(R.id.text_last7days_temperature_min);
        mTempMax7days = root.findViewById(R.id.text_last7days_temperature_max);
        mTempAvg7days = root.findViewById(R.id.text_last7days_temperature_avg);
        //Co2 7 days
        mCo2Min7days = root.findViewById(R.id.text_last7days_co2_min);
        mCo2Max7days = root.findViewById(R.id.text_last7days_co2_max);
        mCo2Avg7days = root.findViewById(R.id.text_last7days_co2_avg);
        //Humidity 7 days
        mHumiMin7days = root.findViewById(R.id.text_last7days_humidity_min);
        mHumiMax7days = root.findViewById(R.id.text_last7days_humidity_max);
        mHumiAvg7days = root.findViewById(R.id.text_last7days_humidity_avg);

        //Images
        mTempImage = root.findViewById(R.id.image_temp);
        mCo2Image = root.findViewById(R.id.image_co2);
        mHumiImage = root.findViewById(R.id.image_humidity);
    }
}
