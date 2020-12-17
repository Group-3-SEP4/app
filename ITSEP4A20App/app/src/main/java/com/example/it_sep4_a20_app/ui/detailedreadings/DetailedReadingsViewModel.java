package com.example.it_sep4_a20_app.ui.detailedreadings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.it_sep4_a20_app.util.ActiveDevice;
import com.example.it_sep4_a20_app.util.OnDeviceChangeListener;
import com.example.it_sep4_a20_app.data.models.Device;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedMeasurements;
import com.example.it_sep4_a20_app.repositories.ReadingsRepository;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

import java.time.LocalDate;

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
        LocalDate current = LocalDate.now();
        String to = current.getYear() + "-" + current.getMonthValue() + "-" + current.getDayOfMonth();
        current = current.minusDays(1);
        String from = current.getYear() + "-" + current.getMonthValue() + "-" + current.getDayOfMonth();
        return mReadingsRepository.getDetailedMeasurements(from, to);
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
