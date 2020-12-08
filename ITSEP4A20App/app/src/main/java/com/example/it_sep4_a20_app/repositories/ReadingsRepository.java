package com.example.it_sep4_a20_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.networking.IReadingsAPIClient;
import com.example.it_sep4_a20_app.networking.ReadingsAPIClient;


public class ReadingsRepository
{
    private IReadingsAPIClient mApiClient;

    private static final String TAG = "ReadingsRepository";

    public ReadingsRepository()
    {
        mApiClient = ReadingsAPIClient.getInstance();
    }

    public LiveData<LiveMeasurements> getLiveMeasurements() {
        Log.i(TAG, "Calling request for measurements...");
        mApiClient.requestMeasurements();
        return mApiClient.getLiveMeasurements();
    }
}
