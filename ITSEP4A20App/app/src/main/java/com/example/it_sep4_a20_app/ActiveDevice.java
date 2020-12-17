package com.example.it_sep4_a20_app;

import com.example.it_sep4_a20_app.data.models.Device;

import java.util.ArrayList;

/**
 * @author Claire Zubiaurre
 */
public class ActiveDevice {

    private static ActiveDevice instance;
    private ArrayList<OnDeviceChangeListener> observers = new ArrayList<>();
    private Device device;

    public static ActiveDevice getInstance()
    {
        if (instance == null)
        {
            instance = new ActiveDevice();
        }
        return instance;
    }

    private void alertOnDeviceChangeObservers() {
        for (int i=0; i<observers.size(); i++) {
            observers.get(i).OnDeviceChange(device);
        }
    }

    public void registerOnDeviceChangeListener(OnDeviceChangeListener observer) {
        observers.add(observer);
    }

    public void setDevice(Device device) {
        this.device = device;
        alertOnDeviceChangeObservers();
    }

    public Device getDevice() {
        return device;
    }
}
