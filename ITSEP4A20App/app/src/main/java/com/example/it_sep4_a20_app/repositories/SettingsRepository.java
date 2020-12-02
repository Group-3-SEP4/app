package com.example.it_sep4_a20_app.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.networking.ISettingsAPIClient;
import com.example.it_sep4_a20_app.networking.SettingsAPIClient;
import com.example.it_sep4_a20_app.util.objects.Settings;

public class SettingsRepository {
    private ISettingsAPIClient mApiClient;

    private static final String TAG = "ReadingsAPIClient";

    public SettingsRepository(){
        mApiClient = SettingsAPIClient.getInstance();
    }

    public MutableLiveData<Settings> getSettings() {
        Log.i(TAG, "Calling request settings...");
        mApiClient.requestSettings();
        return mApiClient.getSettings();
    }

    public void setSettings(Settings settings) {
        Log.i(TAG, "Calling post settings...");
        mApiClient.postSettings(settings);
    }
}
