package com.example.it_sep4_a20_app.ui.preferences.humiditypreference;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.it_sep4_a20_app.repositories.SettingsRepository;
import com.example.it_sep4_a20_app.util.Constants;

public class HumidityPreferenceViewModel extends AndroidViewModel
{
    private SettingsRepository mRepo;


    public HumidityPreferenceViewModel(@NonNull Application application)
    {
        super(application);
        mRepo = SettingsRepository.getInstance(application);
    }

    public void storeMinHumiditySetting(int min)
    {
        mRepo.storeMinHumiditySetting(min);
    }

    public void storeMaxHumiditySetting(int max)
    {
        mRepo.storeMaxHumiditySetting(max);
    }

    public void resetHumiditySettings()
    {
        mRepo.storeMinHumiditySetting(Constants.MINHUMIDITY);
        mRepo.storeMaxHumiditySetting(Constants.MAXHUMIDITY);
    }

    public int getStoredMaxHumiditySetting()
    {
        return mRepo.getMaxHumiditySetting();
    }

    public int getStoredMinHumiditySetting()
    {
        return mRepo.getMinHumiditySetting();
    }
}
