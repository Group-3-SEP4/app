package com.example.it_sep4_a20_app.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.networking.ISettingsAPIClient;
import com.example.it_sep4_a20_app.networking.SettingsAPIClient;
import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.networking.dummy.APIDummy;

public class SettingsRepository {
    private ISettingsAPIClient mApiClient;
//    private APIDummy mApiClient; for testing

    private static final String TAG = "SettingsRepository";

    public SettingsRepository(){
        mApiClient = SettingsAPIClient.getInstance();
//        mApiClient = new APIDummy();
    }

    public LiveData<Settings> getSettings() {
        Log.i(TAG, "Calling request settings...");
        mApiClient.requestSettings();
        return mApiClient.getSettings();
    }

    public void setSettings(Settings settings) {
        Log.i(TAG, "Calling post settings...");
        mApiClient.postSettings(settings);
    }
}
