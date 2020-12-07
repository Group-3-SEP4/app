package com.example.it_sep4_a20_app.ui.preferences.temperaturepreference;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

public class TemperaturePreferenceViewModel extends ViewModel
{
    private SettingsRepository mRepo;
    private final int MAXTEMP = 40;

    public TemperaturePreferenceViewModel() {
        this.mRepo = new SettingsRepository();
    }

    public LiveData<Settings> getSettings() {
        return mRepo.getSettings();
    }

    public void setMaxTemp(double maxTemp) {
        Settings temp = mRepo.getSettings().getValue();
        temp.setTemperatureSetPoint(maxTemp);
        mRepo.setSettings(temp);
    }

    public void resetMaxTemp()
    {
        setMaxTemp(MAXTEMP);
    }
}
