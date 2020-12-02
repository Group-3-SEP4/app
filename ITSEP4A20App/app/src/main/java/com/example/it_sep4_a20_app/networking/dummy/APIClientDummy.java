package com.example.it_sep4_a20_app.networking.dummy;

import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.networking.IAPIClient;
import com.example.it_sep4_a20_app.util.Room;
import com.example.it_sep4_a20_app.util.Settings;

import java.util.ArrayList;
import java.util.List;

public class APIClientDummy implements IAPIClient
{
    @Override
    public MutableLiveData<Double> requestCO2()
    {
        MutableLiveData<Double> data = new MutableLiveData<>();
        data.setValue(12.0);
        return data;
    }

    @Override
    public Settings requestSettings() {
        List<Room> rooms = new ArrayList<>();
        Settings settings = new Settings (1
                ,"01/01/0000"
                , 22.5
                , 200
                , 2000
                , rooms);
        return null;
    }

    @Override
    public Settings postSettings(Settings settings) {
        return null;
    }
}
