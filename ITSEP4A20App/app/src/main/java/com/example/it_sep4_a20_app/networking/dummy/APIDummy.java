package com.example.it_sep4_a20_app.networking.dummy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

<<<<<<< HEAD
import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
=======
import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.networking.IReadingsAPICLient;
import com.example.it_sep4_a20_app.networking.ISettingsAPIClient;
>>>>>>> develop

public class APIDummy implements ISettingsAPIClient
{

<<<<<<< HEAD
=======
    private MutableLiveData<Settings> settingsMutableLiveData;
    public APIDummy()
    {
        settingsMutableLiveData = new MutableLiveData<>();
        Settings settings = new Settings();
        settings.setTemperatureSetPoint(32.0);
        settingsMutableLiveData.setValue(settings);
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
>>>>>>> develop
}
