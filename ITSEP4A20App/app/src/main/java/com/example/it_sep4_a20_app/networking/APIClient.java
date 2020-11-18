package com.example.it_sep4_a20_app.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.util.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIClient implements IAPIClient
{
    private SleepTrackerAPI api;
    private MutableLiveData<Double> co2;
    private MutableLiveData<Object> settings;

    public APIClient(SleepTrackerAPI api)
    {
        this.api = api;
        co2 = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<Double> requestCO2()
    {
        Call<Double> call = api.getCO2();
        call.enqueue(new Callback<Double>()
        {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response)
            {
                if(response.code() == 200)
                {
                     co2.setValue(response.body());
                     Log.i("Retrofit", "Got value: " + co2);
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t)
            {
                Log.i("Retrofit", "Something went wrong :<");
                Log.i("Retrofit", t.getLocalizedMessage());
                Log.i("Retrofit", t.toString());
            }
        });
        return co2;
    }

    public MutableLiveData<Object> requestSettings(){
        Call<Object> call = api.getSettings();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                settings.setValue(response.body());
                Log.i("Retrofit", "Got settings");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :<");
                Log.i("Retrofit", t.getLocalizedMessage());
                Log.i("Retrofit", t.toString());
            }
        });
        return settings;
    }

}
