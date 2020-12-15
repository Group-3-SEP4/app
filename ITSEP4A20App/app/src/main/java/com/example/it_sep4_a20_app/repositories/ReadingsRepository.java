package com.example.it_sep4_a20_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedMeasurements;
import com.example.it_sep4_a20_app.networking.IReadingsAPIClient;
import com.example.it_sep4_a20_app.networking.ReadingsAPIClient;
import com.example.it_sep4_a20_app.networking.dummy.APIDummy;

/**
 * @author Tobias Sønderbo, David Nguyen
 */
public class ReadingsRepository
{
    private IReadingsAPIClient mApiClient;
    private static ReadingsRepository instance;
    private static final String TAG = "ReadingsRepository";
    private String deviceId;

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
        mApiClient = ReadingsAPIClient.getInstance();
    }

    public LiveData<LiveMeasurements> getLiveMeasurements() {
        Log.i(TAG, "Calling request for measurements...");
        mApiClient.requestMeasurements();
        return mApiClient.getLiveMeasurements();
    }

    public LiveData<NightOverview> getNightOverview()
    {
        return mApiClient.getNightOverview();
    }

    public LiveData<DetailedMeasurements> getDetailedMeasurements()
    {
        return mApiClient.getDetailedMeasurements();
    }

    public void setDeviceId(String device_id) {
        this.deviceId = device_id;
        //also do whatever else needed in a real case
    }
}
