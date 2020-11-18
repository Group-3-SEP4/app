package com.example.it_sep4_a20_app.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.util.Settings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIClient implements IAPIClient
{
    private SleepTrackerAPI api;
    private MutableLiveData<Double> co2;
    private Settings settings;
    private boolean settingsResponse;

    public APIClient(SleepTrackerAPI api)
    {
        this.api = api;
        co2 = new MutableLiveData<>();
        settings = new Settings();
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

    public Settings requestSettings(){
        Call<Settings> call = api.getSettings();
        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.code() == 200){
                    settings.setSettingsId(response.body().getSettingsId());
                    settings.setLastUpdate(response.body().getLastUpdate());
                    settings.setTemperatureSetPoint(response.body().getTemperatureSetPoint());
                    settings.setCo2Min(response.body().getCo2Min());
                    settings.setCo2Max(response.body().getCo2Max());
                    settings.setRoom(response.body().getRoom());
                    Log.i("Retrofit", "Got settings");
                }
            }

            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :<");
                Log.i("Retrofit", t.getLocalizedMessage());
                Log.i("Retrofit", t.toString());
            }
        });
        return settings;
    }

    @Override
    public boolean postSettings(Settings settings) {
        Call<Settings> call = api.setSettings(settings);
        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.code() == 200){
                    settingsResponse = true;
                }
            }
            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
                settingsResponse = false;
           }
        });
        return false;
    }

}
