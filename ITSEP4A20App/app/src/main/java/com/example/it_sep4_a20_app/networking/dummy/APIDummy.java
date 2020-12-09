package com.example.it_sep4_a20_app.networking.dummy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.networking.IReadingsAPIClient;
import com.example.it_sep4_a20_app.networking.ISettingsAPIClient;


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
}
