package com.example.it_sep4_a20_app.networking.dummy;

import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedCo2;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedHumidity;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedMeasurements;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedTemperature;
import com.example.it_sep4_a20_app.networking.ISettingsAPIClient;
import com.example.it_sep4_a20_app.networking.IReadingsAPIClient;

import java.util.ArrayList;
import java.util.List;

public class APIDummy implements ISettingsAPIClient, IReadingsAPIClient
{

    private MutableLiveData<LiveMeasurements> liveMeasurementsMutableLiveData;
    private MutableLiveData<Settings> settingsMutableLiveData;
    public APIDummy()
    {
        settingsMutableLiveData = new MutableLiveData<>();
        Settings settings = new Settings();
        settings.setTemperatureSetPoint(32.0);
        settingsMutableLiveData.setValue(settings);

        liveMeasurementsMutableLiveData = new MutableLiveData<>();
        LiveMeasurements measurements = new LiveMeasurements(80, 800, 24);
        liveMeasurementsMutableLiveData.setValue(measurements);
    }


    @Override
    public LiveData<Settings> getSettings()
    {
        return settingsMutableLiveData;
    }

    @Override
    public void requestSettings()
    {
        settingsMutableLiveData.setValue(settingsMutableLiveData.getValue());
    }

    @Override
    public void postSettings(Settings newSettings)
    {
        settingsMutableLiveData.setValue(newSettings);
    }

    @Override
    public LiveData<LiveMeasurements> getLiveMeasurements() {
        return liveMeasurementsMutableLiveData;
    }

    @Override
    public void requestMeasurements() {

    }

    @Override
    public LiveData<NightOverview> NightOverview()
    {
        MutableLiveData<NightOverview> data = new MutableLiveData<>();
        data.setValue(new NightOverview());
        new Thread(() ->
        {
            while (true)
            {
                NightOverview overview = data.getValue();
                overview.setCo2Avg(overview.getCo2Avg() + 2);
                overview.setHumiAvg(overview.getHumiAvg() + 2);
                overview.setTempAvg(overview.getTempAvg() + 2);
                new Handler(Looper.getMainLooper()).post(()->
                {
                    data.setValue(overview);
                });
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

        return data;
    }

    @Override
    public LiveData<DetailedMeasurements> getDetailedMeasurements()
    {
        MutableLiveData<DetailedMeasurements> data = new MutableLiveData<>();
        ArrayList<DetailedCo2> co2s = new ArrayList<>();
        co2s.add(new DetailedCo2("Morning",100));
        co2s.add(new DetailedCo2("Afternoon",120));
        co2s.add(new DetailedCo2("Midday",149));
        co2s.add(new DetailedCo2("Evening",128));
        co2s.add(new DetailedCo2("Night",151));

        ArrayList<DetailedTemperature> temps = new ArrayList<>();
        temps.add(new DetailedTemperature("Morning",22));
        temps.add(new DetailedTemperature("Afternoon",21));
        temps.add(new DetailedTemperature("Midday",20));
        temps.add(new DetailedTemperature("Evening",22));
        temps.add(new DetailedTemperature("Night",19));

        ArrayList<DetailedHumidity> humidity = new ArrayList<>();
        humidity.add(new DetailedHumidity("Morning",44));
        humidity.add(new DetailedHumidity("Afternoon",33));
        humidity.add(new DetailedHumidity("Midday",25));
        humidity.add(new DetailedHumidity("Evening",34));
        humidity.add(new DetailedHumidity("Night",52));

        DetailedMeasurements detailedMeasurements = new DetailedMeasurements();
        detailedMeasurements.setDetailedCo2List(co2s);
        detailedMeasurements.setDetailedTemperatureList(temps);
        detailedMeasurements.setDetailedHumidityList(humidity);
        data.setValue(detailedMeasurements);
        new Thread(() ->
        {
            while (true)
            {
                new Handler(Looper.getMainLooper()).post(()->
                {
                    data.setValue(detailedMeasurements);
                });
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

        return data;
    }
}
