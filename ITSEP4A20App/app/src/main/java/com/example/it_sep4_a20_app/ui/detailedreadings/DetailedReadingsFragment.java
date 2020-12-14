package com.example.it_sep4_a20_app.ui.detailedreadings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.it_sep4_a20_app.R;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedCo2;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedHumidity;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedMeasurements;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedTemperature;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class DetailedReadingsFragment extends Fragment
{
    private DetailedReadingsViewModel mViewModel;
    //LineCharts
    private LineChart mCo2LineChart;
    private LineChart mTemperatureLineChart;
    private LineChart mHumidityLineChart;
    //TextViews
    private TextView mCo2Min, mCo2Max, mCo2Avg;
    private TextView mTempMin, mTempMax, mTempAvg;
    private TextView mHumiMin, mHumiMax, mHumiAvg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_detailed_readings,container,false);
        initialiseLineCharts(root);
        findTextViews(root);
        mViewModel = new ViewModelProvider(this).get(DetailedReadingsViewModel.class);
        observeChartData();
        return root;
    }

    private void findTextViews(View root)
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
    }

    private void initialiseLineCharts(View root)
    {
        //Co2
        mCo2LineChart = root.findViewById(R.id.line_chart_co2);
        mCo2LineChart.getDescription().setEnabled(true);
        mCo2LineChart.setDrawGridBackground(false);

        //Temperature
        mTemperatureLineChart = root.findViewById(R.id.line_chart_temperature);
        mTemperatureLineChart.getDescription().setEnabled(true);
        mTemperatureLineChart.setDrawGridBackground(false);

        //Humidity
        mHumidityLineChart = root.findViewById(R.id.line_chart_humidity);
        mHumidityLineChart.getDescription().setEnabled(true);
        mHumidityLineChart.setDrawGridBackground(false);
    }

    private void observeChartData()
    {
        mViewModel.getDetailedMeasurements().observe(getViewLifecycleOwner(), detailedMeasurements ->
        {
            updateCo2ChartData(detailedMeasurements);
            updateTemperatureChartData(detailedMeasurements);
            updateHumidityChartData(detailedMeasurements);
        });
    }

    private void updateHumidityChartData(DetailedMeasurements detailedMeasurements)
    {
        List<DetailedHumidity> detailedHumidityList = detailedMeasurements.getDetailedHumidityList();
        if(detailedHumidityList.size() == 0)
            return;

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> timestamps = new ArrayList<>();

        for(int i = 0; i < detailedHumidityList.size(); i++)
        {
            entries.add(new Entry(i, (float)detailedHumidityList.get(i).getValue()));
            timestamps.add(detailedHumidityList.get(i).getTimestamp());
        }

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return timestamps.get((int) value);
            }
        };

        XAxis xAxis = mHumidityLineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        LineDataSet dataSet = new LineDataSet(entries,"%RH");
        LineData linedata = new LineData(dataSet);

        //TextViews
        mHumiMin.setText(getString(R.string.text_humidity_min, linedata.getYMin()));
        mHumiMax.setText(getString(R.string.text_humidity_max, linedata.getYMax()));

        float avg = 0;
        for(int i = 0; i < detailedHumidityList.size(); i++)
        {
            avg += detailedHumidityList.get(i).getValue();
        }
        avg /= detailedHumidityList.size();

        mHumiAvg.setText(getString(R.string.text_humidity_avg, avg));

        //Add lines showing preferences
        List<Entry> minEntries = new ArrayList<>();
        int minPreference = mViewModel.getMinHumidityPreference();
        minEntries.add(new Entry(0, minPreference));
        minEntries.add(new Entry(linedata.getXMax(), minPreference));
        LineDataSet minDataSet = new LineDataSet(minEntries,"Minimum preferred");
        minDataSet.setColor(getContext().getColor(R.color.blue_warning_900));
        minDataSet.setCircleColor(getContext().getColor(R.color.blue_warning_900));

        List<Entry> maxEntries = new ArrayList<>();
        int maxPreference = mViewModel.getMaxHumidityPreference();
        maxEntries.add(new Entry(0, maxPreference));
        maxEntries.add(new Entry(linedata.getXMax(), maxPreference));
        LineDataSet maxDataSet = new LineDataSet(maxEntries,"Maximum preferred");
        maxDataSet.setColor(getContext().getColor(R.color.red_warning_900));
        maxDataSet.setCircleColor(getContext().getColor(R.color.red_warning_900));

        linedata.addDataSet(minDataSet);
        linedata.addDataSet(maxDataSet);

        //Finally...
        mHumidityLineChart.setData(linedata);
        mHumidityLineChart.notifyDataSetChanged();
    }

    private void updateTemperatureChartData(DetailedMeasurements detailedMeasurements)
    {
        List<DetailedTemperature> detailedTemperatures = detailedMeasurements.getDetailedTemperatureList();
        if(detailedTemperatures.size() == 0)
            return;

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> timestamps = new ArrayList<>();

        for(int i = 0; i < detailedTemperatures.size(); i++)
        {
            entries.add(new Entry(i, (float)detailedTemperatures.get(i).getValue()));
            timestamps.add(detailedTemperatures.get(i).getTimestamp());
        }

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return timestamps.get((int) value);
            }
        };

        XAxis xAxis = mTemperatureLineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        LineDataSet dataSet = new LineDataSet(entries,"Celsius");
        LineData linedata = new LineData(dataSet);


        //TextViews
        mTempMin.setText(getString(R.string.text_temperature_min, linedata.getYMin()));
        mTempMax.setText(getString(R.string.text_temperature_max, linedata.getYMax()));

        float avg = 0;
        for(int i = 0; i < detailedTemperatures.size(); i++)
        {
            avg += detailedTemperatures.get(i).getValue();
        }
        avg /= detailedTemperatures.size();

        mTempAvg.setText(getString(R.string.text_temperature_avg, avg));

        //Add lines showing preferences
        List<Entry> setPointEntries = new ArrayList<>();
        float setPointSetting = mViewModel.getTemperatureSetPoint();
        setPointEntries.add(new Entry(0, setPointSetting));
        setPointEntries.add(new Entry(linedata.getXMax(), setPointSetting));
        LineDataSet setPointDataSet = new LineDataSet(setPointEntries,"Temperature Set Point");
        setPointDataSet.setColor(getContext().getColor(R.color.red_warning_900));
        setPointDataSet.setCircleColor(getContext().getColor(R.color.red_warning_900));
        linedata.addDataSet(setPointDataSet);

        //Finally...
        mTemperatureLineChart.setData(linedata);
        mTemperatureLineChart.notifyDataSetChanged();
    }

    private void updateCo2ChartData(DetailedMeasurements detailedMeasurements)
    {
        List<DetailedCo2> detailedCo2s = detailedMeasurements.getDetailedCo2List();
        if(detailedCo2s.size() == 0)
            return;

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> timestamps = new ArrayList<>();


        for(int i = 0; i < detailedCo2s.size(); i++)
        {
            entries.add(new Entry(i, detailedCo2s.get(i).getValue()));
            timestamps.add(detailedCo2s.get(i).getTimestamp());
        }

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return timestamps.get((int) value);
            }
        };

        XAxis xAxis = mCo2LineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        LineDataSet dataSet = new LineDataSet(entries,"CO2");
        LineData linedata = new LineData(dataSet);

        //TextViews
        mCo2Min.setText(getString(R.string.text_co2_min, linedata.getYMin()));
        mCo2Max.setText(getString(R.string.text_co2_max, linedata.getYMax()));

        float avg = 0;
        for(int i = 0; i < detailedCo2s.size(); i++)
        {
            avg += detailedCo2s.get(i).getValue();
        }
        avg /= detailedCo2s.size();

        mCo2Avg.setText(getString(R.string.text_co2_avg, avg));

        //Add lines showing preferences
        List<Entry> minEntries = new ArrayList<>();
        int minPreference = mViewModel.getMinCo2Preference();
        minEntries.add(new Entry(0, minPreference));
        minEntries.add(new Entry(linedata.getXMax(), minPreference));
        LineDataSet minDataSet = new LineDataSet(minEntries,"Minimum preferred");
        minDataSet.setColor(getContext().getColor(R.color.blue_warning_900));
        minDataSet.setCircleColor(getContext().getColor(R.color.blue_warning_900));

        List<Entry> maxEntries = new ArrayList<>();
        int maxPreference = mViewModel.getMaxCo2Preference();
        maxEntries.add(new Entry(0, maxPreference));
        maxEntries.add(new Entry(linedata.getXMax(), maxPreference));
        LineDataSet maxDataSet = new LineDataSet(maxEntries,"Maximum preferred");
        maxDataSet.setColor(getContext().getColor(R.color.red_warning_900));
        maxDataSet.setCircleColor(getContext().getColor(R.color.red_warning_900));

        linedata.addDataSet(minDataSet);
        linedata.addDataSet(maxDataSet);

        //Finally..
        mCo2LineChart.setData(linedata);
        mCo2LineChart.notifyDataSetChanged();
    }
}
