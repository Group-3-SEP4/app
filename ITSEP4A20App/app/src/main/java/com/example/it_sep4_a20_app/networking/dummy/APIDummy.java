package com.example.it_sep4_a20_app.networking.dummy;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.networking.IReadingsAPIClient;
import com.example.it_sep4_a20_app.networking.ISettingsAPIClient;

public class APIDummy implements ISettingsAPIClient, IReadingsAPICLient
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
    public LiveData<Double> getCo2()
    {
        return null;
    }

    @Override
    public void requestCO2()
    {

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
}
