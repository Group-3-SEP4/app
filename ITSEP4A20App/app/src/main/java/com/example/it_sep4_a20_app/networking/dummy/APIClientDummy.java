package com.example.it_sep4_a20_app.networking.dummy;

import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.networking.IAPIClient;

public class APIClientDummy implements IAPIClient
{
    @Override
    public MutableLiveData<Double> requestCO2()
    {
        MutableLiveData<Double> data = new MutableLiveData<>();
        data.setValue(12.0);
        return data;
    }
}
