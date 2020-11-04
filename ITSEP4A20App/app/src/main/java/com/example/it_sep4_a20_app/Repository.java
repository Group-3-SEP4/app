package com.example.it_sep4_a20_app;

import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.networking.CO2APIClient;
import com.example.it_sep4_a20_app.networking.ICO2APIClient;
import com.example.it_sep4_a20_app.networking.INetworking;

public class Repository
{
    private ICO2APIClient co2Client;

    public Repository()
    {
        co2Client = new CO2APIClient();
    }

    public MutableLiveData<Double> getCO2()
    {
        return co2Client.requestCO2();
    }
}
