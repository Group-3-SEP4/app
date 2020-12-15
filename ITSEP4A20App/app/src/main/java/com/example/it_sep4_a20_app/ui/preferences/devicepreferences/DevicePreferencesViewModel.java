package com.example.it_sep4_a20_app.ui.preferences.devicepreferences;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.it_sep4_a20_app.data.models.Room;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

import java.util.ArrayList;
import java.util.List;

public class DevicePreferencesViewModel extends AndroidViewModel {
    private SettingsRepository mRepo;

    public DevicePreferencesViewModel(Application application) {
        super(application);
        this.mRepo = SettingsRepository.getInstance(application);
    }

    public void storeDevice(Room room) {
        ArrayList<Room> rooms = mRepo.getDevicesSetting();
        rooms.add(room);
        mRepo.storeDevicesSetting(rooms);
    }

    public List<Room> getDevices() {
        return mRepo.getDevicesSetting();
    }

    public void removeDevice(String device_id) {
        ArrayList<Room> rooms = mRepo.getDevicesSetting();
        for (int i=0; i<rooms.size(); i++) {
            if (rooms.get(i).getRoomId().equals(device_id)) {
                rooms.remove(i);
                break;
            }
        }
        mRepo.storeDevicesSetting(rooms);
    }

    public void storeSelectedDevice(String device_id) { mRepo.storeSelectedDeviceId(device_id); }
    public String getSelectedDevice() { return mRepo.getSelectedDeviceId(); }
}
