package com.example.it_sep4_a20_app.ui.co2;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.companion.WifiDeviceFilter;
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

    private PieChart mChartCo2;
    private PieChart mChartTemperature;
    private PieChart mChartHumidity;

    private final int MAX_CO2 = 1500;
    private final int MAX_TEMPERATURE = 70;
    private final int MAX_HUMIDITY = 100;

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
        mCo2Reading.bringToFront();
        mHumidityReading.bringToFront();

        mChartCo2 = root.findViewById(R.id.chart_co2);
        mChartCo2.setMaxAngle(270f);
        mChartCo2.setRotation(0f);
        mChartCo2.animateY(1400, Easing.EaseInOutQuad);
        mChartCo2.setTouchEnabled(false);
        mChartCo2.getLegend().setEnabled(false);
        mChartCo2.getDescription().setEnabled(false);

        mChartTemperature = root.findViewById(R.id.chart_temperature);
        mChartTemperature.setMaxAngle(270f);
        mChartTemperature.setRotation(0f);
        mChartTemperature.animateY(1400, Easing.EaseInOutQuad);
        mChartTemperature.setTouchEnabled(false);
        mChartTemperature.getLegend().setEnabled(false);
        mChartTemperature.getDescription().setEnabled(false);

        mChartHumidity = root.findViewById(R.id.chart_humidity);
        mChartHumidity.setMaxAngle(270f);
        mChartHumidity.setRotation(0f);
        mChartHumidity.animateY(1400, Easing.EaseInOutQuad);
        mChartHumidity.setTouchEnabled(false);
        mChartHumidity.getLegend().setEnabled(false);
        mChartHumidity.getDescription().setEnabled(false);

        return root;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Co2ViewModel.class);
        mViewModel.getLiveMeasurements().observe(getViewLifecycleOwner(), new Observer<LiveMeasurements>() {
            @Override
            public void onChanged(LiveMeasurements liveMeasurements) {

                //initializing co2, temperature, humidity after it has been changed
                int co2 = liveMeasurements.getCarbonDioxide();
                double temperature = liveMeasurements.getTemperature();
                int humidity = liveMeasurements.getHumidityPercentage();

                // putting it in to strings or textView
                String co2Reading = co2 + " ppm";
                String temperatureReading = temperature + " °C";
                String humidityReading = humidity + " %";

                //setting graphs
                setDataCo2(2, MAX_CO2, co2);
                setDataTemperature(2, MAX_TEMPERATURE, temperature);
                setDataHumidity(2, MAX_HUMIDITY, humidity);

                //setting textViews
                mCo2Reading.setText(co2Reading);
                mHumidityReading.setText(humidityReading);
                mTemperatureReading.setText(temperatureReading);
            }
        });
    }

    public void setDataCo2(int count, float range, int value) {
        ArrayList<PieEntry> values = new ArrayList<>();

        if (range < value) {
            float temp = range;
            value =(int) temp;
        }
        //Adding data to data set
        values.add(new PieEntry((float) value));
        values.add(new PieEntry((float) range - value));

        PieDataSet dataSet = new PieDataSet(values, "Co2 live reading");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        if (value<range){
            dataSet.setColors(new int[]{getContext().getColor(R.color.purple)
                    , getContext().getColor(R.color.white)});
        } else {
            dataSet.setColor(getContext().getColor(R.color.red_warning_900), 200);
        }

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.WHITE);
        mChartCo2.setData(data);

        mChartCo2.invalidate();
    }

    public void setDataTemperature(int count, float range, double value) {
        ArrayList<PieEntry> values = new ArrayList<>();

        if (range < value) {
            float temp = range;
            value =(double) temp;
        }

        values.add(new PieEntry ((float) value));
        values.add(new PieEntry ((float) range - ((float) value)));

        PieDataSet dataSet = new PieDataSet(values, "Temperature live reading");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        if (value<range){
            dataSet.setColors(new int[]{getContext().getColor(R.color.purple)
                                      , getContext().getColor(R.color.white)});
        } else {
            dataSet.setColor(getContext().getColor(R.color.red_warning_900), 200);
        }

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.WHITE);
        mChartTemperature.setData(data);

        mChartTemperature.invalidate();
    }

    public void setDataHumidity(int count, float range, int value) {
        ArrayList<PieEntry> values = new ArrayList<>();

        if (range < value) {
            float temp = range;
            value =(int) temp;
        }

        values.add(new PieEntry((float) value));
        values.add(new PieEntry((float) range - value));

        PieDataSet dataSet = new PieDataSet(values, "Humidity live reading");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        if (value<range){
            dataSet.setColors(new int[]{getContext().getColor(R.color.purple)
                                      , getContext().getColor(R.color.white)});
        } else {
            dataSet.setColor(getContext().getColor(R.color.red_warning_900), 200);
        }

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.WHITE);
        mChartHumidity.setData(data);

        mChartHumidity.invalidate();
    }

}