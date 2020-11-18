package com.example.it_sep4_a20_app.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.util.Settings;

public interface IAPIClient
{
    MutableLiveData<Double> requestCO2();
    Settings requestSettings();
}
