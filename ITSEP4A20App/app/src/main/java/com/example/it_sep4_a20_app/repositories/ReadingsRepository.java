package com.example.it_sep4_a20_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedMeasurements;
import com.example.it_sep4_a20_app.networking.IReadingsAPIClient;
import com.example.it_sep4_a20_app.networking.ReadingsAPIClient;

import java.time.LocalDate;

/**
 * @author Tobias Sønderbo, David Nguyen, Claire Zubiaurre
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
        LocalDate current = LocalDate.now();
        String to = current.getYear() + "-" + current.getMonthValue() + "-" + current.getDayOfMonth();
        current = current.minusDays(1);
        String from = current.getYear() + "-" + current.getMonthValue() + "-" + current.getDayOfMonth();
        mApiClient.requestDetailedMeasurements(deviceId, from, to);
        return mApiClient.getDetailedMeasurements();
    }

    public void setDeviceId(String device_id) {
        this.deviceId = device_id;
        //also do whatever else needed in a real case
    }
}
