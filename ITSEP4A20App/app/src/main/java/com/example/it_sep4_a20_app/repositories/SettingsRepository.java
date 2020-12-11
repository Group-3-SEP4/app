package com.example.it_sep4_a20_app.repositories;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AsyncPlayer;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.networking.ISettingsAPIClient;
import com.example.it_sep4_a20_app.networking.SettingsAPIClient;
import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.networking.dummy.APIDummy;

public class SettingsRepository
{
    private static SettingsRepository instance;

    private ISettingsAPIClient mApiClient;
    private SharedPreferences mPreferences;

    private static final String TAG = "SettingsRepository";

    private SettingsRepository(Application application)
    {
        mApiClient = new APIDummy();
        mPreferences = application.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    public static SettingsRepository getInstance(Application application)
    {
        if (instance == null)
        {
            instance = new SettingsRepository(application);
        }
        return instance;
    }

    public LiveData<Settings> getSettings()
    {
        Log.i(TAG, "Calling request settings...");
        mApiClient.requestSettings();
        return mApiClient.getSettings();
    }

    public void setSettings(Settings settings)
    {
        Log.i(TAG, "Calling post settings...");
        mApiClient.postSettings(settings);
    }

    public void storeMinTemperatureSetting(float min)
    {
        mPreferences.edit().putFloat("min_temperature",min).apply();
    }

    public void storeMaxTemperatureSetting(float max)
    {
        mPreferences.edit().putFloat("max_temperature",max).apply();
    }

    public void storeMinCo2Setting(int min)
    {
        mPreferences.edit().putInt("min_co2",min).apply();
    }

    public void storeMaxCo2Setting(int max)
    {
        mPreferences.edit().putInt("max_co2",max).apply();
    }

    public void storeMinHumiditySetting(int min)
    {
        mPreferences.edit().putInt("min_humidity",min).apply();
    }

    public void storeMaxHumiditySetting(int max)
    {
        mPreferences.edit().putInt("max_humidity",max).apply();
    }

    public float getMinTemperatureSetting()
    {
        return mPreferences.getFloat("min_temperature",0);
    }

    public float getMaxTemperatureSetting()
    {
        return mPreferences.getFloat("max_temperature",0);
    }

    public int getMaxCo2Setting()
    {
        return mPreferences.getInt("max_co2",0);
    }

    public int getMinCo2Setting()
    {
        return mPreferences.getInt("min_co2",0);
    }

    public int getMaxHumiditySetting()
    {
        return mPreferences.getInt("max_humidity",0);
    }

    public int getMinHumiditySetting()
    {
        return mPreferences.getInt("min_humidity",0);
    }
}
