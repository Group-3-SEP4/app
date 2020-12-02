package com.example.it_sep4_a20_app.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.util.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadingsAPIClient implements IReadingsAPICLient
{
    private static ReadingsAPIClient mInstance;
    private SleepTrackerAPI mApi;
    private MutableLiveData<Double> mCo2;

    private static final String TAG = "ReadingsAPIClient";

    private ReadingsAPIClient() {
        this.mApi = ServiceGenerator.getAPI();
        mCo2 = new MutableLiveData<>();
        mCo2.setValue(0.0);
    }

    public static ReadingsAPIClient getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new ReadingsAPIClient();
        }
        return mInstance;
    }

    public LiveData<Double> getCo2()
    {
        return mCo2;
    }

    @Override
    public void requestCO2() {
        Call<Double> call = mApi.getCO2();
        call.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response)
            {
                if(response.code() == 200)
                {
                     mCo2.setValue(response.body());
                     Log.i(TAG, "Got value: " + mCo2);
                }
                Log.d(TAG, response.code() + "");
            }
            @Override
            public void onFailure(Call<Double> call, Throwable t)
            {
                Log.i(TAG, "Something went wrong :<");
                Log.i(TAG, t.getLocalizedMessage());
                Log.i(TAG, t.toString());
            }
        });
    }

}
