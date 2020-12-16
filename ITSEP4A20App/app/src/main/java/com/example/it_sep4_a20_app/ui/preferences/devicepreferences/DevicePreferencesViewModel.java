package com.example.it_sep4_a20_app.ui.preferences.devicepreferences;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.it_sep4_a20_app.data.models.Device;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Claire Zubiaurre
 */
public class DevicePreferencesViewModel extends AndroidViewModel {
    private SettingsRepository mRepo;

    public DevicePreferencesViewModel(Application application) {
        super(application);
        this.mRepo = SettingsRepository.getInstance(application);
    }

    public void storeDevice(Device device) {
        ArrayList<Device> devices = mRepo.getDevicesSetting();
        devices.add(device);
        mRepo.storeDevicesSetting(devices);
    }

    public List<Device> getDevices() {
        return mRepo.getDevicesSetting();
    }

    public void removeDevice(String device_id) {
        ArrayList<Device> devices = mRepo.getDevicesSetting();
        for (int i = 0; i< devices.size(); i++) {
            if (devices.get(i).getRoomId().equals(device_id)) {
                devices.remove(i);
                break;
            }
        }
        mRepo.storeDevicesSetting(devices);
    }

    public void storeSelectedDevice(String device_id) { mRepo.storeSelectedDeviceId(device_id); }
    public String getSelectedDevice() { return mRepo.getSelectedDeviceId(); }
}
