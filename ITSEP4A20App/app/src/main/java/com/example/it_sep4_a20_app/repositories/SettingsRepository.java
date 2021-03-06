package com.example.it_sep4_a20_app.repositories;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.it_sep4_a20_app.data.models.Device;
import com.example.it_sep4_a20_app.networking.ISettingsAPIClient;
import com.example.it_sep4_a20_app.networking.SettingsAPIClient;
import com.example.it_sep4_a20_app.data.models.Settings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.it_sep4_a20_app.util.Constants;
/**
 * @author Tobias Sønderbo, Claire Zubiaurre
 */
public class SettingsRepository
{
    private static SettingsRepository instance;

    private ISettingsAPIClient mApiClient;
    private SharedPreferences mPreferences;

    private static final String TAG = "SettingsRepository";

    private SettingsRepository(Application application)
    {
        mApiClient = SettingsAPIClient.getInstance();
        mPreferences = application.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        mApiClient.getSettings().observeForever(settings ->
        {
            storeMaxCo2Setting(settings.getPpmMax());
            storeMinCo2Setting(settings.getPpmMin());
            storeTemperatureSetPoint((float) settings.getTemperatureSetpoint());
        });
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
        mApiClient.requestSettings(getSelectedDeviceId());
        return mApiClient.getSettings();
    }

    public void setSettings(Settings settings)
    {
        Log.i(TAG, "Calling post settings...");
        mApiClient.postSettings(settings);
    }

    public void storeTemperatureSetPoint(float max)
    {
        mPreferences.edit().putFloat("setpoint_temperature",max).apply();
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

    public void storeDevicesSetting(List<Device> devices)
    {
        Gson gson = new Gson();
        String rooms_json = gson.toJson(devices);
        mPreferences.edit().putString("devices", rooms_json).apply();
    }

    public void storeSelectedDeviceId(String device_id) {
        mPreferences.edit().putString("selected_device_id", device_id).apply();
    }

    public float getTemperatureSetPoint()
    {
        return mPreferences.getFloat("setpoint_temperature", Constants.TEMPERATURESETPOINT);
    }

    public int getMaxCo2Setting()
    {
        return mPreferences.getInt("max_co2",Constants.MAXCO2);
    }

    public int getMinCo2Setting()
    {
        return mPreferences.getInt("min_co2",Constants.MINCO2);
    }

    public int getMaxHumiditySetting()
    {
        return mPreferences.getInt("max_humidity",Constants.MAXHUMIDITY);
    }

    public int getMinHumiditySetting()
    {
        return mPreferences.getInt("min_humidity",Constants.MINHUMIDITY);
    }

    public ArrayList<Device> getDevicesSetting() {
        Gson gson = new Gson();
        Type roomListType = new TypeToken<ArrayList<Device>>(){}.getType();
        ArrayList<Device> devices = gson.fromJson(mPreferences.getString("devices", "[]"), roomListType);
        return devices;
    }

    public String getSelectedDeviceId() {
        return mPreferences.getString("selected_device_id", "no device selected");
    }

}
