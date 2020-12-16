package com.example.it_sep4_a20_app.networking;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedMeasurements;
import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.util.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Tobias Sønderbo, David Nguyen
 */
public class ReadingsAPIClient implements IReadingsAPIClient
{
    private static ReadingsAPIClient mInstance;
    private SleepTrackerAPI mApi;
    private MutableLiveData<NightOverview> mNightOverview;
    private MutableLiveData<LiveMeasurements> mLiveMeasurement;
    private static final String TAG = "ReadingsAPIClient";
    //Temporary deviceEui
    //Another one "0004A30B00219CB5"
    //"0004A30B00219CAC"
    private final String mTempDeviceEui = "0004A30B00219CB5";

    private ReadingsAPIClient() {
        this.mApi = ServiceGenerator.getAPI();
        mLiveMeasurement = new MutableLiveData<>();
        mNightOverview = new MutableLiveData<>();
    }

    public static ReadingsAPIClient getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new ReadingsAPIClient();
        }
        return mInstance;
    }

    @Override
    public LiveData<LiveMeasurements> getLiveMeasurements() {
        return mLiveMeasurement;
    }

    @Override
    public void requestMeasurements() {
        Call<LiveMeasurements> call = mApi.getLiveMeasurement(mTempDeviceEui);
        call.enqueue(new Callback<LiveMeasurements>() {
            @Override
            public void onResponse(Call<LiveMeasurements> call, Response<LiveMeasurements> response) {
                if(response.code() == 200)
                {
                    mLiveMeasurement.setValue(response.body());
                    Log.i(TAG, "Got live measurements: ");
                    Log.i(TAG, "Got CO2: " + mLiveMeasurement.getValue().getCarbonDioxide() + " ppm");
                    Log.i(TAG, "Got Temperature: " + mLiveMeasurement.getValue().getTemperature()+ " °C");
                    Log.i(TAG, "Got Humidity: " + mLiveMeasurement.getValue().getHumidityPercentage() +" %");
                }
                Log.d(TAG, response.code() + "");
            }

            @Override
            public void onFailure(Call<LiveMeasurements> call, Throwable t) {
                Log.i(TAG, "Something went wrong :<");
                Log.i(TAG, t.getLocalizedMessage());
                Log.i(TAG, t.toString());
            }
        });
    }

    @Override
    public LiveData<NightOverview> getNightOverview()
    {
        return null;
    }

    @Override
    public LiveData<DetailedMeasurements> getDetailedMeasurements()
    {
        return null;
    }
}
