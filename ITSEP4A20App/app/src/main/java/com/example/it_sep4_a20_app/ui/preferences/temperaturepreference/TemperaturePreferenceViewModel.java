package com.example.it_sep4_a20_app.ui.preferences.temperaturepreference;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

public class TemperaturePreferenceViewModel extends AndroidViewModel
{
    private SettingsRepository mRepo;
    private final float TEMPERATURESETPOINT = 20.0f;

    public TemperaturePreferenceViewModel(Application application)
    {
        super(application);
        this.mRepo = SettingsRepository.getInstance(application);
    }

    public LiveData<Settings> getSettings()
    {
        return mRepo.getSettings();
    }

    public void setTemperatureSetPoint(double maxTemp)
    {
        Settings temp = mRepo.getSettings().getValue();
        temp.setTemperatureSetPoint(maxTemp);
        mRepo.setSettings(temp);
    }

    public void resetMaxTemp()
    {
        setTemperatureSetPoint(TEMPERATURESETPOINT);
    }

    public void storeTemperatureSetPoint(float max)
    {
        mRepo.storeTemperatureSetPoint(max);
    }

    public float getStoredTemperatureSetPoint()
    {
        return mRepo.getTemperatureSetPoint();
    }
}
