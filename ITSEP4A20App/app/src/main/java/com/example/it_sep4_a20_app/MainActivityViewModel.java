package com.example.it_sep4_a20_app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.it_sep4_a20_app.data.models.Room;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

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

    public List<Room> getDevices() {
        return mRepo.getDevicesSetting();
    }

    public Room getDevice(String device_id) {
        List<Room> rooms = getDevices();
        for (int i=0; i<rooms.size(); i++) {
            if (rooms.get(i).getRoomId().equals(device_id)) {
                return rooms.get(i);
            }
        }
        return null;
    }

    public void storeSelectedDeviceId(String device_id) {
        mRepo.storeSelectedDeviceId(device_id);
        Room device = getDevice(device_id);
        ActiveDevice.getInstance().setDevice(device);
    }


}
