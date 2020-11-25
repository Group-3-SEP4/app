package com.example.it_sep4_a20_app.networking;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.it_sep4_a20_app.util.Settings;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIClient implements IAPIClient {
    private SleepTrackerAPI api;
    private MutableLiveData<Double> co2;
    private Settings  settings;
    private Settings  settingsResponse;

    public APIClient(SleepTrackerAPI api) {
        this.api = api;
        co2 = new MutableLiveData<>();
        settings = new Settings();
        settingsResponse = new Settings();
    }

    @Override
    public MutableLiveData<Double> requestCO2() {
        Call<Double> call = api.getCO2();
        call.enqueue(new Callback<Double>() {
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

    @Override
    public Settings requestSettings() {
        Call<Settings> call = api.getSettings();
        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.code() == 200){
                    settings = response.body();
                    Log.i("Retrofit", "Got settings");

                }
                Log.i("Retrofit", response.code() + "");
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
    public Settings  postSettings(Settings settings) {
        Call<Settings> call = api.setSettings(settings);
        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.code() == 200){
                    settingsResponse = response.body();
                    Log.i("Retrofit", "Settings have been posted");
                }
                Log.i("Retrofit", response.code() + "");
            }
            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :<");
                Log.i("Retrofit", t.getLocalizedMessage());
                Log.i("Retrofit", t.toString());
           }
        });
        return settingsResponse;
    }
}
