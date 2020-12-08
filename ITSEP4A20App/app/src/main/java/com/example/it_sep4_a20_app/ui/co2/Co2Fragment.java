package com.example.it_sep4_a20_app.ui.co2;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.it_sep4_a20_app.R;
import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Co2Fragment extends Fragment {

    private Co2ViewModel mViewModel;

    private TextView mCo2Reading;
    private TextView mTemperatureReading;
    private TextView mHumidityReading;

    private PieChart chartCo2;
    private PieChart chartTemperature;

    private final int MAX_CO2 = 1500;

    public static Co2Fragment newInstance() {
        return new Co2Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_co2, container, false);
        mCo2Reading = root.findViewById(R.id.textView_co2Reading);
        mHumidityReading = root.findViewById(R.id.textView_humidityReading);
        mTemperatureReading = root.findViewById(R.id.textView_temperatureReading);

        chartCo2 = root.findViewById(R.id.chart_co2);
        chartCo2.setMaxAngle(270f);
        chartCo2.setRotation(0f);
        chartCo2.animateY(1400, Easing.EaseInOutQuad);


        return root;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Co2ViewModel.class);
        mViewModel.getLiveMeasurements().observe(getViewLifecycleOwner(), new Observer<LiveMeasurements>() {
            @Override
            public void onChanged(LiveMeasurements liveMeasurements) {
                int co2 = liveMeasurements.getCarbonDioxide();
                int humidity = liveMeasurements.getHumidityPercentage();
                double temperature = liveMeasurements.getTemperature();
                String co2Reading = co2 + " ppm";
                String temperatureReading = temperature + " °C";
                String humidityReading = humidity + " %";
                setDataCo2(2, co2, MAX_CO2);
                mCo2Reading.setText(co2Reading);
                mHumidityReading.setText(humidityReading);
                mTemperatureReading.setText(temperatureReading);
            }
        });
    }

    public void setDataCo2(int count, int value, float range) {
        ArrayList<PieEntry> values = new ArrayList<>();

        values.add(new PieEntry((float) value));
        values.add(new PieEntry((float) range - value));

        PieDataSet dataSet = new PieDataSet(values, "Co2 live reading");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        chartCo2.setData(data);

        chartCo2.invalidate();
    }


}