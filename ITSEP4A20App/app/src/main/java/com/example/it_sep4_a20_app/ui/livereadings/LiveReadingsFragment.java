package com.example.it_sep4_a20_app.ui.livereadings;

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

import java.util.ArrayList;
/**
 * @author David Nguyen
 */
public class LiveReadingsFragment extends Fragment {

    private LiveReadingsViewModel mViewModel;
    //TextViews
    private TextView mCo2Reading;
    private TextView mTemperatureReading;
    private TextView mHumidityReading;
    //Pie charts
    private PieChart mChartCo2;
    private PieChart mChartTemperature;
    private PieChart mChartHumidity;
    //Max values for suitable living situation
    private final int MAX_CO2 = 1000;
    private final int MAX_TEMPERATURE = 40;
    private final int MAX_HUMIDITY = 100;

    public static LiveReadingsFragment newInstance() {
        return new LiveReadingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_livereadings, container, false);

        mCo2Reading = root.findViewById(R.id.textView_co2Reading);
        mHumidityReading = root.findViewById(R.id.textView_humidityReading);
        mTemperatureReading = root.findViewById(R.id.textView_temperatureReading);

        mChartCo2 = root.findViewById(R.id.chart_co2);
        mChartCo2.setMaxAngle(270f); // Sets max angle of the graph to be 270°
        mChartCo2.animateY(1400, Easing.EaseInOutQuad);
        mChartCo2.setTouchEnabled(false); // Disables graph intractability
        mChartCo2.getLegend().setEnabled(false); // Disables legend
        mChartCo2.getDescription().setEnabled(false); // Disables description

        mChartTemperature = root.findViewById(R.id.chart_temperature);
        mChartTemperature.setMaxAngle(270f);
        mChartTemperature.animateY(1400, Easing.EaseInOutQuad);
        mChartTemperature.setTouchEnabled(false);
        mChartTemperature.getLegend().setEnabled(false);
        mChartTemperature.getDescription().setEnabled(false);

        mChartHumidity = root.findViewById(R.id.chart_humidity);
        mChartHumidity.setMaxAngle(270f);
        mChartHumidity.animateY(1400, Easing.EaseInOutQuad);
        mChartHumidity.setTouchEnabled(false);
        mChartHumidity.getLegend().setEnabled(false);
        mChartHumidity.getDescription().setEnabled(false);

        return root;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LiveReadingsViewModel.class);
        mViewModel.getLiveMeasurements().observe(getViewLifecycleOwner(), new Observer<LiveMeasurements>() {
            @Override
            public void onChanged(LiveMeasurements liveMeasurements) {

                // Initializing co2, temperature, humidity after it has been changed
                int co2 = liveMeasurements.getCarbonDioxide();
                double temperature = liveMeasurements.getTemperature();
                int humidity = liveMeasurements.getHumidityPercentage();

                // Putting it in to strings or textView
                String co2Reading = co2 + " ppm";
                String temperatureReading = temperature + " °C";
                String humidityReading = humidity + " %";

                // Setting graphs
                setDataCo2(2, MAX_CO2, co2);
                setDataTemperature(2, MAX_TEMPERATURE, temperature);
                setDataHumidity(2, MAX_HUMIDITY, humidity);

                // Setting textViews
                mCo2Reading.setText(co2Reading);
                mHumidityReading.setText(humidityReading);
                mTemperatureReading.setText(temperatureReading);
            }
        });
    }

    public void setDataCo2(int count, float range, int value) {
        ArrayList<PieEntry> values = new ArrayList<>();
        // Changes value of measured value to range to not overflow when making a graph
        if (range < value) {
            float temp = range;
            value =(int) temp;
        }
        // Adding data to data set
        values.add(new PieEntry((float) value));
        values.add(new PieEntry((float) range - value));

        PieDataSet dataSet = new PieDataSet(values, "Co2 live reading");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        // Changes color if the measured value is out of bounds
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
        // Sets data in the chart
        mChartCo2.setData(data);
        // Informs the chart about changes and displays them
        mChartCo2.invalidate();
    }

    public void setDataTemperature(int count, float range, double value) {
        ArrayList<PieEntry> values = new ArrayList<>();
        // Changes value of measured value to range to not overflow when making a graph
        if (range < value) {
            float temp = range;
            value =(double) temp;
        }
        // Adding data to data set
        values.add(new PieEntry ((float) value));
        values.add(new PieEntry ((float) range - ((float) value)));

        PieDataSet dataSet = new PieDataSet(values, "Temperature live reading");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // Changes color if the measured value is out of bounds
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
        // Sets data in the chart
        mChartTemperature.setData(data);
        // Informs the chart about changes and displays them
        mChartTemperature.invalidate();
    }

    public void setDataHumidity(int count, float range, int value) {
        ArrayList<PieEntry> values = new ArrayList<>();
        // Changes value of measured value to range to not overflow when making a graph
        if (range < value) {
            float temp = range;
            value =(int) temp;
        }
        // Adding data to data set
        values.add(new PieEntry((float) value));
        values.add(new PieEntry((float) range - value));

        PieDataSet dataSet = new PieDataSet(values, "Humidity live reading");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // Changes color if the measured value is out of bounds
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
        // Sets data in the chart[
        mChartHumidity.setData(data);
        // Informs the chart about changes and displays them
        mChartHumidity.invalidate();
    }

}