package com.example.it_sep4_a20_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.networking.IReadingsAPIClient;
import com.example.it_sep4_a20_app.networking.dummy.APIDummy;


public class ReadingsRepository
{
    private IReadingsAPIClient mApiClient;
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
    
    public LiveData<LiveMeasurements> getLiveMeasurements() {
        Log.i(TAG, "Calling request for measurements...");
        mApiClient.requestMeasurements();
        return mApiClient.getLiveMeasurements();
    }

    public LiveData<NightOverview> getNightOverview()
    {
        return mApiClient.NightOverview();
    }
}
