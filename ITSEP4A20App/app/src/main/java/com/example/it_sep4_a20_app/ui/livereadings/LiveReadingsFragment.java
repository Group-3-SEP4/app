
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
import com.example.it_sep4_a20_app.util.Constants;
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
    private TextView mServoReading;
    private TextView mCo2Max;
    private TextView mCo2Min;
    private TextView mTemperatureSetPoint;
    private TextView mHumidityMax;
    private TextView mHumidityMin;
    private TextView mWindowState;
    private TextView mServoPosition;

    //Pie charts
    private PieChart mChartCo2;
    private PieChart mChartTemperature;
    private PieChart mChartHumidity;
    private PieChart mChartServo;

    public static LiveReadingsFragment newInstance() {
        return new LiveReadingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_livereadings, container, false);

        mCo2Reading = root.findViewById(R.id.textView_co2_reading);
        mTemperatureReading = root.findViewById(R.id.textView_temperature_reading);
        mHumidityReading = root.findViewById(R.id.textView_humidity_reading);
        mServoReading = root.findViewById(R.id.textView_current_servo_position);
        mCo2Max = root.findViewById(R.id.textView_co2max);
        mCo2Min = root.findViewById(R.id.textView_co2min);
        mTemperatureSetPoint = root.findViewById(R.id.textView_temperature_point);
        mHumidityMax = root.findViewById(R.id.textView_humidity_max);
        mHumidityMin = root.findViewById(R.id.textView_humidity_min);
        mWindowState = root.findViewById(R.id.textView_windowPosition);
        mServoPosition = root.findViewById(R.id.textView_servo_position);

        mChartServo = root.findViewById(R.id.chart_servo);
        initCharts(mChartServo, root);

        mChartCo2 = root.findViewById(R.id.chart_co2);
        initCharts(mChartCo2, root);

        mChartTemperature = root.findViewById(R.id.chart_temperature);
        initCharts(mChartTemperature, root);

        mChartHumidity = root.findViewById(R.id.chart_humidity);
        initCharts(mChartHumidity, root);

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
                int servo = liveMeasurements.getServoPositionPercentage();

                // Putting it in to strings or textView
                String co2Reading = co2 + " ppm";
                String temperatureReading = temperature + " °C";
                String humidityReading = humidity + " %";
                String servoReading = servo + " %";

                //Getting max and min values
                int maxCo2 = mViewModel.getMaxCo2();
                int minCo2 = mViewModel.getMinCo2();
                double temperatureSetPoint = failSafeForTemperature(mViewModel.getTemperatureSetPoint());
                int maxHumidity = mViewModel.getMaxHumidity();
                int minHumidity = mViewModel.getMinHumidity();
                int maxServoPosition = mViewModel.getMaxServoPosition();

                // Setting graphs
                setChartData(2, maxCo2, co2, mChartCo2);
                setChartData(2, (float) temperatureSetPoint, temperature, mChartTemperature);
                setChartData(2, maxHumidity, humidity, mChartHumidity);
                setChartData(2, maxServoPosition, servo, mChartServo);

                // Setting textViews
                mCo2Reading.setText(co2Reading);
                mHumidityReading.setText(humidityReading);
                mTemperatureReading.setText(temperatureReading);
                mServoReading.setText(servoReading);
                mCo2Max.setText(getString(R.string.current_max_co2, maxCo2));
                mCo2Min.setText(getString(R.string.current_min_co2, minCo2));
                mTemperatureSetPoint.setText(getString(R.string.current_temperature_setpoint,(float) temperatureSetPoint));
                mHumidityMax.setText(getString(R.string.current_max_humidity, maxHumidity));
                mHumidityMin.setText(getString(R.string.current_min_humidity, minHumidity));
                if (servo == 0) {
                    mWindowState.setText(getString(R.string.current_window_state_closed));
                } else
                    mWindowState.setText(getString(R.string.current_window_state_open));
                mServoPosition.setText(getString(R.string.current_servo_position, servo));
            }
        });
    }

    private double failSafeForTemperature(double temperatureSetPoint) {
        //To avoid graph setting its range to 0 in case user sets temp set point to 0
        if(temperatureSetPoint==0) {
            temperatureSetPoint = 20;
            return temperatureSetPoint;
        }else
            return temperatureSetPoint;
    }

    private void initCharts(PieChart chart, View root) {
        chart.setMaxAngle(270f); //displays 270° of the chart
        chart.animateY(1400, Easing.EaseInOutQuad);
        chart.setTouchEnabled(false); // Disables graph intractability
        chart.getLegend().setEnabled(false); // Disables legend
        chart.getDescription().setEnabled(false); // Disables description
    }

    private void setChartData(int count, float range, double value, PieChart chart) {
        ArrayList<PieEntry> values = new ArrayList<>();
        // Changes value of measured value to range to not overflow when making a graph
        if (range < value) {
            float temp = range;
            value =(double) temp;
        } else if (range==0){
            double temp = value;
            range = (float) temp;
        }
        // Adding data to data set
        values.add(new PieEntry ((float) value));
        values.add(new PieEntry ((float) range - ((float) value)));

        String temp = "";
        if (chart == mChartCo2) {
            temp = "Co2";
        } else if(chart == mChartTemperature) {
            temp = "Temperature";
        } else if (chart == mChartHumidity) {
            temp = "Humidity";
        }

        PieDataSet dataSet = new PieDataSet(values, temp + " live reading");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // Changes color if the measured value is out of bounds
        if (value<range){
            dataSet.setColors(new int[]{getContext().getColor(R.color.purple)
                    , getContext().getColor(R.color.white)});
        } else {
            dataSet.setColor(getContext().getColor(R.color.red_warning_900),200);
        }

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.WHITE);
        // Sets data in the chart
        chart.setData(data);
        // Informs the chart about changes and displays them
        chart.invalidate();
    }
}