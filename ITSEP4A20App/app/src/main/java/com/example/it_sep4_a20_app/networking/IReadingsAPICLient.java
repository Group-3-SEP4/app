package com.example.it_sep4_a20_app.networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public interface IReadingsAPICLient
{
    LiveData<Double> getCo2();
    void requestCO2();
}
