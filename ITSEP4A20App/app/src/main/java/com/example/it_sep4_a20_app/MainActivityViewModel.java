package com.example.it_sep4_a20_app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.it_sep4_a20_app.data.models.Device;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;
import com.example.it_sep4_a20_app.util.ActiveDevice;

import java.util.List;

/**
 * @author Claire Zubiauure
 * */
public class MainActivityViewModel extends AndroidViewModel {
    private SettingsRepository mRepo;

    public MainActivityViewModel(Application application) {
        super(application);
        this.mRepo = SettingsRepository.getInstance(application);
    }

    public List<Device> getDevices() {
        return mRepo.getDevicesSetting();
    }

    public Device getDevice(String device_id) {
        List<Device> devices = getDevices();
        for (int i = 0; i< devices.size(); i++) {
            if (devices.get(i).getRoomId().equals(device_id)) {
                return devices.get(i);
            }
        }
        return null;
    }

    public void storeSelectedDeviceId(String device_id) {
        mRepo.storeSelectedDeviceId(device_id);
        Device device = getDevice(device_id);
        ActiveDevice.getInstance().setDevice(device);
    }


}
