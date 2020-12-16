package com.example.it_sep4_a20_app.ui.detailedreadings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.it_sep4_a20_app.ActiveDevice;
import com.example.it_sep4_a20_app.OnDeviceChangeListener;
import com.example.it_sep4_a20_app.data.models.Device;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedMeasurements;
import com.example.it_sep4_a20_app.repositories.ReadingsRepository;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

/**
 * @author Tobias Sønderbo, David Nguyen, Claire Zubiaure
 */
public class DetailedReadingsViewModel extends AndroidViewModel
{
    private ReadingsRepository mReadingsRepository;
    private SettingsRepository mSettingsRepository;

    public DetailedReadingsViewModel(@NonNull Application application)
    {
        super(application);
        mReadingsRepository = ReadingsRepository.getInstance();
        mSettingsRepository = SettingsRepository.getInstance(application);

        ActiveDevice device = ActiveDevice.getInstance();
        device.registerOnDeviceChangeListener(new OnDeviceChangeListener() {
            @Override
            public void OnDeviceChange(Device device) {
                mReadingsRepository.setDeviceId(device.getRoomId());
            }
        });
    }

    public LiveData<DetailedMeasurements> getDetailedMeasurements()
    {
        return mReadingsRepository.getDetailedMeasurements();
    }

    public int getMaxHumidityPreference()
    {
        return mSettingsRepository.getMaxHumiditySetting();
    }

    public int getMinHumidityPreference()
    {
        return mSettingsRepository.getMinHumiditySetting();
    }

    public float getTemperatureSetPoint()
    {
        return mSettingsRepository.getTemperatureSetPoint();
    }

    public int getMinCo2Preference()
    {
        return mSettingsRepository.getMinCo2Setting();
    }

    public int getMaxCo2Preference()
    {
        return mSettingsRepository.getMaxCo2Setting();
    }
}
