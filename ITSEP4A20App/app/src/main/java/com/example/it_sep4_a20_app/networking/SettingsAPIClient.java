package com.example.it_sep4_a20_app.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.util.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsAPIClient implements ISettingsAPIClient
{
    private static final String TAG = "SettingsAPIClient";

    private static SettingsAPIClient mInstance;
    //
    private SleepTrackerAPI mApi;
    private MutableLiveData<Settings> mSettings;

    private SettingsAPIClient()
    {
        this.mApi = ServiceGenerator.getAPI();
        mSettings = new MutableLiveData<>();
    }

    public static SettingsAPIClient getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new SettingsAPIClient();
        }
        return mInstance;
    }

    public LiveData<Settings> getSettings()
    {
        return mSettings;
    }

    @Override
    public void requestSettings() {
        Call<Settings> call = mApi.getSettings();
        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.code() == 200){
                    mSettings.setValue(response.body());
                    Log.i(TAG, "Got settings");

                }
                Log.i(TAG, response.code() + "");
            }
            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
                Log.i(TAG, "Something went wrong :<");
                Log.i(TAG, t.getLocalizedMessage());
                Log.i(TAG, t.toString());
            }
        });
    }

    @Override
    public void postSettings(Settings newSettings) {
        Call<Settings> call = mApi.setSettings(newSettings);
        call.enqueue(new Callback<Settings>() {
            @Override
            public void onResponse(Call<Settings> call, Response<Settings> response) {
                if (response.code() == 200){
                    mSettings.setValue(response.body());
                    Log.i(TAG, "Settings have been posted");
                }
                Log.i(TAG, response.code() + "");
            }
            @Override
            public void onFailure(Call<Settings> call, Throwable t) {
                Log.i(TAG, "Something went wrong :<");
                Log.i(TAG, t.getLocalizedMessage());
                Log.i(TAG, t.toString());
            }
        });
    }
}
