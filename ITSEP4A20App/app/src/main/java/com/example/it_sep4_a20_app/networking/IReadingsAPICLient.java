package com.example.it_sep4_a20_app.networking;

import androidx.lifecycle.MutableLiveData;

public interface IReadingsAPICLient
{
    MutableLiveData<Double> getCo2();
    void requestCO2();
}
