package com.example.it_sep4_a20_app.ui.preferences.devicepreferences;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.it_sep4_a20_app.repositories.SettingsRepository;

public class DevicePreferencesViewModel extends AndroidViewModel {
    private SettingsRepository mRepo;

    public DevicePreferencesViewModel(Application application) {
        super(application);
        this.mRepo = SettingsRepository.getInstance(application);
    }

    public void setDeviceId(String roomId) {
        mRepo.storeDeviceIdSetting(roomId);
    }

    public String getDeviceId() {
        return mRepo.getDeviceIdSetting();
    }

}
