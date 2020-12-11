package com.example.it_sep4_a20_app.ui.preferences.temperaturepreference;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

public class TemperaturePreferenceViewModel extends AndroidViewModel
{
    private SettingsRepository mRepo;
    private final float MAXTEMP = 40.0f;
    private final float MINTEMP = 15.0f;

    public TemperaturePreferenceViewModel(Application application)
    {
        super(application);
        this.mRepo = SettingsRepository.getInstance(application);
    }

    public LiveData<Settings> getSettings()
    {
        return mRepo.getSettings();
    }

    public void setMaxTemp(double maxTemp)
    {
        Settings temp = mRepo.getSettings().getValue();
        temp.setTemperatureSetPoint(maxTemp);
        mRepo.setSettings(temp);
    }

    public void resetMaxTemp()
    {
        setMaxTemp(MAXTEMP);
        mRepo.storeMinTemperatureSetting(MINTEMP);
    }

    public void storeMaxTemperatureSetting(float max)
    {
        mRepo.storeMaxTemperatureSetting(max);
    }

    public void storeMinTemperatureSetting(float min)
    {
        mRepo.storeMinTemperatureSetting(min);
    }

    public float getStoredMaxTemperatureSetting()
    {
        return mRepo.getMaxTemperatureSetting();
    }

    public float getStoredMinTemperatureSetting()
    {
        return mRepo.getMinTemperatureSetting();
    }
}
