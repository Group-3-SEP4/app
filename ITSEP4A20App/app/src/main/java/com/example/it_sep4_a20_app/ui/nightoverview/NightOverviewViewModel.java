package com.example.it_sep4_a20_app.ui.nightoverview;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.repositories.ReadingsRepository;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

public class NightOverviewViewModel extends AndroidViewModel
{
    private ReadingsRepository mReadingsRepo;
    private SettingsRepository mSettingsRepo;

    public NightOverviewViewModel(Application application)
    {
        super(application);
        mReadingsRepo = ReadingsRepository.getInstance();
        mSettingsRepo = SettingsRepository.getInstance(application);
    }

    public LiveData<NightOverview> getNightOverview()
    {
        return mReadingsRepo.getNightOverview();
    }

    public boolean isPreferredCo2(double co2)
    {
        int min = mSettingsRepo.getMinCo2Setting();
        int max = mSettingsRepo.getMaxCo2Setting();
        return min <= co2 && max >= co2;
    }

    public boolean isPreferredTemperature(double temperature)
    {
        float setPoint = mSettingsRepo.getTemperatureSetPoint();
        return setPoint >= temperature && !(setPoint - 4 < temperature);
    }

    public boolean isPreferredHumidity(double humidity)
    {
        int min = mSettingsRepo.getMinHumiditySetting();
        int max = mSettingsRepo.getMaxHumiditySetting();
        return min <= humidity && max >= humidity;
    }
}
