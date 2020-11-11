package com.example.it_sep4_a20_app.networking;

import androidx.lifecycle.MutableLiveData;

public interface ICO2APIClient
{
    MutableLiveData<Double> requestCO2();
}
