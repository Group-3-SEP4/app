package com.example.it_sep4_a20_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.networking.IReadingsAPICLient;
import com.example.it_sep4_a20_app.networking.ReadingsAPIClient;


public class ReadingsRepository
{
    private IReadingsAPICLient mApiClient;

    private static final String TAG = "ReadingsRepository";

    public ReadingsRepository()
    {
        mApiClient = ReadingsAPIClient.getInstance();
    }

    public LiveData<Double> getCO2() {
        Log.i(TAG, "Calling request co2...");
        mApiClient.requestCO2();
        return mApiClient.getCo2();
    }
}
