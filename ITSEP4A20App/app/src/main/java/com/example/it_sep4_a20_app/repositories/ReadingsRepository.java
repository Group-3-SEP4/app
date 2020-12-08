package com.example.it_sep4_a20_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.networking.IReadingsAPICLient;
import com.example.it_sep4_a20_app.networking.ReadingsAPIClient;
import com.example.it_sep4_a20_app.networking.dummy.APIDummy;


public class ReadingsRepository
{
    private IReadingsAPICLient mApiClient;
    private static ReadingsRepository instance;
    private static final String TAG = "ReadingsRepository";

    public static ReadingsRepository getInstance()
    {
        if(instance == null)
        {
            instance = new ReadingsRepository();
        }
        return instance;
    }

    private ReadingsRepository()
    {
        mApiClient = new APIDummy();
    }

    public LiveData<Double> getCO2() {
        Log.i(TAG, "Calling request co2...");
        mApiClient.requestCO2();
        return mApiClient.getCo2();
    }

    public LiveData<NightOverview> getNightOverview()
    {
        return mApiClient.NightOverview();
    }
}
